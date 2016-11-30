package mainEntry;

import mainEntry.model.User;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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
}
