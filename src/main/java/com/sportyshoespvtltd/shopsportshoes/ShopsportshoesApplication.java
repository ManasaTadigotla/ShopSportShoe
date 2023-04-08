package com.sportyshoespvtltd.shopsportshoes;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopsportshoesApplication {
	 private static final Logger logger = Logger.getLogger(ShopsportshoesApplication.class.toString());
	public static void main(String[] args) {
		SpringApplication.run(ShopsportshoesApplication.class, args);
		logger.info("hello");
	}

}
