package com.shashank.spring.documentupload.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author Shahzad Hussain
 */
@Data
@NoArgsConstructor
public class Response {

	private int code;
	private String message;
	private HttpStatus status;
	private Object responseObject;

	public Response(String message, HttpStatus status) {
		this.message = message;
		this.status = status;
		this.code = status.value();
	}

	public Response(String message, Object responseObject, HttpStatus status) {
		this.message = message;
		this.status = status;
		this.code = status.value();
		this.responseObject = responseObject;
	}

}
