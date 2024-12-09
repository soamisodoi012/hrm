package com.hrm.leave_mgnt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LeaveMgntApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeaveMgntApplication.class, args);
	}

}
