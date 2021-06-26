package com.aaryan11hash.chitthi;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChitthiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChitthiApplication.class, args);
	}

}
