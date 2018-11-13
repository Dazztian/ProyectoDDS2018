package proyectoDDSs;

import proyectoDDSs.Dispositivo;
import proyectoDDSs.DistanceCalculator;

import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import modelsPersistencia.CategoriaModel;
import modelsPersistencia.ClienteModel;
import modelsPersistencia.DispositivoModel;
import simplex.facade.*;

@Entity
@DiscriminatorValue("C")
public class Cliente extends Usuario {
		
	@Column(name="nombre")
	private String nombre;
	@Column(name="apellido")
	private String apellido;
	@Column(name="numero_dni")
	private long numeroDocumento;
	@Column(name="tipo_dni")
	private String tipoDocumento;
	@Column(name="telefono")
	private long telefono;
	@Column(name="domicilio")
	private String domicilio;
	@Column(name="longitud")
	private double longitud;
	@Column(name="latitud")
	private double latitud;
	
	//pattern "yyyy-MM-dd'T'HH:mm:ssZ" to be ISO8601
	@Column(name="fecha_alta")
	private Calendar fechaAlta;
	
	@OneToMany(mappedBy="cliente",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();
	
	@Column(name="puntos")
	public int puntos;
	@Transient
	public Estado estadoParaSimplex = new Apagado();
	
	
	//Los clientes tienen una categoria
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="categoria")
	protected Categoria categoria; 
	
	public Cliente() {
		
	}
	
		//La clase GregorianCalendar permite instanciar una fecha pasandole como parametros (anio,mes,dia)	
	public Cliente(String nombre,String apellido,String tipoDocumento,long documento,long telefono,String 
			domicilio,ArrayList<Dispositivo> unosDispositivos,Calendar unaFecha,int numeroCategoria, 
			double latitud, double longitud, String usuario, String contrasenia) {
		super(usuario, contrasenia);
		this.nombre=nombre;
		this.apellido=apellido;
		this.tipoDocumento=tipoDocumento;
		this.numeroDocumento=documento;
		this.telefono=telefono;
		this.domicilio=domicilio;
		this.fechaAlta = unaFecha;
		this.dispositivos=unosDispositivos;
		this.categoria = new CategoriaModel().buscarCategoria(numeroCategoria);
		this.latitud=latitud;
		this.longitud=longitud;
		
		
		
		
	}	
	public Categoria getCategoria() {
		return this.categoria;
	}
	
	
	public void asignarTrafoMasCercano(List<Transformador> trafos) {
			double distanciaMasCorta, distanciaActual;
			Transformador trafoMasCercano;
			
			distanciaMasCorta=DistanceCalculator.calcularDistancia(this.latitud, this.longitud, 
					trafos.get(0).getLat(), trafos.get(0).getLong(), "K");
			trafoMasCercano=trafos.get(0);
			
			for(int i=1;i<trafos.size();i++) {
					distanciaActual=DistanceCalculator.calcularDistancia(this.latitud, this.longitud, 
							trafos.get(i).getLat(), trafos.get(i).getLong(), "K");
					
					if(distanciaActual < distanciaMasCorta) {
						trafoMasCercano=trafos.get(i);
					}
				
			}
			
			trafoMasCercano.addCliente(this);
		
		}
	
	public List<Dispositivo> getDispositivos() {return this.dispositivos;}
	
	public double estimativoFacturacion()
	{ return
			categoria.getCargoFijo() + (categoria.getCargoAdicional() * this.consumoMensual()); }
		
	// --------------------------------------------------------------------------------VERSION ENTREGA1 DE AGREGAR DISPOSITIVO--------------------------------------------------------------------------------
	//Agrego esta funcion para que el cliente pueda dar de alta algun dispositivo
	public void addDispositivo(Dispositivo dispo) 
	{
		
		if(dispo.esInteligente()) {
			this.puntos +=15;
		}
		this.dispositivos.add(dispo);
		
	}
	
	public void addDispositivoTEST(Dispositivo dispo) {
		if (dispo.esInteligente()) {
			puntos +=15;
		}
		dispositivos.add(dispo);
	}
	
	
	// --------------------------------------------------------------------------------VERSION ENTREGA1 DE AGREGAR DISPOSITIVO--------------------------------------------------------------------------------
	
	//---------------------------------------------------------------------------------VERSION ENTREGA2 DE AGREGAR DISPOSITIVO----------------------------------------------------------------------------------

