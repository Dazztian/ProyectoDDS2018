package proyectoDDSs;

import proyectoDDSs.Dispositivo;

import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import simplex.facade.*;


public class Cliente {
	
	private String nombre;
	private String apellido;
	private long numeroDocumento;
	private String tipoDocumento;
	private long telefono;
	private String domicilio;
	private String usuario;
	private String contrasenia;
	private double longitud;
	private double latitud;
	
	//pattern "yyyy-MM-dd'T'HH:mm:ssZ" to be ISO8601
	private Calendar fechaAlta;
	public ArrayList<Dispositivo> dispositivos = new ArrayList<Dispositivo>();
	public int puntos;
	public Estado estadoParaSimplex = new Apagado();
	
		
	//Los clientes tienen una categoria
	protected Categoria categoria; 
		
		//La clase GregorianCalendar permite instanciar una fecha pasandole como parametros (anio,mes,dia)
		public Cliente(String nombre,String apellido,String tipoDocumento,long documento,long telefono,String 
				domicilio,ArrayList<Dispositivo> unosDispositivos,Calendar unaFecha,Categoria unaCategoria, 
				double latitud, double longitud, ArrayList<Transformador> trafos) {
			this.nombre=nombre;
			this.apellido=apellido;
			this.tipoDocumento=tipoDocumento;
			this.numeroDocumento=documento;
			this.telefono=telefono;
			this.domicilio=domicilio;
			this.fechaAlta = unaFecha;
			this.dispositivos=unosDispositivos;
			this.categoria = unaCategoria;
			this.latitud=latitud;
			this.longitud=longitud;
			
			asignarTrafoMasCercano(trafos);
			
			
		}
	private void asignarTrafoMasCercano(ArrayList<Transformador> trafos) {
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
	public ArrayList<Dispositivo> dispositivos() {return dispositivos;}
	
	public double estimativoFacturacion()
	{ return
			categoria.getCargoFijo() + (categoria.getCargoAdicional() * this.consumoMensual()); }
		
	// --------------------------------------------------------------------------------VERSION ENTREGA1 DE AGREGAR DISPOSITIVO--------------------------------------------------------------------------------
	//Agrego esta funcion para que el cliente pueda dar de alta algun dispositivo
	public void addDispositivo(Dispositivo dispo) 
	{
		dispositivos.add(dispo);
		if(dispo.esInteligente()) 
		{
			puntos +=15;
		}
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
						       	DispositivoInteligente dispositivoEncontrado = new DispositivoInteligente(dispoNombreCompleto, dispoConsumo, new Encendido(), consumoMin, consumoMax);
						       	dispositivos.add(dispositivoEncontrado);
						       	puntos +=15;
			       	    	}
			       	    	else
			       	    	{
			       	    		DispositivoEstandar dispositivoEncontrado = new DispositivoEstandar(dispoNombreCompleto, dispoConsumo , 10, consumoMin, consumoMax);
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
	
	
	public double consumoMensual()
	{
		//Al cliente le calculo cuanto consume cada dispositivo sumo uno a uno su consumo y luego devuelvo el resultado,
		//Se realiza el c�lculo suponiendo que est�n siempre funcionando.
		return 
				dispositivos.stream().
				mapToDouble(dispositivo -> dispositivo.consumoMensual()).sum();
	}
	
	public void adaptarDispositivo(DispositivoEstandar unDispositivo) {
		ModuloAdaptador dispositivoAdaptado = unDispositivo.adaptar();
		dispositivos.remove(unDispositivo);
		dispositivos.add(dispositivoAdaptado);
		puntos += 10;
	}

	public PointValuePair consumoOptimo(){
		
		  SimplexFacade consumoOptimo = new SimplexFacade (GoalType.MAXIMIZE, true);
		  double[] auxLista = new double[dispositivos.size()];
		  for(int i = 0; i < dispositivos.size(); i++) {
			  auxLista[i] = dispositivos.get(i).kwhConsumeXHora();
		  }
		  consumoOptimo.crearFuncionEconomica(auxLista);
		  consumoOptimo.agregarRestriccion(Relationship.LEQ, 612, auxLista);
		  for(int i = 0; i < dispositivos.size(); i++){
			  
		  		for(int j = 0; j < dispositivos.size(); j++) {
		  			auxLista[j] = 0;
		  		}
		  		auxLista[i] = 1;
		  		consumoOptimo.agregarRestriccion(Relationship.GEQ, dispositivos.get(i).consumoMinimo, auxLista);
		  		consumoOptimo.agregarRestriccion(Relationship.LEQ, dispositivos.get(i).consumoMaximo, auxLista);
				}
			PointValuePair solucion = consumoOptimo.resolver();
			return solucion;
		   
		 
	}
	
	public void mostrarConsumoOptimo() {
		
		PointValuePair solucion = this.consumoOptimo();
		
		for(int i = 0; i < dispositivos.size(); i++){
			System.out.format("El consumo Maximo para el dispositivo %d es: \n", i); 
			System.out.format(" %f \n ", (float) solucion.getPoint()[i]);
			}
		
	}
	
	public void cambiarConfigAccionConsumoOptimo(Estado unEstado) {
		estadoParaSimplex = unEstado;
	}
	
	public void accionarSegunConsumoOptimo() {
		
		PointValuePair consumosOptimos = this.consumoOptimo();
		
		for(int i = 0; i < dispositivos.size(); i++) {
			if(dispositivos.get(i).esInteligente()) {
				if(consumosOptimos.getPoint()[i] <= ((DispositivoInteligente) dispositivos.get(i)).horasDeUsoMensuales()) {
					((DispositivoInteligente) dispositivos.get(i)).cambiarEstado(this.estadoParaSimplex);
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
}
