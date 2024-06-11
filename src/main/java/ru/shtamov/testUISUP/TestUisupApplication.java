package ru.shtamov.testUISUP;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.shtamov.testUISUP.domain.enums.Level;

@SpringBootApplication
public class TestUisupApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestUisupApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
