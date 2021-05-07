package com.bydyx.dbutil.exception;

/**
 * @author 风清扬
 * @date 2020/11/18 15:59
 */
public class DBExceptoin extends RuntimeException {
	public DBExceptoin() {
	}

	public DBExceptoin(String message) {
		super(message);
	}

	public DBExceptoin(String message, Throwable cause) {
		super(message, cause);
	}

	public DBExceptoin(Throwable cause) {
		super(cause);
	}

	public DBExceptoin(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}