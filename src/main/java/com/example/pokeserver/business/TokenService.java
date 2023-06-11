package com.example.pokeserver.business;

import com.example.pokeserver.config.RsaKeyProperties;
import com.example.pokeserver.data.User;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Service
public class TokenService {

    private final JwtEncoder encoder;
    private final JwtDecoder decoder;

    public TokenService(JwtEncoder encoder, JwtDecoder decoder) {
        this.encoder = encoder;
        this.decoder = decoder;
    }

    public String generateToken(User user) {
        var generationTimestamp = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(generationTimestamp)
                .expiresAt(generationTimestamp.plus(1, ChronoUnit.HOURS))
                .subject(user.getEmail())
                .claim("roles", user.getRoles().toString())
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public boolean validateAccessToken(String token) {
        Jwt decodedToken = decoder.decode(token);
        return decodedToken != null;
    }

    public String getSubject(String token) {
        Jwt decodedToken = decoder.decode(token);
        var subject = decodedToken.getSubject();
        return subject;
    }

    public Map<String, Object> parseClaims(String token) {
        return decoder.decode(token).getClaims();
    }
}
