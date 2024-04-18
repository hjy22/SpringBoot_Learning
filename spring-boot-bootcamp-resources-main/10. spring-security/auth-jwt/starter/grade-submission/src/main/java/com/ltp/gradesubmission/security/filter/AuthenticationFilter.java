package com.ltp.gradesubmission.security.filter;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.jersey.JerseyProperties.Servlet;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.function.ServerResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltp.gradesubmission.entity.User;
import com.ltp.gradesubmission.security.SecurityConstants;
import com.ltp.gradesubmission.security.manager.CustomAuthenticationManager;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    
    private CustomAuthenticationManager authenticationManager;

    //用于尝试进行身份验证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response) throws AuthenticationException{
        try{
             //把JSON格式变成java的USER形式
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException();
        } 
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
    AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("incorrect password");
        response.getWriter().flush();
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = JWT.create()
            .withSubject(authResult.getName())
            .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION))
            .sign(Algorithm.HMAC512(SecurityConstants.SECRET_KEY));
        response.addHeader(SecurityConstants.AUTHORIZATION, SecurityConstants.BEARER+token);
    }
}
