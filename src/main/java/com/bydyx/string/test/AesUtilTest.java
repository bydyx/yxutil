package com.bydyx.string.test;

import com.bydyx.string.AesUtil;
import com.bydyx.string.PrintUtil;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author q.f
 * @date 2020/8/4 17:08
 */
public class AesUtilTest {
    public static void main(String[] args) {
        List<DecryptObj> list = createDecryptObjList();
        list.forEach(decryptObj -> {
            String content = decryptObj.getContent();
            String key = decryptObj.getKey();
            String iv = decryptObj.getIv();
            byte[] result = AesUtil.decrypt(content, key, iv);
            PrintUtil.print(AesUtil.strInit(result));
        });
    }

    private static List<DecryptObj> createDecryptObjList() {
        List<DecryptObj> list = new ArrayList<>();
        list.add(DecryptObj.builder()
                           .content("EYRPdBhNMMdGASv37H0opJPzz3tXGsX/7Rmjv3u9E6GhuB1p9FKvrNKiqYmi6ctCP1oKTVdRX7C5eBZlv2DMHnFmniYy7YzlbE0jH9rxbO0as5o1mxy4alY3bF7pZ/niaxQKiCPXqoK7kiERYd2rjqJXflgt7WLM53S04IU+W1LwPYbC2Kf/PtMCovIK0P36VmAlMV4s23wtcpxCQyXSsA==")
                           .iv("H1R0idulFWi4XeU6smLuLA==")
                           .key("yQuq6enfZk4IPe56tIIuYw==")
                           .build());
        list.add(DecryptObj.builder()
                           .content("EYRPdBhNMMdGASv37H0opJPzz3tXGsX/7Rmjv3u9E6GhuB1p9FKvrNKiqYmi6ctCP1oKTVdRX7C5eBZlv2DMHnFmniYy7YzlbE0jH9rxbO0as5o1mxy4alY3bF7pZ/niaxQKiCPXqoK7kiERYd2rjqJXflgt7WLM53S04IU+W1LwPYbC2Kf/PtMCovIK0P36VmAlMV4s23wtcpxCQyXSsA==")
                           .iv("H1R0idulFWi4XeU6smLuLA==")
                           .key("yQuq6enfZk4IPe56tIIuYw==")
                           .build());
        list.add(DecryptObj.builder()
                           .content("EYRPdBhNMMdGASv37H0opJPzz3tXGsX/7Rmjv3u9E6GhuB1p9FKvrNKiqYmi6ctCP1oKTVdRX7C5eBZlv2DMHnFmniYy7YzlbE0jH9rxbO0as5o1mxy4alY3bF7pZ/niaxQKiCPXqoK7kiERYd2rjqJXflgt7WLM53S04IU+W1LwPYbC2Kf/PtMCovIK0P36VmAlMV4s23wtcpxCQyXSsA==")
                           .iv("H1R0idulFWi4XeU6smLuLA==")
                           .key("yQuq6enfZk4IPe56tIIuYw==")
                           .build());
        list.add(DecryptObj.builder()
                           .content("EYRPdBhNMMdGASv37H0opJPzz3tXGsX/7Rmjv3u9E6GhuB1p9FKvrNKiqYmi6ctCP1oKTVdRX7C5eBZlv2DMHnFmniYy7YzlbE0jH9rxbO0as5o1mxy4alY3bF7pZ/niaxQKiCPXqoK7kiERYd2rjqJXflgt7WLM53S04IU+W1LwPYbC2Kf/PtMCovIK0P36VmAlMV4s23wtcpxCQyXSsA==")
                           .iv("H1R0idulFWi4XeU6smLuLA==")
                           .key("yQuq6enfZk4IPe56tIIuYw==")
                           .build());
        list.add(DecryptObj.builder()
                           .content("EYRPdBhNMMdGASv37H0opJPzz3tXGsX/7Rmjv3u9E6GhuB1p9FKvrNKiqYmi6ctCP1oKTVdRX7C5eBZlv2DMHnFmniYy7YzlbE0jH9rxbO0as5o1mxy4alY3bF7pZ/niaxQKiCPXqoK7kiERYd2rjqJXflgt7WLM53S04IU+W1LwPYbC2Kf/PtMCovIK0P36VmAlMV4s23wtcpxCQyXSsA==")
                           .iv("H1R0idulFWi4XeU6smLuLA==")
                           .key("yQuq6enfZk4IPe56tIIuYw==")
                           .build());
        return list;
    }
}

@Data
@Builder
class DecryptObj {
    String content;
    String key;
    String iv;
}
