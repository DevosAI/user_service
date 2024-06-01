package com.devos.user_service.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JWTUtils {

    private  static  final Logger logger= LoggerFactory.getLogger(JWTUtils.class);

    @Value("${spring.app.jwtSecret}")
    private String jwtSecret;

    @Value("${spring.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String getJwtFromHeader(HttpServletRequest request) {
        String beararToken = request.getHeader("Authorization");
        logger.info("Bearar token: " + beararToken);
        if(beararToken != null && beararToken.startsWith("Bearer ")) {
            return beararToken.substring(7);
        }
        return null;
    }

    public String generateTokenFromUsername(UserDetails userDetails) {
        String username =userDetails.getUsername();
        logger.info(username);
        //logger.info(new Date((new Date()).getTime()+ jwtExpirationMs).toString());
        logger.info("Testing");
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime()+ jwtExpirationMs) )
                .signWith(key())
                .compact();
    }


    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build().parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    private Key key(){
        logger.info("Creating key");
        logger.info(jwtSecret);
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public boolean validateToken(String authToken){
        try {
            System.out.println("Validate");
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parseSignedClaims(authToken);
            return true;
        }catch(MalformedJwtException e){
            logger.error("MalformedJwtException",e);
        }catch (ExpiredJwtException e){
            logger.error("ExpiredJwtException",e);
        }catch (UnsupportedJwtException e){
            logger.error("UnsupportedJwtException",e);
        }catch (IllegalArgumentException e){
            logger.error("Invalid JWT");
            logger.error(e.getMessage());
        }
        return false;
    }
}
