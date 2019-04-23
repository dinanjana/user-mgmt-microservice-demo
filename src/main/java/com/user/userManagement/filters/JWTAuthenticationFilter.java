package com.user.userManagement.filters;

import static com.user.userManagement.Constants.EXPIRATION_TIME;
import static com.user.userManagement.Constants.HEADER_STRING;
import static com.user.userManagement.Constants.TOKEN_PREFIX;
import static com.user.userManagement.Constants.SECRET;
import static com.user.userManagement.Constants.ROLE;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.userManagement.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by dinanjanag on 4/21/19.
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UserDetailsService userDetailsService;

    private AuthenticationManager authenticationManager;

    private Logger logger0 = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            System.out.println("Came here ......");
            User creds = new ObjectMapper().readValue(req.getInputStream(), User.class);
            UserDetails userDetails = userDetailsService.loadUserByUsername(creds.getUserName());
            logger0.info("Attempting authentication for {}", creds.getUserName());
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, creds.getPassword(), userDetails.getAuthorities());
            return authenticationManager.authenticate(token);
        } catch (IOException e) {
            logger0.error("Failed to authenticate", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {

        String token = Jwts.builder()
            .setSubject(((UserDetails) auth.getPrincipal()).getUsername())
            .claim(ROLE, ((UserDetails) auth.getPrincipal()).getAuthorities())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact();

        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }

}
