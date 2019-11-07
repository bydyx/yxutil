package com.bydyx.yxutil.json;

/**
 * json序列化接口
 */
public interface JsonSerialize<T> {

    default T parseJsonString(String jsonStr) {
        // TODO Object obj = new Obj().parseJsonString();
        // 目前会创建2个对象,之后要实现一个方法: JsonUtil.parseJson(String json,Obj obj)
        // 会把name一样的值存到obj的属性中
        return (T) JsonUtil.strToObj(jsonStr, this.getClass());
    }

    default String toJsonString() {
        return JsonUtil.parseJsonStr(this);
    }
}
