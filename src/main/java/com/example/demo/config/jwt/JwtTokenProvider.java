package com.example.demo.config.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 30.
 **/
@Component
public class JwtTokenProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    // @Value("${spring.jwt.secret}")
    @Value(value = "secretKeytestauthorizationjwtmanagetokensecretKeytestauthorizationjwtmanagetokensecretKeytestauthorizationjwtmanagetokensecretKeytestauthorizationjwtmanagetoken")
    private String jwtSecret;

    @Value(value = "600000")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody();

        return Long.valueOf(claims.getSubject());
    }

    public boolean validateToken(String authToken, HttpServletRequest request) {
        // Jwt 관련 Exception 발생시 Filter에서 발생한 Exception은 Spring의 DispatcherServlet까지 닿을 수가 없기 때문에 사용자 정의 Exception을 처리할 수 없다
        // https://velog.io/@tjdals080/spring-boot-security-jwt3
        // 해결방법 1 : 에러 발생시 request의 attribute에 각 에러 정보 Set
        // 해결방법 2 : Security Config 부분에 설정한 EntryPoint → customAuthenticationEntryPoint에서 핸들링
//        SignatureException : Invalid Signature
//        MalformedJwtException : Invalid JWT
//        ExpiredJwtException : Expired JWT
//        UnsupportedJwtException : Unsupported Exception
//        IllegalArgumentException : Empty JWT Claims stirng
        try {
            // Jws<Claims> claims =  Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(authToken);
            // TODO: return !claims.getBody().getExpiration().before(new Date()); // 만료일자 확인

            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            LOGGER.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            LOGGER.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            LOGGER.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            LOGGER.error("JWT claims string is empty");
        }
        return false;
    }
}
