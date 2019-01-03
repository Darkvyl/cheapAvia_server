package com.darkvyl.cheapavia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CheapaviaApplication {

	public static final String TOKEN = "6f7315a085d721dc916ccc592eec2aba";

	public static void main(String[] args) {
		SpringApplication.run(CheapaviaApplication.class, args);
	}

}

