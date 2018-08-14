package proyectoDDSs;

import proyectoDDSs.Dispositivo;

import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Cliente {
	
	private String nombre;
	private String apellido;
	private long numeroDocumento;
	private String tipoDocumento;
	private long telefono;
	private String domicilio;
	private String usuario;
	private String contrasenia;
	//pattern "yyyy-MM-dd'T'HH:mm:ssZ" to be ISO8601
	private Calendar fechaAlta;
	public ArrayList<Dispositivo> dispositivos = new ArrayList<Dispositivo>();
	public int puntos;
		
		
	//Los clientes tienen una categoria
	protected Categoria categoria; 
		
		//La clase GregorianCalendar permite instanciar una fecha pasandole como parametros (anio,mes,dia)
		public Cliente(String nombre,String apellido,String tipoDocumento,long documento,long telefono,String 
				domicilio,ArrayList<Dispositivo> unosDispositivos,Calendar unaFecha,Categoria unaCategoria) {
			this.nombre=nombre;
			this.apellido=apellido;
			this.tipoDocumento=tipoDocumento;
			this.numeroDocumento=documento;
			this.telefono=telefono;
			this.domicilio=domicilio;
			this.fechaAlta = unaFecha;
			this.dispositivos=unosDispositivos;
			this.categoria = unaCategoria;
			
			
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

	public void agregarDipositivo( String tipoDispo)
	{
		 JSONParser parser = new JSONParser();
	        try 
	        {
	        	JSONArray tablaDispositivos = (JSONArray) parser.parse(new FileReader("..\\ProyectoDDS2018\\src\\main\\java\\proyectoDDSs\\tablaDeDispositivos.json"));
	        
	        	//Todo lo que "cargue" del JSON lo guardo en o
	        	for (Object o : tablaDispositivos)
	          	  {
			       	    JSONObject unDispo = (JSONObject) o;
			       	    
			       	    //¿Es este el dispositivo que busco?
			       	    String dispoTipo = (String) unDispo.get("tipo");
			       	    
			       	    if(dispoTipo== tipoDispo)
			       	    {
			       	    	String dispoNombre = (String) unDispo.get("equipo");
			       	    	Boolean dispoInteligente = (Boolean) unDispo.get("inteligente");
					       	Boolean dispoBajo_Consumo = (Boolean) unDispo.get("bajo_consumo");
					       	double dispoConsumo = (double) unDispo.get("consumo");
					       	
					       	String dispoNombreCompleto = dispoNombre.concat(dispoTipo);
			       	    	
			       	    	if(dispoInteligente)
			       	    	{			       	    		
						       	DispositivoInteligente dispositivoEncontrado = new DispositivoInteligente(dispoNombreCompleto, dispoConsumo, new Encendido());
						       	dispositivos.add(dispositivoEncontrado);
						       	puntos +=15;
			       	    	}
			       	    	else
			       	    	{
			       	    		DispositivoEstandar dispositivoEncontrado = new DispositivoEstandar(dispoNombreCompleto, dispoConsumo , 10);
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
		//Se realiza el cï¿½lculo suponiendo que estï¿½n siempre funcionando.
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
	
}
