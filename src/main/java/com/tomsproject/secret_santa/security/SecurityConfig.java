package com.tomsproject.secret_santa.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final UserDetailsService userDetailsService;
    final PasswordEncoder passwordEncoder;


    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    //set up a password encoder - BCrypt

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManagerBean());
        //change default authentication login url
        authenticationFilter.setFilterProcessesUrl("/api/login");

        //check if front end and back end have the same port and cors header
        http.cors();
        //we are using a token authorization - there is no cookies csrf safe
        http.csrf().disable().authorizeHttpRequests();
        // we are using a token authorization - There is no reason for creating a session,
        //session is out token timeout
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //paths witch requires authentication
        http.authorizeHttpRequests().antMatchers(HttpMethod.GET,"/api/getAdmin/**").authenticated();
        http.authorizeHttpRequests().antMatchers(HttpMethod.POST,"/api/game/**").authenticated();
        http.authorizeHttpRequests().antMatchers(HttpMethod.POST,"/api/user/create/**").authenticated();

        //there is no need to authentication - everybody can sign and can open a token message to lottery participants
        http.authorizeHttpRequests().antMatchers(HttpMethod.GET,"/api/admin/**").permitAll();
         //add  authenticationFilter - allow to authentication  in AuthenticationFilter class

        http.addFilter(authenticationFilter);
        //add authorization as UsernamePasswordAuthenticationFilter (implementing in  AuthorizationFilter)
        http.addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    }



    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
