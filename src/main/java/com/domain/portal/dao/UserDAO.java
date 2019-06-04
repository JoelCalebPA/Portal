package com.domain.portal.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.domain.portal.model.Role;
import com.domain.portal.model.Sede;
import com.domain.portal.model.User;

import oracle.jdbc.OracleTypes;

@Repository
public class UserDAO extends AbstractHibernateDAO<User> implements IUserDAO {

	@Autowired
	private BasicDataSource dataSource;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserDAO() {
		setClazz(User.class);
	}

	@Override
	public User findUser(String username) {
		Connection connection;
		CallableStatement callableStatement;
		ResultSet resultSet;
		try {
			connection = dataSource.getConnection();
			callableStatement = connection.prepareCall("{ call findUserByUsername(?,?)}");
			callableStatement.registerOutParameter("user_cursor", OracleTypes.CURSOR);
			callableStatement.setString("username", username);
			callableStatement.execute();
			resultSet = (ResultSet) callableStatement.getObject("user_cursor");
			User u = new User();
			while (resultSet.next()) {
				Role r = new Role();
				r.setIdRol(resultSet.getLong("id_rol"));
				Sede s = new Sede();
				s.setIdSede(resultSet.getLong("id_sede"));
				u.setIdUsuario(resultSet.getLong("id_usuario"));
				u.setRol(r);
				u.setSede(s);
				u.setNombres(resultSet.getString("nombres"));
				u.setApellidos(resultSet.getString("apellidos"));
				u.setUsuario(resultSet.getString("usuario"));
				u.setPassword(resultSet.getString("password"));
				u.setEstado(resultSet.getInt("estado"));
			}
			return u;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Role findRoleUser() {
		return getCurrentSession().createNamedQuery("Role.find", Role.class).setParameter("idRol", (long) 1)
				.getSingleResult();
	}

	@Override
	public Role findRoleByUser(String username) {
		try {
			Connection conn = dataSource.getConnection();
			CallableStatement cs = conn.prepareCall("{ call findRoleByUsername(?,?)}");
			cs.registerOutParameter("role_cursor", OracleTypes.CURSOR);
			cs.setString("username", username);
			cs.execute();
			ResultSet rs = (ResultSet) cs.getObject("role_cursor");
			Role r = new Role();
			while (rs.next()) {
				r.setIdRol(rs.getLong("id_rol"));
				r.setNombre(rs.getString("nombre"));
			}
			return r;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findOne(long id) {
		return super.findOne(id);
	}

	@Override
	public List<User> findAll() {
		return super.findAll();
	}

	@Override
	public void save(User entity) {
		entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
		super.save(entity);
	}

	@Override
	public User update(User entity) {
		return super.update(entity);
	}

	@Override
	public void delete(User entity) {
		super.delete(entity);
	}

	@Override
	public void deleteById(long id) {
		super.deleteById(id);
	}

}
