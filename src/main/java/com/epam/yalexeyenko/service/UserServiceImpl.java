package com.epam.yalexeyenko.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.yalexeyenko.converter.UserConverter;
import com.epam.yalexeyenko.dto.UserDTO;
import com.epam.yalexeyenko.model.User;
import com.epam.yalexeyenko.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserConverter userConverter;

	@Override
	public UserDTO create(UserDTO userDTO) {
		if (emailExist(userDTO.getEmail())) {
			return null;
		}
		User user = userConverter.DTOToUser(userDTO);
		return userConverter.userToDTO(userRepository.saveAndFlush(user));
	}	

	@Override
	public UserDTO find(Long id) {
		return userConverter.userToDTO(userRepository.findOne(id));
	}
	
	@Override
	public UserDTO findByEmail(String email) {
		return userConverter.userToDTO(userRepository.findByEmail(email));
	}

	@Override
	public List<UserDTO> findAll() {
		return userConverter.userToDTO(userRepository.findAll());
	}

	@Override
	public void update(UserDTO userDTO) {
		userRepository.saveAndFlush(userConverter.DTOToUser(userDTO));
	}

	@Override
	public void delete(Long id) {
		userRepository.delete(id);
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public UserConverter getUserConverter() {
		return userConverter;
	}

	public void setUserConverter(UserConverter userConverter) {
		this.userConverter = userConverter;
	}

	private boolean emailExist(String email) {
		User user = userRepository.findByEmail(email);
		if (user != null) {
			return true;
		}
		return false;
	}

}
