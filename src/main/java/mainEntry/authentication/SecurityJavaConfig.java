package mainEntry.authentication;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@Configuration
@EnableWebSecurity
@CrossOrigin(origins = "http://localhost:8081")
@ComponentScan("mainEntry.authentication")
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Autowired
	private MySavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler;

	// @Override
	// protected void configure(AuthenticationManagerBuilder auth)
	// throws Exception {
	// auth.inMemoryAuthentication().withUser("temporary")
	// .password("temporary").roles("ADMIN").and().withUser("user")
	// .password("userPass").roles("USER");
	// }

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth)
			throws Exception {


		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery(
						"select email ,password, true from user where email=?")
				.authoritiesByUsernameQuery(
						"select user.email, role.name from user,role where email=? and role.id = user.role");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.csrf().disable().exceptionHandling()
		// .authenticationEntryPoint(restAuthenticationEntryPoint).and()
		// .authorizeRequests().antMatchers("/getAllCommentsForUser")
		// .authenticated().and().formLogin()
		// .successHandler(authenticationSuccessHandler)
		// .failureHandler(new SimpleUrlAuthenticationFailureHandler())
		// .and().logout();
		
		http.httpBasic()
		.and().authorizeRequests()
//			   .antMatchers("/login").permitAll()
			   .antMatchers("/logout").authenticated()
				.antMatchers("/getAllCommentsForUser").authenticated()
				.and()
				.formLogin().successHandler(authenticationSuccessHandler)
				.failureHandler(new SimpleUrlAuthenticationFailureHandler())
				.and().logout().and().csrf().disable();

	}

	@Bean
	public MySavedRequestAwareAuthenticationSuccessHandler mySuccessHandler() {
		return new MySavedRequestAwareAuthenticationSuccessHandler();
	}

	@Bean
	public SimpleUrlAuthenticationFailureHandler myFailureHandler() {
		return new SimpleUrlAuthenticationFailureHandler();
	}

	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		driverManagerDataSource
				.setUrl("jdbc:mysql://localhost:3306/commentsDB");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("root");
		return driverManagerDataSource;
	}
}