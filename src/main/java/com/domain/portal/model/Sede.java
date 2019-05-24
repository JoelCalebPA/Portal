package com.domain.portal.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the SEDE database table.
 * 
 */
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

	// bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy = "sede")
	private List<Usuario> usuarios;

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

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setSede(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setSede(null);

		return usuario;
	}

}