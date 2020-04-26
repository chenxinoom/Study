package com.czx.demo.utils;

import java.util.HashMap;
import java.util.Map;


public class Condition {
//	public static String charset ="UTF-8";
	private String classPath = null;
    private String method = null;
    private Map<String,Object> params = new HashMap<>();

    public Condition(){

    }

    public Condition(String classPath, String method) {
        this.classPath = classPath;
        this.method = method;
    }
    
    public Condition(String classPath, String method, Map<String, Object> params) {
        this.classPath = classPath;
        this.method = method;
        this.params = params;
    }
    
    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

    @Override
    public String toString() {
        return "Condition{" +
                "serviceId='" + classPath + '\'' +
                ", method='" + method + '\'' +
                ", params=" + params +
                '}';
    }
}
