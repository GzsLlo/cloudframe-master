/*
 * Copyright (c) 2019. AI.
 */
package com.ai.cloudframe.common.base.enums;


/**
 * @author tangsz
 */

public enum ErrorCodeEnum {

	E00000403("0403", "无访问权限"),

	E00000500("0500", "未知异常"),

	E00000001("0001", "登陆已过期,请重新登陆"),

	E00000002("0002", "缺少必输参数:"),;


	private String code;
	private String msg;

	public String msg() {
		return msg;
	}

	public String code() {
		return code;
	}

	ErrorCodeEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static ErrorCodeEnum getEnum(String code) {
		for (ErrorCodeEnum ele : ErrorCodeEnum.values()) {
			if (ele.code() == code) {
				return ele;
			}
		}
		return null;
	}
}
