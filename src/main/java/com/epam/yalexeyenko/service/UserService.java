package com.epam.yalexeyenko.service;

import com.epam.yalexeyenko.dto.UserDTO;

public interface UserService extends Service<UserDTO> {
	UserDTO findByEmail(String email);
}
