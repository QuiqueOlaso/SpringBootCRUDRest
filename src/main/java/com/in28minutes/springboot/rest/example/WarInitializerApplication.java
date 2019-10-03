package com.in28minutes.springboot.rest.example;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class WarInitializerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		// startH2Server();
		SpringApplication sa = new SpringApplication(WarInitializerApplication.class);
		sa.run(args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		// startH2Server();
		return application.sources(WarInitializerApplication.class);
	}

	@RestController
	public static class WarInitializerController {

		@GetMapping("/")
		public String handler(Model model) {
			model.addAttribute("date", LocalDateTime.now());
			return "WarInitializerApplication is up and running!";
		}
	}

	/*
	 * private static void startH2Server() { try { Server h2Server =
	 * Server.createTcpServer().start(); if (h2Server.isRunning(true)) {
	 * log.info("H2 server was started and is running."); } else { throw new
	 * RuntimeException("Could not start H2 server."); } } catch (SQLException e) {
	 * throw new RuntimeException("Failed to start H2 server: ", e); } }
	 */

}
