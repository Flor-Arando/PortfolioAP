package com.grupo.backend.Controller;

import org.springframework.beans.factory.annotation.Value;
import java.security.Key;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class ControllerGenerico {
    @Value("${jwt.key}")
    protected String jwtKey;
    private Key key;

    private void generarKeyParaFirma() {
        this.key = Keys.hmacShaKeyFor(this.jwtKey.getBytes());
    }

    public String generarToken() {
        this.generarKeyParaFirma();
        return Jwts.builder().setSubject("yo").signWith(this.key).compact();
    }
    
    public boolean tokenValido(String jws) {
		try {
            this.generarKeyParaFirma();
            return Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(jws).getBody().getSubject().equals("yo");
        } catch (JwtException e) {
            return false;
        }
	}
}
