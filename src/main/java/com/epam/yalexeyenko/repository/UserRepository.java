package com.epam.yalexeyenko.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.epam.yalexeyenko.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	Page<User> findAll(Pageable pageable);
	
	@Modifying
	@Query("UPDATE User u SET u.enabled = ?1 WHERE u.id = ?2")
	void updateUserStatus(boolean enabled, Long id);
	
}
