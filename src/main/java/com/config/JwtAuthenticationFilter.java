package com.config;

import java.io.IOException;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private UserDetailsService userDetail;
	
	@Autowired
	private JwtUtils jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String requestTokenHeader = request.getHeader("Authorization");
		System.out.println("Token Header JwtAuthenticationFilter -> "+requestTokenHeader);
		String username = null;
		String jwtToken = null;
		if(requestTokenHeader != null) {//requestTokenHeader.startsWith("Bearer ")
			//valid
//			jwtToken = requestTokenHeader.substring(7);//use when Bearer is header
			jwtToken = requestTokenHeader;
			try {
				username = this.jwtUtil.extractUsername(jwtToken);
			}catch(ExpiredJwtException e) {
				e.printStackTrace();
				System.out.println("Expired JWT Token JwtAuthenticationFilter");
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("Error JwtAuthenticationFilter");
			}
		}else {
			System.out.println("Token Invalid JwtAuthenticationFilter");
		}
		System.out.println("Email -> "+username);
		//validate
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			final UserDetails user = this.userDetail.loadUserByUsername(username);
			if(this.jwtUtil.validateToken(jwtToken, user)) {
				//token valid
				UsernamePasswordAuthenticationToken usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
				usernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
			}
		}else {
			System.out.println("Token is not valid JwtAuthenticationFilter");
		}
		filterChain.doFilter(request, response);
		
	}

}
