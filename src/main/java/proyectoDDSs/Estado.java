package proyectoDDSs;

import java.time.LocalDateTime;
import javax.persistence.*;


/*
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Estado")
@Table(name = "Estado")
*/

public abstract class Estado {
	
	/*
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "fechaDeInicio")
	public LocalDateTime fechaDeInicio;
	
	@Column(name = "fechaDeFin")
	public LocalDateTime fechaDeFin = null;
	
	public Estado () {
		fechaDeInicio = LocalDateTime.now();
	}
	public Estado (LocalDateTime inicio, LocalDateTime fin) {
		fechaDeInicio = inicio;
		fechaDeFin = fin;
	}
	
	*/
	
	public abstract boolean estadoEncendido();
	public abstract double coeficienteDeConsumo();
	
	/*
	public void finalizarEstado() {
		fechaDeFin = LocalDateTime.now();
	}
	public void finalizarEstado(LocalDateTime fin) {
		fechaDeFin = fin;
	}
	*/
	public abstract String nombreEstado();
	
	//Hace un recuento de cuantas horas estuvo el Estado activo.
	
	/*
	public int horasActivas() {
		if(fechaDeFin == null) {
			return horasActivasHastaElMomento(LocalDateTime.now());
		}else {
		return horasDeDiferencia(fechaDeFin, fechaDeInicio);
		}
	}
	
	*/
	//Hace un recuento de cuantas horas estuvo activo el Estado al momento de hacer la consulta,
	//Se usa cuando todavia no se tiene un a fecha de fin del estado, o sea, cuando es el estado actual.
	
	/*
	public int horasActivasHastaElMomento(LocalDateTime fechaDeConsulta) {
		return horasDeDiferencia(fechaDeConsulta, fechaDeInicio);
	}
	*/
	
	
	public int horasDeDiferencia(LocalDateTime fechaMayor, LocalDateTime fechaMenor) {
		return  ((fechaMayor.getYear() - fechaMenor.getYear()) * 365 * 24) +
				(((fechaMayor.getMonthValue() - fechaMenor.getMonthValue()) * 30 * 24)) +
				(((fechaMayor.getDayOfMonth() - fechaMenor.getDayOfMonth()) * 24)) +
				((fechaMayor.getHour() - fechaMenor.getHour()));
	}
	
	/*
	public boolean ocurreEntre (LocalDateTime fechaLimiteMaxima, LocalDateTime fechaLimiteMinima) {
		LocalDateTime aux = fechaDeFin;
		if(fechaDeFin == null){
			aux = LocalDateTime.now();
		}
		return (aux.isBefore(fechaLimiteMinima) && fechaDeInicio.isAfter(fechaLimiteMinima));
	}
	public void mostrarPeriodoPorConsola() {
		System.out.format("El estado empezo el %s y termino el %s \n", fechaDeInicio.toString(), fechaDeFin.toString());
	}
	
	public double coeficienteConHorasActivas() {
		return (this.coeficienteDeConsumo() * this.horasActivas());
	}
	*/
	
}
