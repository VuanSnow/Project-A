package com.myproject.projecta;

import com.myproject.projecta.domain.User;
import com.myproject.projecta.domain.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjectAApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectAApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository ur) {
		return (args) -> {
			User user = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			ur.save(user);
			user = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			ur.save(user);

			ur.findAll().forEach(e -> System.out.println(e));
		};
	}
}
