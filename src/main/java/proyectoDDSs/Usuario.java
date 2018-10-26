package proyectoDDSs;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Tipo_Usuario")
@Table(name="Usuarios")
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
