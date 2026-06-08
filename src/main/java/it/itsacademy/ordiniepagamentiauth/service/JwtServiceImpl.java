package it.itsacademy.ordiniepagamentiauth.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import it.itsacademy.ordiniepagamentiauth.dto.JwtToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.Date;
import java.util.LinkedHashMap;

@Service @Transactional
public class JwtServiceImpl implements JwtService {
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration}")
    private long jwtExpirationMillis;

    @Override
    public JwtToken generateToken(String username) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        String jwt = Jwts.builder()
                .claims(new LinkedHashMap<>())
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMillis))
                .signWith(key)
                .compact();

        return new JwtToken(jwt);
    }
}
