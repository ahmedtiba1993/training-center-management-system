package com.tiba.center.user.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record RoleRequest(
    @NotEmpty(message = "ROLE_NAME_REQUIRED") @NotBlank(message = "ROLE_NAME_REQUIRED") String name,
    @NotEmpty(message = "ROLE_DESCRIPTION_REQUIRED")
        @NotBlank(message = "ROLE_DESCRIPTION_REQUIRED")
        String description) {}
