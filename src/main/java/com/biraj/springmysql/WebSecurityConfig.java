//package com.biraj.springmysql;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.context.properties.NestedConfigurationProperty;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.client.OAuth2ClientContext;
//import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
//import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
//import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.filter.CompositeFilter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.Filter;
//
//
//@Configuration
////@EnableWebSecurity
////@EnableOAuth2Client
//@RestController
//public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{
//  
// // @Autowired
//  //OAuth2ClientContext oauth2ClientContext;
//  
// /* @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    
//    /* http
//          .authorizeRequests()
//              .antMatchers("/", "/demo/adduser").permitAll()
//              .anyRequest().authenticated()
//              .and()
//          .formLogin()
//              .loginPage("/demo/login")
//              .permitAll()
//              .and()
//              .logout()
//              .permitAll()
//              .and().addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
//    
//    http
//    .authorizeRequests()
//        .antMatchers("/resources/**").permitAll() 
//        .antMatchers("/scripts/**").permitAll() 
//        .antMatchers("/webjars/**").permitAll() 
//        .antMatchers("/css/**").permitAll() 
//        .antMatchers("/github/**").permitAll() 
//        //.antMatchers("/register").permitAll()
//        .anyRequest().authenticated()
//        
//        .and()
//    .csrf().disable()
//    .formLogin()
//        .loginPage("/login")
//        .permitAll()
//        .and()
//    .logout()                                    
//        .permitAll();
//              
//              
//  }*/
//  
// /* @Autowired
//  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//      auth
//          .inMemoryAuthentication()
//              .withUser("user").password("user").roles("USER");
//  }*/
//  
//  
//}