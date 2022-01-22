package com.tomsproject.secret_santa.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
@Log4j2
public class AuthorizationFilter extends OncePerRequestFilter {


    @Value("${token.secret}")
    String tokenSecret;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       log.info("inside of Autentication method reqquest  +  " + request.getHeader("Authorization") +   "   " + "response   " + response.getStatus());

        if(request.getServletPath().equals("/api/login")){
           filterChain.doFilter(request,response);
       }
       else {
           String authorizationHeader = request.getHeader(AUTHORIZATION);

               if(authorizationHeader!=null&&authorizationHeader.startsWith("Bearer ")) {
                   try {
                       System.out.println("inside a try authorization " +request.getServletPath());

                       String token = authorizationHeader.replace("Bearer ", "");
                       Algorithm algorithm = Algorithm.HMAC256(SecretCredentials.TOKEN.getBytes());
                       JWTVerifier verifier = JWT.require(algorithm).build();
                       DecodedJWT decodedJWT = verifier.verify(token);
                       String admin = decodedJWT.getSubject();
               ////DoIt
                       //Token.user == equal authorization
                       System.out.println("authorization servletPath" + request.getServletPath());
                       if(request.getServletPath().equals("/api/getAdmin/" +admin)||
                       request.getServletPath().equals("/api/user/create") ||
                               request.getServletPath().equals("/api/game/admin_games")||
                                       request.getServletPath().equals("/api/game/user_games")

                       ){
                       List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
                       Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                       roles.forEach(role -> {
                           authorities.add(new SimpleGrantedAuthority(role));
                       });
                       UsernamePasswordAuthenticationToken authenticationToken =
                               new UsernamePasswordAuthenticationToken(admin, null, authorities);
                       SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                           System.out.println("authorization success path:" + request.getServletPath());
                       filterChain.doFilter(request, response);}
                       else throw new Exception();
                   } catch (Exception e) {
                       response.setHeader("error",e.getMessage());
                       response.sendError(301,"Authorization failed ");
                       System.out.println("authorization error path:" + request.getServletPath());
                   }
               }
               else {filterChain.doFilter(request,response);
            System.out.println("authorization faild " + request.getServletPath());}




        }
    }
}
