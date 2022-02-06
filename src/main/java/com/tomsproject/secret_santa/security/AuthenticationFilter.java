package com.tomsproject.secret_santa.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.tomsproject.secret_santa.security.SecretCredentials.SECRET_TOKEN;
import static com.tomsproject.secret_santa.security.SecretCredentials.tokenTimeout;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Log4j2
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

     final AuthenticationManager authenticationManager;

    AuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager=authenticationManager;
    }



    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        log.info("Attempt authentication. User: " + request.getParameter("login"));
         log.info("Attempt authentication response. Status: " + response.getStatus()
        );
        //get login and password from request

        String adminName=request.getParameter("login");
        String password = request.getParameter("password");

        //pass to authentication manager - validate password

        UsernamePasswordAuthenticationToken authenticationToken=
                new UsernamePasswordAuthenticationToken(adminName,password);
        return authenticationManager.authenticate(authenticationToken);


    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication)  {
        //password correct
        User user= (User) authentication.getPrincipal();

        //token algorithm - keyed-hash message authentication code
        Algorithm algorithm= Algorithm.HMAC256(SECRET_TOKEN);

        //create token
        String accessToken= JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() +tokenTimeout))
                .withIssuer(request.getRequestURI())
                .withClaim("roles",
                        user.
                                getAuthorities().
                                stream().
                                map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

            //token map - covert-able to JSON
        Map<String,String> tokens= new HashMap<>();
        tokens.put("access_token",accessToken);


        response.setContentType(APPLICATION_JSON_VALUE);
        //Convert tokenMap to JSON response
        try {
            new ObjectMapper().writeValue(response.getOutputStream(),tokens);
        } catch (IOException e) {
           log.error("Error inside successfulAuthentication - " +
                   "TOKEN can not be map to StreamOutput "+ e.getMessage());

        }}

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
        log.info("Unsuccessful authentication. Login: " + request.getParameter("login") +
                ", Message:  " + failed.getMessage()
                );



}}
