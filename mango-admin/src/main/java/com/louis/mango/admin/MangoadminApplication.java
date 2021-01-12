package com.louis.mango.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan(value = "com.louis.mango.admin.dao")
@SpringBootApplication(scanBasePackages = {"com.louis.mango"})
public class MangoadminApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangoadminApplication.class, args);
	}

}
