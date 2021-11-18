package com.ds.antddun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@EnableJpaAuditing
public class AntddunApplication {

	public static void main(String[] args) {
		SpringApplication.run(AntddunApplication.class, args);
	}
	@Bean(name = "${uploadPath}")
	public String uploadPath() {
		return "c:\\upload";
	}
}
