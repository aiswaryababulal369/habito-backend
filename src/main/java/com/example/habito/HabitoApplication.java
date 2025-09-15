package com.example.habito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class HabitoApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(HabitoApplication.class);
		String port = System.getenv("PORT"); // Railway dynamic portS
		if (port != null && !port.isEmpty()) {
			app.setDefaultProperties(Collections.singletonMap("server.port", port));
		}
		app.run(args);

	}

}
