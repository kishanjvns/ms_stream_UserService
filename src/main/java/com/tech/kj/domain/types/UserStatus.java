package com.tech.kj.domain.types;

public enum UserStatus {
    INIT("INIT",1),ACTIVE("ACTIVE",2),BLOCKED("BLOCKED",3);

    private final String description;
    private final int code;

    UserStatus(String description, int code) {
        this.description=description;
        this.code = code;
    }
}
