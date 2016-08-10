package com.epam.yalexeyenko.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.epam.yalexeyenko.dto.UserDTO;

public interface UserService extends Service<UserDTO> {
	UserDTO findByEmail(String email);
	Page<UserDTO> findAll(Pageable pageable);
	void updateUserStatus(boolean enabled, Long id);
}
