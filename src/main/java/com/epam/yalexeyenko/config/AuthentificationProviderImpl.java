package com.epam.yalexeyenko.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.yalexeyenko.model.Role;
import com.epam.yalexeyenko.model.User;
import com.epam.yalexeyenko.repository.UserRepository;
import com.epam.yalexeyenko.service.UserService;

@Service("authentificationProvider")
@Transactional
public class AuthentificationProviderImpl implements AuthentificationProvider {

	@Autowired
	private UserService userServiceImpl;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user != null) {
			List<GrantedAuthority> authorities = new ArrayList<>();
			for (Role role : user.getRoles()) {
				authorities.add(new SimpleGrantedAuthority(role.getName()));
			}
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
		}
		throw new UsernameNotFoundException("Email '" + email + "' not found,");
	}
	
	public UserService getUserServiceImpl() {
		return userServiceImpl;
	}

	public void setUserServiceImpl(UserService userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

}
