package com.timesete.projeto5.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class JWTService {

    @Autowired
    private JwtEncoder encoder;

    @Autowired
    private JwtDecoder decoder;

    public String generateToken(Authentication authentication, String id) {
        Instant now = Instant.now();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .id(id)
                .subject(authentication.getName())
                .claim("scope", scope)
                .issuedAt(now)
                .expiresAt(now.plus(2, ChronoUnit.HOURS))
                .build();

        var encodedParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS512).build(), claims);
        return this.encoder.encode(encodedParameters).getTokenValue();
    }

    public String getTokenId(String token) {
        Jwt decodedToken = decoder.decode(token.substring(7));
        String id = decodedToken.getClaim("jti");
        return id;
    }

    public String getSubject(String token) {
        Jwt decodedToken = decoder.decode(token.substring(7));
        String subject = decodedToken.getSubject();
        return subject;
    }

    public Instant getExpirationDate(String token) {
        Jwt decodedToken = decoder.decode(token.substring(7));
        return decodedToken.getExpiresAt();
    }

    public Boolean isValidToken(String token) throws Exception {
        return validateExpirationDate(token);
    }

    private Boolean validateExpirationDate(String token) {
        Jwt decodedToken = decoder.decode(token.substring(7));
        Instant expirationDate = decodedToken.getExpiresAt();
        Instant now = Instant.now();
        return now.isBefore(expirationDate);
    }
}
