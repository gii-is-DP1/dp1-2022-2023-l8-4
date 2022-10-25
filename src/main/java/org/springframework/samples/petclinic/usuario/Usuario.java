package org.springframework.samples.petclinic.usuario;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.partida.Partida;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
@ToString
@EqualsAndHashCode(callSuper=false)
public class Usuario extends NamedEntity {

	@Column(name = "contraseña")
	@NotEmpty
	private String contraseña;
	
	@Column(name = "fecha_registro")
	@NotEmpty
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_registro;
	
	@Column(name = "fecha_modificacion")
	@NotEmpty
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_modificacion;
	
	@Column(name = "ultimo_inicio_sesion")
	@NotEmpty
	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimo_inicio_sesion;
	
	@Column(name = "nombre_usuario")
	@NotEmpty
	private String nombre_usuario;
	
	@Column(name = "email")
	@NotEmpty
	@Email
	private String email;
	
	@Column(name = "administrador")
	@NotEmpty
	private Boolean administrador;
	
	@Column(name = "fecha_nacimiento")
	@NotEmpty
	@DateTimeFormat(pattern="yyyy/mm/dd")
	private LocalDate fecha_nacimiento;
	
	@Column(name = "foto_perfil")
	@NotEmpty
	private String foto_perfil;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "creador_id")
	private Collection<Partida> partidas_creadas;
	
	@ManyToMany(fetch = FetchType.EAGER,
				cascade = {CascadeType.PERSIST,
						CascadeType.MERGE,
						CascadeType.DETACH,
						CascadeType.REFRESH})
	@JoinTable(name = "jugadores_partidas", 
				joinColumns = @JoinColumn(name = "jugador_id"),
				inverseJoinColumns = @JoinColumn(name = "partida_id"))
	private Collection<Partida> partidas_jugadas;
	
}