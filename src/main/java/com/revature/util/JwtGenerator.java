package com.revature.util;

import com.revature.models.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtGenerator {

    public static String createJwt(User subject) {

        SignatureAlgorithm sigAlg = SignatureAlgorithm.HS512;
        long now = System.currentTimeMillis();

        JwtBuilder builder = Jwts.builder()
                                .setId(Integer.toString(subject.getUserId()))
                                .setSubject(subject.getUsername())
                                .setIssuer("revature")
                                .claim("role", subject.getUserRole())
                                .setExpiration(new Date(now + 300000))
                                .signWith(sigAlg, "super-secret-key");

        return builder.compact();

    }
}
