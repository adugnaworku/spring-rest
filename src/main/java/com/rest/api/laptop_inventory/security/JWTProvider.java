package com.rest.api.laptop_inventory.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

@Service
@Slf4j
public class JWTProvider {

    private KeyStore keyStore;

    @PostConstruct
    public void init() throws Exception {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/inventorKeyStore.jks");
            keyStore.load(resourceAsStream, "mypass".toCharArray());
        }
        catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException ex) {
            throw new Exception("Exception happened while loading keystore");
        }
    }
    private PublicKey getPublicKey() {
        try {
            return keyStore.getCertificate("mytest").getPublicKey();
        } catch (KeyStoreException e) {
            throw new JwtException("Exception occurred while retrieving a public key from keystore");
        }
    }

    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("mytest", "mypass".toCharArray());
        } catch (UnrecoverableKeyException | NoSuchAlgorithmException | KeyStoreException ex) {
            throw new SecurityException("Exception occured while retrieving a public key from keystore");
        }
    }

    public String generateToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(getPrivateKey())
                .compact();
    }

    public boolean validateToken(String jwt) {
        try {
            Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
        } catch (JwtException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public String getUserNameFromJwt(String jwt) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(getPublicKey())
                    .parseClaimsJws(jwt)
                    .getBody();
            return claims.getSubject();
        } catch(JwtException ex) {
            throw new MalformedJwtException("JWT token is not valid");
        }
    }
}
