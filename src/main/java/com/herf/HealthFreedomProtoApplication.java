package com.herf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 👇 Add this exclude
@SpringBootApplication(exclude = {
    org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class HealthFreedomProtoApplication {
    public static void main(String[] args) {
        SpringApplication.run(HealthFreedomProtoApplication.class, args);
    }
}
