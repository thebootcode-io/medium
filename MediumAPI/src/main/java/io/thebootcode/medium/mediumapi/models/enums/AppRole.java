package io.thebootcode.medium.mediumapi.models.enums;

import lombok.Getter;

@Getter
public enum AppRole {
    ROLE_ADMIN("Admin"),
    ROLE_USER("User");

    private final String displayName;

    AppRole(String displayName) {
        this.displayName = displayName;
    }
}
