package com.ccbupt.entity;

import java.util.Date;

public class SimpleLog {
	private String method;      // 方法名
    private Object[] params;    // 参数
    private Object result;      // 返回值
    private String error;       // 异常信息
    private Long costTime;      // 耗时(ms)
    private Date time;          // 时间
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Object[] getParams() {
		return params;
	}
	public void setParams(Object[] params) {
		this.params = params;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Long getCostTime() {
		return costTime;
	}
	public void setCostTime(Long costTime) {
		this.costTime = costTime;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
    
    
}
