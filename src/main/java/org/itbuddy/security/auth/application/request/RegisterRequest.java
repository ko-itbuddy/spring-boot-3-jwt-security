package org.itbuddy.security.auth.application.request;

import lombok.Builder;
import org.itbuddy.security.common.security.domain.Role;

@Builder
public record RegisterRequest(
    String firstname,
    String lastname,
    String email,
    String password,
    Role role
) {
}
