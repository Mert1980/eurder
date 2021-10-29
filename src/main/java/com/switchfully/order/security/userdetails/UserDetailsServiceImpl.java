package com.switchfully.order.security.userdetails;

import com.switchfully.order.model.entity.user.User;
import com.switchfully.order.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepository.getCustomerByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", email)));

		return UserDetailsImpl.build(user);
	}
}
