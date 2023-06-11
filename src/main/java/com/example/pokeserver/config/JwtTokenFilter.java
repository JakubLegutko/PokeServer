package com.example.pokeserver.config;

import com.example.pokeserver.business.TokenService;
import com.example.pokeserver.data.User;
import com.example.pokeserver.util.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
        private final TokenService tokenService;

        public JwtTokenFilter(TokenService tokenService) {
                this.tokenService = tokenService;
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request,
                                        HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {

                if (!hasAuthorizationBearer(request)) {
                        filterChain.doFilter(request, response);
                        return;
                }

                String token = getAccessToken(request);

                if (!tokenService.validateAccessToken(token)) {
                        filterChain.doFilter(request, response);
                        return;
                }

                setAuthenticationContext(token, request);
                filterChain.doFilter(request, response);
        }

        private boolean hasAuthorizationBearer(HttpServletRequest request) {
                String header = request.getHeader("Authorization");
                if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
                        return false;
                }

                return true;
        }

        private String getAccessToken(HttpServletRequest request) {
                String header = request.getHeader("Authorization");
                String token = header.split(" ")[1].trim();
                return token;
        }

        private void setAuthenticationContext(String token, HttpServletRequest request) {
                UserDetails userDetails = getUserDetails(token);

                UsernamePasswordAuthenticationToken
                        authentication = new UsernamePasswordAuthenticationToken(userDetails, null, null);
                var claims = tokenService.parseClaims(token);
                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
        }

//        private UserDetails getUserDetails(String token) {
//                var user = new User();
//                String jwtSubject = tokenService.getSubject(token);
//                user.setEmail(jwtSubject);
//                CustomUserDetails userDetails = new CustomUserDetails(user);
//                return userDetails;
//        }
        private UserDetails getUserDetails(String token) {
                User userDetails = new User();
                var claims = tokenService.parseClaims(token);
                String subject = (String) claims.get(Claims.SUBJECT);
                String roles = (String) claims.get("roles");

                roles = roles.replace("[", "").replace("]", "");
                String[] roleNames = roles.split(",");

                for (String aRoleName : roleNames) {
                        userDetails.addRole(new Role(aRoleName));
                }

                String[] jwtSubject = subject.split(",");

                userDetails.setId(Integer.parseInt(jwtSubject[0]));
                userDetails.setEmail(jwtSubject[1]);

                return userDetails;
        }
}