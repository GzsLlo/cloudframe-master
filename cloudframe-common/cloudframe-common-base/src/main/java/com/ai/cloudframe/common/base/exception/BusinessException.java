/*
 * Copyright (c) 2019 AI.
 */
package com.ai.cloudframe.common.base.exception;


import com.ai.cloudframe.common.base.enums.ErrorCodeEnum;

/**
 * 业务异常.
 *
 * @author tangsz
 */
public class BusinessException extends RuntimeException {

	/**
	 * 异常码
	 */
	protected String code;

	private static final long serialVersionUID = 3160241586346324994L;

	public BusinessException() {
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String code, String message) {
		super(message);
		this.code = code;
	}

	public BusinessException(String code, String msgFormat, Object... args) {
		super(String.format(msgFormat, args));
		this.code = code;
	}

	public BusinessException(ErrorCodeEnum codeEnum, Object... args) {
		super(String.format(codeEnum.msg(), args));
		this.code = codeEnum.code();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
