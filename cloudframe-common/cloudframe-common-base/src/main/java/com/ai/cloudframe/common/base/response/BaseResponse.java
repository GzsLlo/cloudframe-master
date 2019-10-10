package com.ai.cloudframe.common.base.response;


import com.ai.cloudframe.common.base.enums.StatusEnum;
import com.ai.cloudframe.common.base.util.StringUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 返参基础类.
 *
 * @author tangsz
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseResponse<T> implements Serializable{
	private String code;
	
	private String message;

	private T data;

	public BaseResponse() {}

	public BaseResponse(T data) {
		this.data = data;
	}

	public BaseResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public BaseResponse(String code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public static <T> BaseResponse<T> create(T t){
		return new BaseResponse<T>(t);
	}

	public static <T> BaseResponse<T> create(T t, StatusEnum statusEnum){
		return new BaseResponse<T>(statusEnum.getCode(), statusEnum.getMessage(), t);
	}

	public static <T> BaseResponse<T> success(){
		return  BaseResponse.success(null, "");
	}

	public static <T> BaseResponse<T> success(T t){
		return  BaseResponse.success(t, "");
	}

	public static <T> BaseResponse<T> success(T t, String message){
		return new BaseResponse<T>(StatusEnum.SUCCESS.getCode(), StringUtil.isNullOrEmpty(message) ? StatusEnum.SUCCESS.getMessage() : message, t);
	}

	public static <T> BaseResponse<T> fail(){
		return  BaseResponse.fail(null, "");
	}

	public static <T> BaseResponse<T> fail(T t){
		return  BaseResponse.fail(t, "");
	}


	public static <T> BaseResponse<T> fail(T t, String message){
		return new BaseResponse<T>(StatusEnum.FAIL.getCode(), StringUtil.isNullOrEmpty(message) ? StatusEnum.FAIL.getMessage() : message, t);
	}

	public static <T> BaseResponse<T> create(T t, StatusEnum statusEnum, String message){

		return new BaseResponse<T>(statusEnum.getCode(), message, t);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "BaseResponse{" +
				"code=" + code +
				", message='" + message + '\'' +
				", data=" + data +
				'}';
	}
}
