package com.heartauction.auth;

public record LoginMember(
        Long id,
        String name
) {
    public static final String key = "LoginMember";
}
