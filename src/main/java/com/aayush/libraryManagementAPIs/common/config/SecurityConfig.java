package com.aayush.libraryManagementAPIs.common.config;

//import com.aayush.libraryManagementAPIs.filter.CorsFilter;

import com.aayush.libraryManagementAPIs.common.constants.Constants;
import com.aayush.libraryManagementAPIs.common.filter.RequestBodyReaderAuthentication;
import com.aayush.libraryManagementAPIs.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@EnableGlobalMethodSecurity(prePostEnabled=true)
@Configuration //Enables Spring configuration
@EnableWebSecurity //It indicates that this configuration file has spring security configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailServiceImpl userDetailsService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	public RequestBodyReaderAuthentication authenticationFilter() throws Exception {
		RequestBodyReaderAuthentication authenticationFilter
				= new RequestBodyReaderAuthentication();
//		authenticationFilter.setAuthenticationSuccessHandler(this::loginSuccessHandler);
//		authenticationFilter.setAuthenticationFailureHandler(this::loginFailureHandler);
		authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/account/login", "POST"));
		authenticationFilter.setAuthenticationManager(authenticationManagerBean());
		return authenticationFilter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub

        //HttpMethod.OPTIONS Enables preflight response check whether access is allowed or not
        //Before sending actual request a preflight request is sent to check if we have right permissions
		http
                .csrf().disable()
				.authorizeRequests()
				.antMatchers("/account/register","/account/register/**","/account/**","/home", "/book/**")
				.permitAll()
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.antMatchers(Constants.SWAGGER_URL_PATTERN).permitAll()
				.and()
				.authorizeRequests()
				.anyRequest().fullyAuthenticated()
				.and()
				.addFilterBefore(
                authenticationFilter(),
                UsernamePasswordAuthenticationFilter.class)
				.logout();
	}

	//Configures Cors globally
	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedHeader("*");
		configuration.addAllowedOrigin("*");
//		configuration.setAllowedHeaders("*");
//		configuration.setAllowedOrigins(Arrays.asList("*"));
//		configuration.setAllowCredentials(true);
		configuration.setAllowedMethods(Arrays.asList("OPTIONS", "GET", "POST", "PUT", "DELETE", "PATCH"));
//		source.registerCorsConfiguration("/**", configuration);
		source.registerCorsConfiguration("/**", configuration);
		return new CorsFilter(source);
	}

/*	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs",
				"/configuration/ui",
				"/swagger-resources/**",
				"/configuration/security",
				"/swagger-ui.html",
				"/webjars/**");
	}*/
}
