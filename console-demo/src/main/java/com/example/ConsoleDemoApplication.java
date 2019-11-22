package com.example;

import com.example.jpademo.bd.BDProduct;
import com.example.jpademo.bd.BDUser;
import com.example.jpademo.parser.manager.ParsManager;
import com.example.jpademo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsoleDemoApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;
	@Autowired
	private ParsManager parsManager;

	public static void main(String[] args) {
		SpringApplication.run(ConsoleDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String name = "valaki";
		BDUser bdUser = new BDUser();
		bdUser.setName(name);

		for (int i = 0; i < 10; i++) {
			bdUser.setEmail(name + i + "@example.com");
			userService.addUser(bdUser);
		}

		System.out.println("Users: " + userService.getAllUsers());

		System.out.println("Users with name: " + userService.getUserByName(name));

		String path = "valami";
		parsManager.write(bdUser, path);
		BDUser u = parsManager.parseUser(path);

		System.out.println("================*************=================");
		System.out.println(u);

		parsManager.write2(bdUser, path);
		u = parsManager.parseUser2(path);

		System.out.println("================*************=================");
		System.out.println("================*************=================");
		System.out.println(u);

		BDProduct product = new BDProduct();
		product.setName("Valaki22");
		product.setEmail("valaki22@example.com");
		String path2 = "valami2";
		parsManager.write(product, path2);
		BDProduct p = parsManager.parseProduct(path2);

		System.out.println("================*************=================");
		System.out.println("================*************=================");
		System.out.println(p);
	}
}
