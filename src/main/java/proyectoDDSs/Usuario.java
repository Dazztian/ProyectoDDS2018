package proyectoDDSs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Usuario {

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="nombre_usuario")
	private String usuario;
	@Column(name="contrasenia")
	private String contrasenia;
	
	public Usuario() {
		
	}
	
	public Usuario (String unUsuario, String unaContrasenia) {
		usuario = unUsuario;
		contrasenia = unaContrasenia;
	}
	
}
