package com.domain.portal.service;

import java.util.List;

public interface IHibernateDAO<T> {

	T findOne(long id);

	List<T> findAll();

	void save(T entity);

	T update(T entity);

	void delete(T entity);

	void deleteById(long id);

}
