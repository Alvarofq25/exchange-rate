package com.datos.exchange.rate.controller;

import com.datos.exchange.rate.model.AuthRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public Map<String, String> authenticate(@RequestBody AuthRequest authRequest) {
        String jwt = Jwts.builder()
                .setSubject(authRequest.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 día
                .signWith(SignatureAlgorithm.HS256, "Dm00W3QQ23q7dvQAnckCRyZr5GEJCzEe34QcLXcwL4o=")
                .compact();

        Map<String, String> response = new HashMap<>();
        response.put("token", jwt);
        return response;
    }
}
