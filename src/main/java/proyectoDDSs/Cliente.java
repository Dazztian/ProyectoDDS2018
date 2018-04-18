package proyectoDDSs;

import proyectoDDSs.Dispositivo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class Cliente {

	private String nombre;
	private String apellido;
	private int numeroDocumento;
	private String tipoDocumento;
	private int telefono;
	private String domicilio;
	
	
	
	List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();
	
	private boolean dispoEncendido() 
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
				 filter(dispositivo-> dispositivo.estaEncendido()).
				 collect(Collectors.toList()).
				 size();
	}
	protected int cantDispositivos() {
		return this.cantDispositivosEncendidos() + this.cantDispositivosApagados();
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
	
	//Constructor a desarrollar
	public Cliente() {}
}
