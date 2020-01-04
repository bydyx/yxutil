package com.bydyx.yxutil.request;
import lombok.Data;

/**
 * 其他服务的token
 *
 * @author qiang.feng
 * @date 2019/12/11 16:10
 */
@Data
public class OtherToken {
    String token;
    RefreshToken refreshToken;
    long createTime = System.currentTimeMillis();

    public OtherToken(RefreshToken refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void refreshToken() {
        refreshToken(System.currentTimeMillis());
    }

    public synchronized void refreshToken(long getTime) {
        if (getTime > createTime) {
            token = refreshToken.refreshToken();
            createTime = System.currentTimeMillis();
        }
    }
}