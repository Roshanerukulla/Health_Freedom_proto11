package com.herf.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.herf.dto.RegistrationRequest;
import com.herf.dto.RegistrationResponse;
import com.herf.service.RegistrationService;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private static final Logger logger = Logger.getLogger(RegistrationController.class.getName());

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public RegistrationResponse registerUser(@RequestBody RegistrationRequest registrationRequest) {
        logger.info("Received registration request: " + registrationRequest.getUsername());

        try {
            // Validate DOB format early
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            sdf.setLenient(false);
            sdf.parse(registrationRequest.getDob());
        } catch (ParseException e) {
            logger.severe("Date parsing error: " + e.getMessage());
            throw new RuntimeException("Invalid date format. Expected MM/dd/yyyy");
        }

        // Delegate actual logic to service layer
        return registrationService.registerUser(registrationRequest);
    }
}