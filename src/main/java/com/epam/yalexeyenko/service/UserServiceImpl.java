package com.epam.yalexeyenko.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.yalexeyenko.controller.NewsController;
import com.epam.yalexeyenko.converter.UserConverter;
import com.epam.yalexeyenko.dto.UserDTO;
import com.epam.yalexeyenko.model.User;
import com.epam.yalexeyenko.repository.RoleRepository;
import com.epam.yalexeyenko.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserConverter userConverter;

	@Override
	public UserDTO create(UserDTO userDTO) {
		if (emailExist(userDTO.getEmail())) {
			return null;
		}
		User user = userConverter.DTOToUser(userDTO);
		user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
		return userConverter.userToDTO(userRepository.saveAndFlush(user));
	}	

	@Override
	public UserDTO find(Long id) {
		return userConverter.userToDTO(userRepository.findOne(id));
	}
	
	@Override
	public void updateUserStatus(boolean enabled, Long id) {
		userRepository.updateUserStatus(enabled, id);		
	}
	
	@Override
	public UserDTO findByEmail(String email) {
		return userConverter.userToDTO(userRepository.findByEmail(email));
	}
	
	@Override
	public Page<UserDTO> findAll(Pageable pageable) {
		Page<User> pageUsers = userRepository.findAll(pageable);
		return new PageImpl<UserDTO>(userConverter.userToDTO(pageUsers.getContent()), pageable, pageUsers.getTotalElements());
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

	public RoleRepository getRoleRepository() {
		return roleRepository;
	}

	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

}
