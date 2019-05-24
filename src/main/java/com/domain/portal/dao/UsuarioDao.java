package com.domain.portal.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.domain.portal.model.Rol;
import com.domain.portal.model.Usuario;

public class UsuarioDao {

	private EntityManager em;

	@Autowired
	public UsuarioDao(EntityManagerFactory emf) {
		this.em = emf.createEntityManager();
	}

	@Transactional(readOnly = true)
	public Usuario getUsuario() {
		TypedQuery<Usuario> usuario = em.createQuery("SELECT u FROM usuario u", Usuario.class);
		return usuario.getSingleResult();
	}

	@Transactional(readOnly = true)
	public List<String> getRoles() {
		TypedQuery<Rol> roles = em.createNamedQuery("Rol.findAll", Rol.class);
		return roles.getResultStream().map(Rol::getNombre).collect(Collectors.toList());
	}

}
