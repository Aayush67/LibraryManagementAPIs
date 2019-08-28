package com.aayush.libraryManagementAPIs;

import com.aayush.libraryManagementAPIs.model.entity.Role;
import com.aayush.libraryManagementAPIs.model.entity.UserDetail;
import com.aayush.libraryManagementAPIs.model.entity.UserRegistration;
import com.aayush.libraryManagementAPIs.service.AccountService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@SpringBootApplication
public class LibraryManagementApIsApplication {
	private final static Logger LOGGER = Logger.getLogger(LibraryManagementApIsApplication.class.getName());


	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(LibraryManagementApIsApplication.class, args);
		AccountService accountService = applicationContext.getBean(AccountService.class);
		Optional<UserRegistration> user = accountService.findByEmail("caayush96@gmail.com");
		if(!user.isPresent()) {
			UserRegistration adminUser = new UserRegistration();
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			adminUser.setPassword(passwordEncoder.encode("aayush"));
			adminUser.setEmail("caayush96@gmail.com");
//			adminUser.setAge(24);
//			adminUser.setName("Aayush");
			Role newRole = new Role();
//			newRole.setRoleId(1);
			newRole.setRole("ADMIN");
			Set<Role> setRole = new HashSet<Role>();
			setRole.add(newRole);
			adminUser.setRoles(setRole);
			UserDetail userDetail = new UserDetail();
			userDetail.setUsername("Aayush");
			userDetail.setAddress("Sector 49");
			userDetail.setAge("24");
			userDetail.setPhone("7863526736");
			adminUser.setUserDetail(userDetail);
			accountService.addAdminAccount(adminUser);
			LOGGER.warning("Admin User Created");
		}
		else
		{
			LOGGER.warning("User Already Exists");
		}

	}

}
