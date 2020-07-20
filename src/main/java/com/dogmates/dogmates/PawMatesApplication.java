package com.dogmates.dogmates;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

@RestController
@Slf4j
@SpringBootApplication
public class PawMatesApplication {

	@RequestMapping("/")
	public String home() {
		return "Hello Docker";
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(PawMatesApplication.class);
		Environment env = app.run(args).getEnvironment();
		val protocol = "http";

		log.info(
				"\n------------------------------------------------\n\t" +
						"Application: '{}' is running! Access URLs:\n\t" +
						"Local-server: \t\t{}://localhost:{}\n\t",
					env.getProperty("spring.application.name"),
					protocol,
					env.getProperty("server.port")
		);
	}

	// Fix the CORS errors
	@Bean
	public FilterRegistrationBean simpleCorsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		// *** URL below needs to match the Vue client URL and port ***
		config.setAllowedOrigins(Collections.singletonList("http://localhost:8080"));
		config.setAllowedMethods(Collections.singletonList("*"));
		config.setAllowedHeaders(Collections.singletonList("*"));
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
}
