package com.sivalabs.springtraining;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sivalabs.springtraining.entities.User;
import com.sivalabs.springtraining.services.UserService;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) 
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = ctx.getBean(UserService.class);
		List<User> users = userService.findAll();
		for (User user : users) {
			System.out.println(user);
		}
	}

}
