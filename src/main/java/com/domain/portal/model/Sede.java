package com.domain.portal.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "sede")
@NamedQuery(name = "Sede.findAll", query = "SELECT s FROM Sede s")
public class Sede implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEDE_ID_GENERATOR", sequenceName = "SEDE_SEQUENCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEDE_ID_GENERATOR")
	@Column(name = "ID_SEDE")
	private long idSede;

	private String nombre;

	@OneToOne(mappedBy = "sede", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	private User usuario;

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

	public User getUser() {
		return usuario;
	}

	public void setUser(User user) {
		this.usuario = user;
	}

}