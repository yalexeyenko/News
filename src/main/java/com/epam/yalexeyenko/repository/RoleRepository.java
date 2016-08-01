package com.epam.yalexeyenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.yalexeyenko.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
