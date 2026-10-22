package com.ccbupt.entity;

import java.util.HashMap;
import java.util.Map;

public class R<T> {
    private int code;
    private String message;
    private T data;

    public static <T> R<T> ok(T data) {
        R<T> r = new R<>();
        r.code = 200;
        r.message = "success";
        r.data = data;
        return r;
    }

    // 重载方法：接收Map和额外的字符串信息
    public static R<Map<String, String>> ok(Map<String, String> dataMap, String message) {
        R<Map<String, String>> r = new R<>();
        r.code = 200;
        r.message = message != null ? message : "success";
        r.data = dataMap;
        return r;
    }

    public static <T> R<T> fail(String msg) {
        R<T> r = new R<>();
        r.code = 500;
        r.message = msg;
        return r;
    }
    
    public static <T> R<T> fail(int code, String msg) {
        R<T> r = new R<>();
        r.code = code;
        r.message = msg;
        return r;
    }
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        return "R{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

// 示例使用方式
class ExampleUsage {
    public static void main(String[] args) {
        // 正确的使用方式
        Map<String, String> mapData = new HashMap<>();
        mapData.put("key1", "value1");
        mapData.put("key2", "value2");
        
        // 将Map作为泛型参数传入
        R<Map<String, String>> result = R.ok(mapData);
        System.out.println("单参数调用: " + result);
        
        // 使用重载方法 - 同时传递Map和字符串消息
        R<Map<String, String>> resultWithMsg = R.ok(mapData, "操作成功");
        System.out.println("双参数调用: " + resultWithMsg);
        
        // 如果需要返回其他类型的数据
        R<String> stringResult = R.ok("操作成功");
        System.out.println("字符串结果: " + stringResult);
        
        R<Integer> intResult = R.ok(123);
        System.out.println("整数结果: " + intResult);
        
        // 正确处理多种数据的方式
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("data", mapData);
        responseMap.put("message", "操作成功");
        R<Map<String, Object>> complexResult = R.ok(responseMap);
        System.out.println("复合结果: " + complexResult);
        
        // 错误处理示例
        R<String> errorResult = R.fail("操作失败");
        System.out.println("错误结果: " + errorResult);
        
        R<String> customErrorResult = R.fail(404, "找不到资源");
        System.out.println("自定义错误: " + customErrorResult);
    }
}
