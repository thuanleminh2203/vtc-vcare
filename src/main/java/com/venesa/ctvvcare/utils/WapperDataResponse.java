package com.venesa.ctvvcare.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class WapperDataResponse {
	
	public static <T> ResponseEntity<T> sucsses(T body){
		return new ResponseEntity<T>(body, HttpStatus.OK);
	}
	
	public static <T> ResponseEntity<T> err(T body, HttpStatus httpStatus){
		return new ResponseEntity<T>(body, httpStatus);
	}
}
