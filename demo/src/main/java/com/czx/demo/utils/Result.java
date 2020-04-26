package com.czx.demo.utils;

import java.io.Serializable;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class Result implements Serializable{
	
	public final static int SUCCESS = 0;
	
    public final static int FAIL = 1;
    
    private int status;

    private String message;
    
    private Map<String,Object> contentMap;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Map<String, Object> getContentMap() {
		return contentMap;
	}

	public void setContentMap(Map<String, Object> contentMap) {
		this.contentMap = contentMap;
	}
	
	public static Result json2Object(String json){
		Result commandResult = JSON.parseObject(json,Result.class);
        return  commandResult;
    }
}
