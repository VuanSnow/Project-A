package com.myproject.projecta;

import com.myproject.projecta.domain.Message;
import com.myproject.projecta.domain.MessageRepository;
import com.myproject.projecta.domain.User;
import com.myproject.projecta.domain.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ProjectAApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectAApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository ur, MessageRepository mr) {
		return (args) -> {
			User user = new User("user", "Mike the user","$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER","mike@user.com");
			ur.save(user);
			user = new User("admin", "Minnie the admin","$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN","minnie@admin.com");
			ur.save(user);

			ur.findAll().forEach(e -> System.out.println(e));

			List<Message> testMessages = new ArrayList<Message>(){{
				add(new Message("Monkey", "Caesar Cipher", "npolfz", true, ur.findByUsername("user")));
				add(new Message("Pig", "Caesar Cipher", "qjh", true, ur.findByUsername("user")));
				add(new Message("Bunny", "Caesar Cipher", "cvooz", true, ur.findByUsername("admin")));
				add(new Message("Food", "Morse Code", "..-. --- --- -.. ", true, ur.findByUsername("user")));
				add(new Message("Bunny", "Morse Code", "-... ..- -. -. -.-- ", true, ur.findByUsername("admin")));
			}};
			testMessages.forEach(p -> mr.save(p));

		};
	}
}