	public void agregarDispositivo( String tipoDispo)
	{
		 JSONParser parser = new JSONParser();
	        try 
	        {
	        	JSONArray tablaDispositivos = (JSONArray) parser.parse(new FileReader("..\\ProyectoDDS2018\\src\\main\\java\\proyectoDDSs\\tablaDeDispositivos.json"));
	        
	        	//Todo lo que "cargue" del JSON lo guardo en o
	        	for (Object o : tablaDispositivos)
	          	  {
			       	    JSONObject unDispo = (JSONObject) o;
			       	    
			       	    //�Es este el dispositivo que busco?
			       	    String dispoTipo = (String) unDispo.get("tipo");
			       	    
			       	    if(dispoTipo.equals(tipoDispo))
			       	    {
			       	    	String dispoNombre = (String) unDispo.get("equipo");
			       	    	Boolean dispoInteligente = (Boolean) unDispo.get("inteligente");
					       	Boolean dispoBajo_Consumo = (Boolean) unDispo.get("bajo_consumo");
					       	double dispoConsumo = (double) unDispo.get("consumo");
					       	double consumoMin = (double) unDispo.get("consumoMin");
					       	double consumoMax = (double) unDispo.get("consumoMax");
					       	
					       	String dispoNombreCompleto = dispoNombre.concat(dispoTipo);
			       	    	
			       	    	if(dispoInteligente)
			       	    	{			       	    		
						       	DispositivoInteligente dispositivoEncontrado = new DispositivoInteligente(dispoNombre, dispoTipo, dispoConsumo, new Encendido(), consumoMin, consumoMax);
						       	dispositivos.add(dispositivoEncontrado);
						       	puntos +=15;
			       	    	}
			       	    	else
			       	    	{
			       	    		DispositivoEstandar dispositivoEncontrado = new DispositivoEstandar(dispoNombre, dispoTipo, dispoConsumo , 10, consumoMin, consumoMax);
						       	dispositivos.add(dispositivoEncontrado);
			       	    	}
			       	    }
			       	    
			      }
	   	    }
	        	catch (Exception e) { e.printStackTrace(); }
 }
	
	//---------------------------------------------------------------------------------VERSION ENTREGA2 DE AGREGAR DISPOSITIVO----------------------------------------------------------------------------------
	
	
	
	public void bajaDispositivo(Dispositivo unDispositivo) {dispositivos.remove(unDispositivo);}
	
	public boolean algunDispositivoEncendido() 
	{
		return dispositivos.stream().filter(dispositivo->dispositivo.esInteligente()).anyMatch(dispositivo-> dispositivo.estaEncendido());
	}
	public int cantDispositivosEncendidos() 
	{
		 return 
				 dispositivos.stream().
				 filter(dispositivo->dispositivo.esInteligente()).
				 filter(dispositivo-> dispositivo.estaEncendido()).
				 collect(Collectors.toList()).
				 size();
	}
	public int cantDispositivosApagados()
	{
		 return 
				 dispositivos.stream().
				 filter(dispositivo-> dispositivo.esInteligente()).
				 filter(dispositivo-> !dispositivo.estaEncendido()).
				 collect(Collectors.toList()).
				 size();
	}
	//la cantidad total de dispositivos la podemos saber directamente de la lista de dispositivos 
	public int cantDispositivos() {return dispositivos.size();}
	
	public double consumoEnLaUltimaHora()
	{
		//Al cliente le calculo cuanto consume cada dispositivo sumo uno a uno su consumo y luego devuelvo el resultado,
		//Se realiza el c�lculo suponiendo que est�n siempre funcionando.
		return 
				dispositivos.stream().
				mapToDouble(dispositivo -> dispositivo.consumoEnLasUltimasNHoras(1)).sum();
				
	}
	
	public double consumoMensual()
	{
		//Al cliente le calculo cuanto consume cada dispositivo sumo uno a uno su consumo y luego devuelvo el resultado,
		//Se realiza el c�lculo suponiendo que est�n siempre funcionando.
		return dispositivos.stream().
				mapToDouble(dispositivo -> dispositivo.consumoMensual()).sum();
	}
	
	public void adaptarDispositivo(DispositivoEstandar dispo) {
		//Aca deberia hacerse una query que busque el adaptador para ese dispo_a_adaptar
		ModuloAdaptador dispositivoAdaptado = dispo.adaptar();
		
		dispositivos.remove(dispo);
		dispositivos.add(dispositivoAdaptado);
		puntos += 10;
	}
	
