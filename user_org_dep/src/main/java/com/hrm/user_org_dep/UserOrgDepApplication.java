package com.hrm.user_org_dep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@SpringBootApplication
@EnableFeignClients
public class UserOrgDepApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserOrgDepApplication.class, args);

	}
}
