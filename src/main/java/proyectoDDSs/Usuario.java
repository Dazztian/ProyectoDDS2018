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
	private Long id;
	
	@Column(name="nombre_usuario")
	protected String usuario;
	@Column(name="contrasenia")
	protected String contrasenia;
	
	public Usuario() {
		
	}
	
	public Usuario (String unUsuario, String unaContrasenia) {
		usuario = unUsuario;
		contrasenia = unaContrasenia;
	}
	
	public Long getId() {
		return this.id;
	}

	public String getUsuario() {
		return this.usuario;
	}
	
	public String getContrasenia() {
		return this.contrasenia;
	}
	
}
