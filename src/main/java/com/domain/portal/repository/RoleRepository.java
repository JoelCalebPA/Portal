package com.domain.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.portal.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
