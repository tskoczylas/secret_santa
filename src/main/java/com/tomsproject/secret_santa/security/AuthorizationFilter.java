package com.tomsproject.secret_santa.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.log4j.Log4j2;
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

import static com.tomsproject.secret_santa.security.SecretCredentials.SECRET_TOKEN;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
@Log4j2
public class AuthorizationFilter extends OncePerRequestFilter {



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // catch REST request /api/login and send back to chainFilter
        if(request.getServletPath().equals("/api/login")){
           filterChain.doFilter(request,response);
       }


       else {
           //Catch request with header "AUTHORIZATION"
           String authorizationHeader = request.getHeader(AUTHORIZATION);

           //Header is not null and starting with string  Bearer
               if(authorizationHeader!=null&&authorizationHeader.startsWith("Bearer ")) {
                   try {

                       String token = authorizationHeader.replace("Bearer ", "");
                      //H - signature SECRET_TOKEN
                       Algorithm algorithm = Algorithm.HMAC256(SECRET_TOKEN);
                       //Verify token
                       JWTVerifier verifier = JWT.require(algorithm).build();
                       //Decode verified token
                       DecodedJWT decodedJWT = verifier.verify(token);

                       //Decode a admin from token
                       String admin = decodedJWT.getSubject();
                        //paths require  to authorization
                       if(request.getServletPath().equals("/api/getAdmin/" +admin)||
                       request.getServletPath().equals("/api/user/create") ||
                               request.getServletPath().equals("/api/game/admin_games")||
                                       request.getServletPath().equals("/api/game/user_games"))
                       {
                           //decode role and create SMA object
                       List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
                       Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                       roles.forEach(role ->
                           authorities.add(new SimpleGrantedAuthority(role))
                       );

                       //Create UPAT
                       UsernamePasswordAuthenticationToken authenticationToken =
                               new UsernamePasswordAuthenticationToken(admin, null, authorities);

                       //Set UPAT to security context
                       SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                       filterChain.doFilter(request, response);}
                       else{
                           response.sendError(301,"Authorization failed - invalid or expired Token ");
                       }

                   } catch (Exception e) {
                       response.setHeader("error",e.getMessage());
                       response.sendError(301,"Authorization failed - invalid Token format ");
                   }
               }
               else {filterChain.doFilter(request,response);




        }
    }
}}
