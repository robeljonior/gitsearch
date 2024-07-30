package com.example.serchrepo.Config;

import com.example.serchrepo.Model.Userr;
import com.example.serchrepo.Repository.UserrRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class   JwtService {
    private static final String SECRET_KEY ="404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    private final UserrRepo userrRepo;



    public String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()*1000*60*24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public String generaterefreshtoken(Map<String, Object > extraclame, UserDetails userDetails){
        return Jwts.builder().setClaims(extraclame).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+604800000))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }
    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build().parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = base64UrlDecode();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private byte[] base64UrlDecode() {
        // Replace '_' with '/' and '-' with '+' to convert to standard base64
        String base64EncodedString = JwtService.SECRET_KEY
                .replace('_', '/')
                .replace('-', '+');

        // Add padding if necessary
        int padding = (4 - base64EncodedString.length() % 4) % 4;
        base64EncodedString += "=".repeat(padding);

        // Decode the base64 string
        return Base64.getDecoder().decode(base64EncodedString);
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        Userr userr = userrRepo.findByEmail(username).orElse(null);
        return (userr.getUsername().equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());

    }

}
