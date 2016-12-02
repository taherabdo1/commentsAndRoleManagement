package mainEntry;

import mainEntry.model.User;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import resources.RestConfig;

/**
 * Hello world!
 * 
 */

@Configuration
@Import({ RestConfig.class, User.class })
@SpringBootApplication
@ComponentScan(basePackages = "")
public class App {
	public static void main(String[] args) {

		SpringApplication.run(App.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				System.out.println("INSIDE adding cors registery");
				registry.addMapping("/*").allowedOrigins("http://localhost:8081/app");
			}
		};
	}
}
