package com.venesa.ctvvcare.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData<T> implements Serializable {
	private int errorCode;
	private String errorMessage;
	private T data;



}
