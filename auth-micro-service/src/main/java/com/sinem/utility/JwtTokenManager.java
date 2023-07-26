package com.sinem.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {
    /**
     *  1 - Kullanici kendini dogrulamali, username ve password ile doLogin
     *  2 - Dogrulanmis kisinin kimlik bilgileri ile ona yeni bir JWT olusturmaliyiz.
     */
    private final long exDate = 1000L*20;   // 20 saniye
    private final String sifreAnahtari = "bşkbpteo942uy3u432-310*21weıfwjsv";
    public Optional<String> createToken(Long id){
        try {
            String token;
            token = JWT.create()
                    .withAudience()
                    // DIKKAT !! Lutfen buraya kisisel bilgiler ya da gizli olan bilgiler eklemeyin.
                    // sifre, adres, email vb. buraya eklenmemeliç
                    .withIssuer("sinem")    // Token icinde gizli olmayan ancak kullanilmasi gereken bilgiler
                    .withClaim("id", id)
                    .withClaim("islemturu", "genel")
                    .withIssuedAt(new Date())   // JWT token'in olusturulma zamani. timestamp
                    .withExpiresAt(new Date(System.currentTimeMillis() + exDate))   // JWT token'in olusturulma zamani. timestamp
                    .sign(Algorithm.HMAC512(sifreAnahtari));    // JWT token in sifreleme algoritmasi
            return Optional.of(token);
        } catch (Exception ex){
            return Optional.empty();
        }
    }
    public Optional<Long> getByIdFromToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC512(sifreAnahtari);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("sinem")
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if(decodedJWT == null){
                return Optional.empty();
            }
            Long id = decodedJWT.getClaim("id").asLong();
            return Optional.of(id);
        } catch (Exception ex){
            return Optional.empty();
        }
    }
}
