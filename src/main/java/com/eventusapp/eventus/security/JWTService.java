package com.eventusapp.eventus.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.eventusapp.eventus.models.User;
import com.eventusapp.eventus.services.EvironmentVariablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;



@Service
public class JWTService {

    @Autowired
    EvironmentVariablesService envService;


    private String secretKey =null;




    public String generateToken(User user) {
        secretKey = envService.getDotEnvSecretKey();
        String base64EncodedSecretKey= Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
        String token = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(base64EncodedSecretKey);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("userid", user.getUser_id())
                    .withClaim("role", user.getRole())
                    .withIssuedAt(new Date())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
            throw exception;
        }
        return String.format("%s "+token,"Bearer") ;

    }

    public boolean verifyToken(String token){
        secretKey = envService.getDotEnvSecretKey();
        String base64EncodedSecretKey= Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
        String rawToken = token.split(" ")[1]; // remove bearer from token
        try {
            Algorithm algorithm = Algorithm.HMAC256(base64EncodedSecretKey); //use more secure key
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(rawToken);
        } catch (JWTVerificationException exception){
            //throw exception;
            return false;
        }
        return true;
    }

        public String getRole(String token){
        secretKey = envService.getDotEnvSecretKey();
        String base64EncodedSecretKey= Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
        String rawToken = token.split(" ")[1]; // remove bearer from token
        DecodedJWT jwt = JWT.decode(rawToken);
        return jwt.getClaim("role").asString();
    }

    public String getUserId(String token){
        secretKey = envService.getDotEnvSecretKey();
        String base64EncodedSecretKey= Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
        String rawToken = token.split(" ")[1]; // remove bearer from token
        DecodedJWT jwt = JWT.decode(rawToken);
        return jwt.getClaim("userid").asString();
    }

    public Date getIssueDate(String token){
        secretKey = envService.getDotEnvSecretKey();
        String base64EncodedSecretKey= Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
        String rawToken = token.split(" ")[1]; // remove bearer from token
        DecodedJWT jwt = JWT.decode(rawToken);
        return jwt.getIssuedAt();
    }

}
