package com.futurecreator.searchquestion.utils.jwt;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 工具类
 * 根据内容生成token
 * 或根据token来判断当前内容是否过期
 */
@Component
public class JWT {

    @Value("${jwt.token}")
    private String jwtToken;

    /**
     * 根据内容和过期时间创建token
     * @param content
     * @param expireTime
     * @return
     */
    public String createToken(String content,Long expireTime){
        Map<String,Object> claims = new HashMap<>();
        claims.put("content",content);
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtToken) // 签发算法，秘钥为jwtToken
                .setClaims(claims) // body数据，要唯一，自行设置
                .setIssuedAt(new Date()) // 设置签发时间
                .setExpiration(new Date(System.currentTimeMillis() +expireTime));// 一天的有效时间
        return jwtBuilder.compact();
    }

    //解密token,得到id
    public Boolean isTokenValid(String token){
        try {
            return Jwts.parser().setSigningKey(jwtToken).parse(token)!=null;
        }catch (Exception e){
            return false;
        }
    }
}