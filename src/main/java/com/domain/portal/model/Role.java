package com.domain.portal.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "rol")
@NamedQueries({ @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r"),
		@NamedQuery(name = "Role.findByUser", query = "SELECT r FROM Role r INNER JOIN User u ON u.rol.idRol=r.idRol WHERE u.usuario=:username"),
		@NamedQuery(name = "Role.find", query = "SELECT r FROM Role r WHERE r.idRol=:idRol") })
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ROL_ID_GENERATOR", sequenceName = "rol_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROL_ID_GENERATOR")
	@Column(name = "ID_ROL")
	private long idRol;

	private String nombre;

	@OneToMany(mappedBy = "rol", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<User> usuarios;

	public Role() {
	}

	public long getIdRol() {
		return this.idRol;
	}

	public void setIdRol(long idRol) {
		this.idRol = idRol;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<User> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<User> usuarios) {
		this.usuarios = usuarios;
	}

	public void addUsuario(User user) {
		this.usuarios.add(user);
	}

	public void removeUsuario(User user) {
		this.usuarios.remove(user);
	}

}