package com.ds.antddun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AntddunApplication {

	public static void main(String[] args) {
		SpringApplication.run(AntddunApplication.class, args);
	}

	@Bean(name = "${com.ds.upload.uploadPath}")
	public String uploadPath() {
		return "c:\\upload";
	}
}
