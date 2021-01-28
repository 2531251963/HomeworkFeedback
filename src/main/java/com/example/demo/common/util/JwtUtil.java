package com.example.demo.common.util;

import com.example.demo.common.constant.Constant;
import com.example.demo.common.response.Response;
import com.example.demo.common.response.ResponseCode;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StreamUtils;

import java.util.Calendar;

/**
 * JWT工具
 */
public class JwtUtil {

    /** JWT秘钥 */
    public final static String SECRET_KEY = "homeworkFeedback";
    /**
     * 构建JWT对象
     */
    public static String buildJwt(Long userId) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, Constant.ONE_DAY);
        String jwt = Jwts.builder()
                // 使用HS256加密算法
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                // 过期时间
                .setExpiration(calendar.getTime())
                .claim("userId",userId)
                .compact();
        return jwt;
    }


    /**
     * 身份认证
     */
    public static Response<Long> validationToken(String jwt) {
        if (StringUtils.isBlank(jwt)){
            return Response.error(ResponseCode.AUTH_VALIDATION_FAILED);
        }
        try {
            //解析JWT字符串中的数据，并进行最基础的验证
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(jwt)
                    .getBody();
            Long userId =claims.get("userId",Long.class);
            if (userId != null && userId != 0) {
                return Response.ok(userId);
            }
            return Response.error(ResponseCode.AUTH_VALIDATION_FAILED);
        } catch (ExpiredJwtException e) {
            // 已过期令牌
            return Response.error(ResponseCode.AUTH_VALIDATION_EXPIRE_FAILED);
        }
    }


    /**
     * 身份认证
     */
    public static Long getUserId(String jwt) {
        if (StringUtils.isBlank(jwt)){
            return null;
        }
        try {
            //解析JWT字符串中的数据，并进行最基础的验证
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(jwt)
                    .getBody();
            Long userId =claims.get("userId",Long.class);
            if (userId != null && userId != 0) {
                return userId;
            }
            return null;
        } catch (ExpiredJwtException e) {
            // 已过期令牌
            return null;
        }
    }
}
