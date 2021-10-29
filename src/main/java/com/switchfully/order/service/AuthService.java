package com.switchfully.order.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.switchfully.order.model.entity.user.User;
import com.switchfully.order.repository.UserRepository;
import com.switchfully.order.security.jwt.JwtUtils;
import com.switchfully.order.security.request.LoginRequest;
import com.switchfully.order.security.response.JwtResponse;
import com.switchfully.order.security.userdetails.UserDetailsImpl;
import com.switchfully.order.utils.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtUtils jwtUtils;

    public ResponseEntity<?> login(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok( new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername()));
    }

    public User convertToDTO(User user) {
        return objectMapper.convertValue(user,User.class);
    }

    public User convertToEntity(User user) {
        return objectMapper.convertValue(user, User.class);
    }
}
