package com.bydyx.util.string;

import lombok.Data;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ObjToStrUtilTest {
    @Test
    public void testMapToXmlStr() throws Exception {
        Map map = new HashMap();
        map.put("x1x", 1);
        map.put("xx", null);
        Obj obj = new Obj();
        Obj2 obj2 = new Obj2();
        obj2.setKey("xasxasxa");
        obj.setFile2(obj2);
        obj2.setO(new Obj3());
        map.put("11",obj);
        System.out.println(ObjToStrUtil.mapToXmlStr(map));
    }
}

@Data
class Obj {
    String name;
    Obj2 file2;
}

@Data
class Obj2{
    String key;
    Obj3 o;
}
@Data
class Obj3{
    String key;
}