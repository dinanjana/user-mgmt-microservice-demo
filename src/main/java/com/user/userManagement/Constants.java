package com.user.userManagement;

/**
 * Created by dinanjanag on 4/21/19.
 */
public class Constants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 28_800_000; // 8 hours
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String ROLE = "role";
    public static final String SIGN_UP_URL = "/users/sign-up";
}
