package com.tiba.center.exception;

import static org.springframework.http.HttpStatus.*;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum ErrorCode {
  NO_CODE(0, NOT_IMPLEMENTED, "No code"),
  INCORRECT_CURRENT_PASSWORD(300, BAD_REQUEST, "Current password is incorrect"),
  NEW_PASSWORD_DOES_NOT_MATCH(301, BAD_REQUEST, "The new password does not match"),
  ACCOUNT_LOCKED(302, FORBIDDEN, "User account is locked"),
  ACCOUNT_DISABLED(303, FORBIDDEN, "User account is disabled"),
  BAD_CREDENTIALS(304, FORBIDDEN, "LOGIN_PASSWORD_INCORRECT"),
  DUPLICATE_ENTITY(305, CONFLICT, "DUPLICATE_FOUND"),
  NOT_FOUND(306, BAD_REQUEST, "NOT_FOUND"),
  INVALID_REQUEST(307, BAD_REQUEST, "INVALID_REQUEST"),
  INVALID_ACTION(407, HttpStatus.CONFLICT, "INVALID_ACTION");
  ;
  @Getter private final int code;
  @Getter private final String description;
  @Getter private final HttpStatus httpStatus;

  ErrorCode(int code, HttpStatus httpStatus, String description) {
    this.code = code;
    this.description = description;
    this.httpStatus = httpStatus;
  }
}
