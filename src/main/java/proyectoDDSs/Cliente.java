package proyectoDDSs;

import proyectoDDSs.Dispositivo;

import java.util.*;
import java.util.stream.Collectors;

public class Cliente {
	
	private String nombre;
	private String apellido;
	private long numeroDocumento;
	private String tipoDocumento;
	private long telefono;
	private String domicilio;
	//private Date fechaAlta;
	protected ArrayList<Dispositivo> dispositivos = new ArrayList<Dispositivo>();
	
	//Los clientes tienen una categoria
	protected Categoria categoria; 
	
	//La clase GregorianCalendar permite instanciar una fecha pasandole como parametros (anio,mes,dia)
	public Cliente(String nombre,String apellido,String tipoDocumento,long documento,long telefono,String 
			domicilio,ArrayList<Dispositivo> unosDispositivos/*,GregorianCalendar fecha*/,ArrayList<Categoria> unaCategoria) {
		this.nombre=nombre;
		this.apellido=apellido;
		this.tipoDocumento=tipoDocumento;
		this.numeroDocumento=documento;
		this.telefono=telefono;
		this.domicilio=domicilio;
		//this.fechaAlta=fecha.getTime();//getTime devuelve una fecha del tipo Date
		this.dispositivos=unosDispositivos;
		//this.categoria = unaCategoria;
	}
	
	protected void estimativoFacturacion()
	{ this.categoria.CalcularMonto(categoria.cargoFijo, categoria.cargoAdicional, this.consumoMensual()); }
		
	
	//Agrego esta funcion para que el cliente pueda dar de alta algun dispositivo
	protected void addDispositivo(Dispositivo dispo) {dispositivos.add(dispo);}
	
	
	protected boolean algunDispositivoEncendido() 
	{
		return dispositivos.stream().anyMatch(dispositivo-> dispositivo.estaEncendido());
	}
	protected int cantDispositivosEncendidos() 
	{
		 return 
				 dispositivos.stream().
				 filter(dispositivo-> dispositivo.estaEncendido()).
				 collect(Collectors.toList()).
				 size();
	}
	protected int cantDispositivosApagados()
	{
		 return 
				 dispositivos.stream().
				 filter(dispositivo-> !dispositivo.estaEncendido()).
				 collect(Collectors.toList()).
				 size();
	}
	//la cantidad total de dispositivos la podemos saber directamente de la lista de dispositivos 
	protected int cantDispositivos() {return dispositivos.size();}
	
	protected double consumoMensual()
	{
		//Al cliente le calculo cuanto consume cada dispositivo sumo uno a uno su consumo y luego devuelvo el resultado,
		//Se realiza el cálculo suponiendo que están siempre funcionando.
		return 
				dispositivos.stream().
				mapToInt( elem -> elem.kwhConsumeXHora())
				.sum();
	}
	
	
}
