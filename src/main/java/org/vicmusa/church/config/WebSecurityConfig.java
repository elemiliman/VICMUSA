package org.vicmusa.church.config;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Log log = LogFactory.getLog(WebSecurityConfig.class);
	
	@Value("${rememberMe.privateKey}")
	private String rememberMeKey;
	
	@Value("${spring.profiles.active}")
	private String env;
	
 	@Resource
	private UserDetailsService userService;
 	
 	@Bean
 	public RememberMeServices rememberMeServices(){
 		TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices(rememberMeKey, userService);
 		return rememberMeServices;
 	}
 	
 	@Bean
 	public PasswordEncoder passwordEncoder(){
 		log.info("Created password encoder bean");
 		return new BCryptPasswordEncoder();
 	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .mvcMatchers("/",
                		"/home",
                		"/error",
                		"/register",
                		"/forgot-password",
                		"/reset-password/*",
                		"/donate/**",
                		"/gallery/**",
                		"/sermons/**",
                		"/about-us/**",
                		"/blogs/**",
                		"/events/**",
                		"/public/**").permitAll()
                .mvcMatchers("/users/all").hasRole("ADMIN")
                .anyRequest().authenticated();
        http
            .formLogin()
                .loginPage("/login")
                .permitAll().and()
            .rememberMe().key(rememberMeKey).rememberMeServices(rememberMeServices()).and()
            .logout()
                .permitAll();
        
//        if (!env.equals("dev"))
//        	http.requiresChannel().anyRequest().requiresSecure();
    }
}
