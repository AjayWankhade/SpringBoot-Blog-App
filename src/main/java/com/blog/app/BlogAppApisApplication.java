package com.blog.app;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blog.app.entities.Role;
import com.blog.app.repository.RoleRepo;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("xyz"));

		try {
			// Creating roles
			Role role = new Role();
			role.setId(151);
			role.setName("ADMIN_USER");

			Role role1 = new Role();
			role1.setId(152);
			role1.setName("NORMAL_USER");

			// Creating list of roles
			List<Role> roles = List.of(role, role1);

			// Saving roles to the database
			List<Role> results = this.roleRepo.saveAll(roles);

			// Printing saved roles
			results.forEach(r -> System.out.println(r.getName()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
