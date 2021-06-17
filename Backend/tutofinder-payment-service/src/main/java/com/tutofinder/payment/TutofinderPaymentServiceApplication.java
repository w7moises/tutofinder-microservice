package com.tutofinder.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class TutofinderPaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutofinderPaymentServiceApplication.class, args);
	}

}
