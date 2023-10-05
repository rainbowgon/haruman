package ssafy.haruman.global.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.UUID;

public class JwtUtil {

    public static String createJwt(UUID memberId, String secretKey, Long expiredMs) {
        Claims claims = Jwts.claims();
        claims.put("member_id", memberId);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public static boolean isExpired(String token, String secretKey) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                    .getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public static UUID getMemberIdFromJwt(String token, String secretKey) {
        return UUID.fromString(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                                       .getBody().get("member_id", String.class));
    }
}
