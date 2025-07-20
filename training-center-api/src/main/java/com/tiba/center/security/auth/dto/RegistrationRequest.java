package com.tiba.center.security.auth.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {

  @NotEmpty(message = "FIRST_NAME_REQUIRED")
  @NotBlank(message = "FIRST_NAME_REQUIRED")
  private String firstname;

  @NotEmpty(message = "LAST_NAME_REQUIRED")
  @NotBlank(message = "LAST_NAME_REQUIRED")
  private String lastname;

  @NotEmpty(message = "EMAIL_REQUIRED")
  @NotBlank(message = "EMAIL_REQUIRED")
  @Email(message = "INVALID_EMAIL_FORMAT")
  private String email;

  @NotEmpty(message = "PASSWORD_REQUIRED")
  @NotBlank(message = "PASSWORD_REQUIRED")
  @Size(min = 8, message = "MINIMUM_PASSWORD_LENGTH_8_CHARACTERS")
  private String password;

  @NotEmpty(message = "CONFIRM_PASSWORD_REQUIRED")
  @NotBlank(message = "CONFIRM_PASSWORD_REQUIRED")
  private String confirmPassword;

  @AssertTrue(message = "PASSWORDS_DO_NOT_MATCH")
  public boolean isPasswordConfirmed() {
    return password != null && password.equals(confirmPassword);
  }
}
