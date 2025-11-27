package com.herf.service;


import com.herf.dto.RegistrationResponse;
import com.herf.dto.RegistrationRequest;

public interface RegistrationService {
    RegistrationResponse registerUser(RegistrationRequest request);
}
