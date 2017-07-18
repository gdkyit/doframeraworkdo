package com.gdky.restful.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gdky.restful.security.AuthenticationTokenFilter;
import com.gdky.restful.security.CustomAuthenticationProvider;
import com.gdky.restful.security.EntryPointUnauthorizedHandler;
import com.gdky.restful.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private EntryPointUnauthorizedHandler unauthorizedHandler;
	
    @Autowired
    private CustomUserDetailsService userDetailsService;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		// All of Spring Security will ignore the requests
				.antMatchers("/resources/**").antMatchers(HttpMethod.POST, "/");
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	 http
     	.csrf().disable()
     	.headers().cacheControl().disable().and()
        .servletApi().and()
     	.exceptionHandling().authenticationEntryPoint(this.unauthorizedHandler).and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
     	.authorizeRequests()
     	//allow all static resources
     	.antMatchers("/").permitAll()
        .antMatchers("/**/*.ico").permitAll()
        .antMatchers("/**/*.css").permitAll()
        .antMatchers("/**/*.js").permitAll()
        .antMatchers("/**/*.dpkg").permitAll()
        //身份验证api允许匿名访问
        .antMatchers(HttpMethod.POST,"/api/auth/**").permitAll() 
        .anyRequest().authenticated().and()
        .formLogin()
        .loginPage("/gov.hygs.htgl.login.Login.d")
        .failureUrl("/gov.hygs.htgl.login.Login.d?error=true" )
        .usernameParameter("username")
        .passwordParameter("password")
        .loginProcessingUrl("/j_spring_security_check" )
        .defaultSuccessUrl("/gov.hygs.htgl.view.Main.d").and()
        .logout()
        .logoutUrl("/j_spring_security_logout" )
        .logoutSuccessUrl("/gov.hygs.htgl.login.Login.d")
        .invalidateHttpSession(true)
        .and()
        // Custom JWT based authentication
        .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

    @Autowired
	 @Override
	 public void configure(AuthenticationManagerBuilder auth)
	 throws Exception {

	 auth.authenticationProvider(customAuthenticationProvider());
	
	 }
    
    @Bean
	public CustomAuthenticationProvider customAuthenticationProvider() {
		CustomAuthenticationProvider provider = new CustomAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
    }
    
    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
      AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
      authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
      return authenticationTokenFilter;
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