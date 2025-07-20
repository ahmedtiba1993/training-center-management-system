package com.tiba.center.common;

import java.time.Instant;

public record ApiResponse<T>(boolean success, String message, T data, long timestamp) {
  public static <T> ApiResponse<T> success(T data, String message) {
    return new ApiResponse<>(true, message, data, Instant.now().toEpochMilli());
  }

  public static <T> ApiResponse<T> error(String message) {
    return new ApiResponse<>(false, message, null, Instant.now().toEpochMilli());
  }

  public static <T> ApiResponse<T> error(T data, String message) {
    return new ApiResponse<>(false, message, data, Instant.now().toEpochMilli());
  }
}
