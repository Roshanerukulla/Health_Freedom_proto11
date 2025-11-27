package com.herf.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.herf.dto.RegistrationRequest;
import com.herf.dto.RegistrationResponse;
import com.herf.entity.User;
import com.herf.repository.UserRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegistrationResponse registerUser(RegistrationRequest request) {

        // ✅ Check if username already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }

        // ✅ Check if email already registered
        if (userRepository.existsByEmailId(request.getEmailId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered");
        }

        // ✅ Map request → entity
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmailId(request.getEmailId());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            sdf.setLenient(false);
            user.setDob(sdf.parse(request.getDob()));
        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid DOB format. Use MM/dd/yyyy");
        }

        // ✅ Save user to DB
        User saved = userRepository.save(user);

        // ✅ Build response DTO
        RegistrationResponse response = new RegistrationResponse();
        response.setId(saved.getId());
        response.setUsername(saved.getUsername());
        response.setEmailId(saved.getEmailId());
        response.setMessage("Registration successful!");

        return response;
    }
}
