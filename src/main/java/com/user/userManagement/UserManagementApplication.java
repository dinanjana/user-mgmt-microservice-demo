package com.user.userManagement;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.user.userManagement.beans")
public class UserManagementApplication implements CommandLineRunner{

	@Autowired
	DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Our DataSource is = " + dataSource);
	}

}
