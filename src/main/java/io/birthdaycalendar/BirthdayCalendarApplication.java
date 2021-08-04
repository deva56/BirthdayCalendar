package io.birthdaycalendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import io.birthdaycalendar.config.SwaggerConfiguration;

@SpringBootApplication
@Import(SwaggerConfiguration.class)
public class BirthdayCalendarApplication {

	public static void main(String[] args) {
		SpringApplication.run(BirthdayCalendarApplication.class, args);
	}
}
