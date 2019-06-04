package com.domain.portal.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SequenceGenerator;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
		@NamedQuery(name = "User.find", query = "SELECT u FROM User u WHERE u.usuario=:username") })
@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "callFindUserByUsernameProcedure", procedureName = "findUserByUsername", resultClasses = {
				User.class }, parameters = {
						@StoredProcedureParameter(name = "username", mode = ParameterMode.IN, type = String.class),
						@StoredProcedureParameter(name = "user_cursor", mode = ParameterMode.OUT, type = Void.class) }),
		@NamedStoredProcedureQuery(name = "callFindRoleByUsernameProcedure", procedureName = "findRoleByUsername", resultClasses = {
				Role.class }, parameters = {
						@StoredProcedureParameter(name = "username", mode = ParameterMode.IN, type = String.class),
						@StoredProcedureParameter(name = "role_cursor", mode = ParameterMode.OUT, type = Void.class) }) })
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "USUARIO_ID_GENERATOR", sequenceName = "USUARIO_SEQUENCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_ID_GENERATOR")
	@Column(name = "ID_USUARIO")
	private long idUsuario;

	private String apellidos;

	private int estado;

	private String nombres;

	private String password;

	private String usuario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_ROL")
	private Role rol;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_SEDE")
	private Sede sede;

	public User() {
	}

	public long getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Role getRol() {
		return this.rol;
	}

	public void setRol(Role rol) {
		this.rol = rol;
	}

	public Sede getSede() {
		return this.sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

}