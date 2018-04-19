package proyectoDDSs;

import proyectoDDSs.Dispositivo;

import java.util.*;
import java.util.stream.Collectors;

public class Cliente {
	
	private String nombre;
	private String apellido;
	private int numeroDocumento;
	private String tipoDocumento;
	private int telefono;
	private String domicilio;
	private Date fechaAlta;
	protected ArrayList<Dispositivo> dispositivos = new ArrayList<Dispositivo>();
	
	//Constructor a desarrollar
	//La clase GregorianCalendar permite instanciar una fecha pasandole como parametros (anio,mes,dia)
	public Cliente(String nombre,String apellido,String tipoDocumento,int documento,int telefono,String domicilio,GregorianCalendar fecha) {
		this.nombre=nombre;
		this.apellido=apellido;
		this.tipoDocumento=tipoDocumento;
		this.numeroDocumento=documento;
		this.telefono=telefono;
		this.domicilio=domicilio;
		this.fechaAlta=fecha.getTime(); //getTime devuelve una fecha del tipo Date
	}
	
	//Agrego esta funcion para que el cliente pueda dar de alta algun dispositivo
	protected void addDispositivo(Dispositivo dispo) {
		dispositivos.add(dispo);
	}
	
	
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
	protected int cantDispositivos() {
		return dispositivos.size();//this.cantDispositivosEncendidos() + this.cantDispositivosApagados();
	}
	
	protected float consumoMensual()
	{
		//A cada cliente le calculo cuanto consume cada dispositivo
		//Sumo uno a uno su consumo y luego devuelvo el resultado
		return 
				dispositivos.stream().
				mapToInt( elem -> elem.kwhConsumeXHora())
				.sum();
	}
	
	
}
