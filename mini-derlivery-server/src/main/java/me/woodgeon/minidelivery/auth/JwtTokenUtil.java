package me.woodgeon.minidelivery.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

// JWT 토큰 방식을 사용할 때 필요한 기능들을 정의한 클래스
// 새로운 JWT토큰 발급, Claim에서 loginId 가져오기, 만료 기간 체크 기능 수행
public class JwtTokenUtil {
    //JWT 토큰 발급
    private static Key skey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public static String createToken(String loginId, String key, long expireTimeMs) {
        // Claim에 loginId를 넣어줌으로써 필요시 loginId 꺼내기 가능
        Claims claims = Jwts.claims();
        claims.put("loginId", loginId);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
                .signWith(skey)
                .compact();
    }
    // Claim에서 loginId 꺼내는 함수
    public static String getLoginId(String token, String secretkey) {
        return extractClaims(token, secretkey).get("loginId").toString();
    }
    // 발급된 토큰이 만료시간이 되었는지 체크하는 함수
    public static boolean isExpired(String token, String secretkey) {
        Date expiredDate = extractClaims(token, secretkey).getExpiration();
        return expiredDate.before(new Date());
    }
    // Secretkey를 사용하여 토큰 parsing
    private static Claims extractClaims(String token, String secretkey) {
        return Jwts.parserBuilder()
                .setSigningKey(skey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
