package com.ochanhyeok.board.global.error;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

	private ErrorCode errorCode;

	public BusinessException(ErrorCode errorCode) {
		super(errorCode.getMessage()); // super는 반드시 생성자 앞에 와야 함
		this.errorCode = errorCode;
	}
}
