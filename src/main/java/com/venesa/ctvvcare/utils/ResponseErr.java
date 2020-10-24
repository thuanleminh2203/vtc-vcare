package com.venesa.ctvvcare.utils;

public class ResponseErr {
	private String errCode;
	private String mess;

	public ResponseErr(String errCode, String mess) {
		this.errCode = errCode;
		this.mess = mess;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}
	
	
}