	public void adaptarDispositivoTEST (DispositivoEstandar dispo) {
		ModuloAdaptador modulo = dispo.adaptar();
		dispositivos.remove(dispo);
		dispositivos.add(modulo);
		puntos += 10;
	}

	public PointValuePair consumoOptimo(){
		
		  SimplexFacade consumoOptimo = new SimplexFacade (GoalType.MAXIMIZE, true);
		  Object[] dispositivosSinHeladeras = dispositivos.stream().filter(elem -> elem.noEsHeladera()).toArray();
		  double[] auxLista = new double[dispositivos.size()];
		  for(int i = 0; i < dispositivosSinHeladeras.length; i++) {
			  auxLista[i] = ((Dispositivo) dispositivosSinHeladeras[i]).kwhConsumeXHora();
		  }
		  consumoOptimo.crearFuncionEconomica(auxLista);
		  consumoOptimo.agregarRestriccion(Relationship.LEQ, 612, auxLista);
		  for(int i = 0; i < dispositivosSinHeladeras.length; i++){
			  
		  		for(int j = 0; j < dispositivosSinHeladeras.length; j++) {
		  			auxLista[j] = 0;
		  		}
		  		auxLista[i] = 1;
		  		consumoOptimo.agregarRestriccion(Relationship.GEQ, ((Dispositivo) dispositivosSinHeladeras[i]).consumoMinimo, auxLista);
		  		consumoOptimo.agregarRestriccion(Relationship.LEQ, ((Dispositivo) dispositivosSinHeladeras[i]).consumoMaximo, auxLista);
				}
			PointValuePair solucion = consumoOptimo.resolver();
			return solucion;
		   
		 
	}
	
	public void mostrarConsumoOptimo() {
		try {
		PointValuePair solucion = this.consumoOptimo();
		}
		catch (Exception e) {
			System.out.format("Su hogar no es optimizable \n");
			return;
		}
		PointValuePair solucion = this.consumoOptimo();
		Object[] dispositivosSinHeladeras = dispositivos.stream().filter(elem -> elem.noEsHeladera()).toArray();
		
		for(int i = 0; i < dispositivosSinHeladeras.length; i++){
			System.out.format("El consumo Maximo para el dispositivo %d es: \n", i); 
			System.out.format(" %f \n ", (float) solucion.getPoint()[i]);
			}
		
	}
	
	public void cambiarConfigAccionConsumoOptimo(Estado unEstado) {
		estadoParaSimplex = unEstado;
	}
	
	public void accionarSegunConsumoOptimo() {
		
		PointValuePair consumosOptimos = this.consumoOptimo();
		Object[] dispositivosSinHeladeras = dispositivos.stream().filter(elem -> elem.noEsHeladera()).toArray();
		
		for(int i = 0; i < dispositivosSinHeladeras.length; i++) {
			if(((Dispositivo) dispositivosSinHeladeras[i]).esInteligente()) {
				if(consumosOptimos.getPoint()[i] <= ((DispositivoInteligente) dispositivosSinHeladeras[i]).horasDeUsoMensuales()) {
					((DispositivoInteligente) dispositivosSinHeladeras[i]).cambiarEstado(this.estadoParaSimplex);
				}
			}
		}
	}
	
	public void suscribirseAlSimplexAutomatico() {
		SimplexAutomatico simplex = SimplexAutomatico.getObserver();
		simplex.suscribirASimplexAutomatico(this);
	}
	
	public void desuscribirseAlSimplexAutomatico() {
		SimplexAutomatico simplex = SimplexAutomatico.getObserver();
		simplex.desuscribirASimplexAutomatico(this);
	}
	
	public double consumoEnIntervalo(LocalDateTime fechaLimiteMaxima, LocalDateTime fechaLimiteMinima) {
		return dispositivos.stream().mapToDouble(disp -> disp.consumoEnIntervalo(fechaLimiteMaxima, fechaLimiteMinima)).sum();
	}
	
	public void cambiarGeolocalizacion (double nuevaLatitud, double nuevaLongitud) {
		latitud = nuevaLatitud;
		longitud = nuevaLongitud;
	}


	public double latitud() {
		return latitud;
	}
	public double longitud() {
		return longitud;
	}
	
	public String getApellido() {
		return this.apellido;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public long getNumeroDocumento() {
		return this.numeroDocumento;
	}

}