package com.ecommercebackend.onlineshoping_backend.api.Security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.ecommercebackend.onlineshoping_backend.Models.LocalUser;
import com.ecommercebackend.onlineshoping_backend.api.Sevices.JwtService;
import com.ecommercebackend.onlineshoping_backend.dao.LocalUserDao;
import com.mysql.cj.protocol.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    private LocalUserDao localUserDao;
    private JwtService jwtService;

    public JWTRequestFilter(LocalUserDao localUserDao, JwtService jwtService) {
        this.localUserDao = localUserDao;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
       String tokenHeader = request.getHeader("Authorization");
       if (tokenHeader !=null && tokenHeader.startsWith("Bearer ")) {
        String token = tokenHeader.substring(7);
        try {
            String userName=jwtService.getUserName(token);
            Optional<LocalUser> opuser= localUserDao.findByUsernameIgnoreCase(userName) ;
            if (opuser.isPresent()) {
                LocalUser user = opuser.get();
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,null,new ArrayList());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (JWTDecodeException ex) {
            // TODO: handle exception
        }
              
       }
        filterChain.doFilter(request, response);
    }
    
}
