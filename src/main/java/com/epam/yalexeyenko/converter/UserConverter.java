package com.epam.yalexeyenko.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.epam.yalexeyenko.dto.UserDTO;
import com.epam.yalexeyenko.model.User;

@Repository
public class UserConverter {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserDTO userToDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setEmail(user.getEmail());
		userDTO.setEnabled(user.isEnabled());
		userDTO.setPassword(user.getPassword());
		return userDTO;
	}
	
	public User DTOToUser(UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setEmail(userDTO.getEmail());
		user.setEnabled(userDTO.isEnabled());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		return user;
	}
	
	public List<UserDTO> userToDTO(List<User> userList) {
		List<UserDTO> userDTOList = new ArrayList<>();
		for (User user : userList) {
			userDTOList.add(userToDTO(user));
		}
		return userDTOList;
	}
	
	public List<User> DTOToUser(List<UserDTO> userDTOList) {
		List<User> userList = new ArrayList<>();
		for (UserDTO userDTO : userDTOList) {
			userList.add(DTOToUser(userDTO));
		}
		return userList;
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
}
