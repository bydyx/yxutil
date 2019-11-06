package com.bydyx.yxutil.string;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.bydyx.yxutil.string.exception.JwtException;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

/**
 * @author qiang.feng
 * @date 2019/11/6 8:43
 */
public class JwtUtil {

    public static String createToken(Map<String, String> infoMap, String secret, Date expirationDate) {
        Algorithm algorithm = createAlgorithm(secret);
        JWTCreator.Builder builder = JWT.create();
        infoMap.keySet().forEach(key -> builder.withClaim(key, infoMap.get(key)));
        return builder.withExpiresAt(expirationDate).sign(algorithm);
    }

    /**
     * 校验token
     */
    public static void verify(Map<String, String> infoMap, String jwtToken, String secret) throws JwtException {
        try {
            JWTVerifier verifier = createJwtVerifier(infoMap, secret);
            verifier.verify(jwtToken);
        } catch (Exception e) {
            throw new JwtException(e);
        }
    }

    public static JWTVerifier createJwtVerifier(Map<String, String> infoMap, String secret) {
        Algorithm algorithm = createAlgorithm(secret);
        Verification require = JWT.require(algorithm);
        infoMap.keySet().forEach(key -> require.withClaim(key, infoMap.get(key)));
        return require.build();
    }

    public static Algorithm createAlgorithm(String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return algorithm;
        } catch (IllegalArgumentException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获得token中的信息
     */
    public static String getValue(String token, String key) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim(key).asString();
    }
}