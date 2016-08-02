package com.epam.yalexeyenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.yalexeyenko.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
	Status findByName(String name);
}
