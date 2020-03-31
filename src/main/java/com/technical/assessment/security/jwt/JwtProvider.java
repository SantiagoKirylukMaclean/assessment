package com.technical.assessment.security.jwt;




import com.technical.assessment.security.service.UserPrinciple;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtProvider {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationOauth}")
    private int jwtExpirationOauth;

    @Value("${app.jwtExpirationClient}")
    private int jwtExpirationClient;

    public String generateJwtToken(Authentication authentication) {

        UserPrinciple sellerPrincipal = (UserPrinciple) authentication.getPrincipal();


        return Jwts.builder()
                .setSubject((sellerPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationOauth))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    /*
    public String generateTokenClient(Payment payment) {
        return Jwts.builder()
                .setSubject((payment.getSeller()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationClient))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
*/
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty -> Message: {}", e);
        }

        return false;
    }
}

