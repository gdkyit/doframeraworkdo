package com.gdky.restful.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gdky.restful.api.CustomAuthenticationProvider;
import com.gdky.restful.security.EntryPointUnauthorizedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private EntryPointUnauthorizedHandler unauthorizedHandler;
	
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                // All of Spring Security will ignore the requests
                .antMatchers("/resources/**")
                .antMatchers(HttpMethod.POST, "/login");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
        	.csrf().disable()
	        	.headers().cacheControl().disable().and()
	            .servletApi().and()
	        	//.exceptionHandling().authenticationEntryPoint(this.unauthorizedHandler).and()
	           // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	            .authorizeRequests()
	            	//.antMatchers("/").permitAll()
	                .antMatchers("/**/*.ico").permitAll()
	                .antMatchers("/**/*.dpkg").permitAll()
	                .antMatchers("/**/*.css").permitAll()
	                .antMatchers("/**/*.js").permitAll()
	                .antMatchers("/*").hasRole("USER")
                    //.antMatchers("/", "/home").permitAll()
	                .anyRequest().authenticated()  
                    //.anyRequest().access("hasRole('ROLE_USER')")
                    .and()
                .formLogin()
                    .loginPage("/com.gdky.restful.view.Login.d")
                    .failureForwardUrl("/com.gdky.restful.view.Login.d?error=true")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .loginProcessingUrl("/j_spring_security_check")
                    .defaultSuccessUrl("/com.gdky.restful.view.Main.d")
                    .permitAll()
                    .and()
                .logout()
                	.logoutSuccessUrl("/com.gdky.restful.view.Login.d")
                	.logoutUrl("/j_spring_security_logout")
                	.invalidateHttpSession(true)
                	.permitAll();
    }
    
    @Autowired
    @Override
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }    
    

    @Bean
    public PlaintextPasswordEncoder plainTextPasswordEncoder() {
        return new PlaintextPasswordEncoder();
    }
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
