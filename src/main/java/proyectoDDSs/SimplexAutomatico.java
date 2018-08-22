package proyectoDDSs;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SimplexAutomatico {
	
	private Timer temporizador;
	private static SimplexAutomatico simplex;
	private static boolean singleObserver  = false;
	public ArrayList<Cliente> ClientesQueNotifico = new ArrayList<Cliente>();
	
	private SimplexAutomatico() {

		temporizador=new Timer();
		int intervalo=1000;
		temporizador.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				actualizarATodos();
			}
		}, 0, intervalo*1000);
	}
	
	public static SimplexAutomatico getObserver(){
	
		if(!singleObserver){
			
			simplex = new SimplexAutomatico();
			
			singleObserver=true;
			
		}
		
		return simplex;
		
	}
	public void ActualizarCliente(Cliente unCliente){
		unCliente.accionarSegunConsumoOptimo();	
	}
	
	private void actualizarATodos() {
		for (Cliente unCliente:ClientesQueNotifico) {
			this.ActualizarCliente(unCliente);
		}
	}
	
	public void suscribirASimplexAutomatico(Cliente unCliente) {
		ClientesQueNotifico.add(unCliente);}
	
	public void desuscribirASimplexAutomatico(Cliente unCliente) {
		ClientesQueNotifico.remove(unCliente);}
	
}
