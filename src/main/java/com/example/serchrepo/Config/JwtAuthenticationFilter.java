package com.example.serchrepo.Config;

import com.example.serchrepo.Model.Userr;
import com.example.serchrepo.Repository.UserrRepo;
import com.example.serchrepo.Service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final static String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;
    private final UserrRepo userrRepo;




    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        if (StringUtils.isEmpty(authHeader) || !org.apache.commons.lang3.StringUtils.startsWith(authHeader, "Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        username = jwtService.extractUsername(jwt);
        Userr userr = userrRepo.findByEmail(username).orElseThrow(() -> new RuntimeException("user not found"));
        if (StringUtils.isNotEmpty(userr.getUsername()) && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(userr.getUsername());

                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userr.getUsername(), userr.getPassword(), null);


                securityContext.setAuthentication(authenticationToken);
                SecurityContextHolder.setContext(securityContext);



        }
        filterChain.doFilter(request,response);

    }
}
