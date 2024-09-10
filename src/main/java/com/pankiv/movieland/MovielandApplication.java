package com.pankiv.movieland;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.pankiv.movieland")
public class MovielandApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovielandApplication.class, args);
	}

}
