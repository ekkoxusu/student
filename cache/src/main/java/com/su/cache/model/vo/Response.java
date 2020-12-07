package com.su.cache.model.vo;

/**
 * @author xusu
 * @since 2020/12/7
 */
public class Response<T> {

    /**
     * 编码
     */
    private String  code;

    /**
     * 数据
     */
    private T data;

    /**
     * 备注
     */
    private String msg;


    public Response(String code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static <T> Response<T> sussce(T data){
        return new Response(200+"",data,"");
    }

    public static <T> Response<T> error(T data){
        return new Response(500+"",data,"");
    }
}
