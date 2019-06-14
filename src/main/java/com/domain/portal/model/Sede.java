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
@Table(name = "sede")
@NamedQueries({ @NamedQuery(name = "Sede.findAll", query = "SELECT s FROM Sede s"),
		@NamedQuery(name = "Sede.findByUser", query = "SELECT s FROM Sede s INNER JOIN User u ON u.sede.idSede=s.idSede WHERE u.usuario=:username"),
		@NamedQuery(name = "Sede.find", query = "SELECT s FROM Sede s WHERE s.idSede=:idSede") })
public class Sede implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEDE_ID_GENERATOR", sequenceName = "SEDE_SEQUENCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEDE_ID_GENERATOR")
	@Column(name = "ID_SEDE")
	private long idSede;

	private String nombre;

	@OneToMany(mappedBy = "sede", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<User> usuarios;

	public Sede() {
	}

	public long getIdSede() {
		return this.idSede;
	}

	public void setIdSede(long idSede) {
		this.idSede = idSede;
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