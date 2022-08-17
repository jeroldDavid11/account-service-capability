package com.serviceaccount.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.serviceaccount.security.service.CustomDetailsService;



@Configuration
// debug mode @EnableWebSecurity(debug = true)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	

	@Autowired
	   private CustomDetailsService customDetailsService;

	
	//enable just basic authetication 
	//	  @Override
	//	    public void configure(HttpSecurity http) throws Exception {
	//		 http.httpBasic().and().authorizeRequests().anyRequest().authenticated().and().csrf().disable().authorizeRequests().and().formLogin().disable().authorizeRequests(); 
	//		 }
		
	//	@Bean
	//	@Override
	//	protected UserDetailsService userDetailsService() {
	//		
	//	    List<UserDetails> users = new ArrayList<>();
	//		users.add(User.withDefaultPasswordEncoder().username("test").password("1234").roles("USER").build());
	//		return new InMemoryUserDetailsManager(users);
	//		
	//	}
	
	//enable OAUTH and jswt
	   public PasswordEncoder encoder_er() {
	      return new BCryptPasswordEncoder();
	   }
	   @Override
	   @Autowired
	   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		   // using im memory authentication
		   //auth.inMemoryAuthentication().withUser("test").password("{noop}ajsdjsadpq[[e").roles("USER");
		  auth.userDetailsService(customDetailsService).passwordEncoder(encoder_er());
	   }
	   @Override
	   protected void configure(HttpSecurity http) throws Exception {
	      http.authorizeRequests().anyRequest().authenticated().and().sessionManagement()
	         .sessionCreationPolicy(SessionCreationPolicy.NEVER);
	   }
	   
	   
	   @Override
	   public void configure(WebSecurity web) throws Exception {
	      web.ignoring().antMatchers("/h2-console/**");
	   }
	   
	   @Override
	   @Bean
	   public AuthenticationManager authenticationManagerBean() throws Exception {
	      return super.authenticationManagerBean();
	   }
}