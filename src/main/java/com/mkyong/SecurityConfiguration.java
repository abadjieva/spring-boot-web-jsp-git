package com.mkyong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mkyong.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//	
//	@Autowired
//	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("USER");
//		auth.inMemoryAuthentication().withUser("admin").password("root123").roles("ADMIN");
//		auth.inMemoryAuthentication().withUser("dba").password("root123").roles("ADMIN","DBA");
//	}
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	 
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
	  throws Exception {
	    auth.authenticationProvider(authenticationProvider());
	System.out.println("***************************KONFIGURE");
	    //auth.userDetailsService(userDetailsService);
	}
	 
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider
	      = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
	    authProvider.setPasswordEncoder(encoder());
	    return authProvider;
	}
	 
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
		//return (PasswordEncoder) new Md5PasswordEncoder();
		
	}
	
	//@Override
//	   protected void configure(HttpSecurity http) throws Exception {
//	        http.authorizeRequests().antMatchers("/noSecurity").permitAll()
//	            .anyRequest().authenticated()
//	            .and().formLogin().loginPage("/login").permitAll()
//	            .and().logout().permitAll();
//	    }
//	 
	
	   @Override
	   protected void configure(HttpSecurity http) throws Exception {
	  //.access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
	  http.authorizeRequests()
	  	.antMatchers("/", "/home").permitAll()
	  	.and().formLogin().loginPage("/login")
	  	.usernameParameter("ssoId").passwordParameter("password")
	  	.and().exceptionHandling().accessDeniedPage("/Access_Denied");
	}
}
