package com.interest.fajia.model;

import com.google.gson.annotations.SerializedName;

public class Result<T> implements com.interest.framework.Result<T>{
	@SerializedName("result")
	private T result;
	@SerializedName("status")
	private int code;
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	@Override
	public String getErrorMsg() {
		// TODO Auto-generated method stub
		if(isSu()){
			return "";
		}else{
			return (String) result;
		}
	}
	@Override
	public boolean isSu() {
		// TODO Auto-generated method stub
		return code==1;
	}
	
}