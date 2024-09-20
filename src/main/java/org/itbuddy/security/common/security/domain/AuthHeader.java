package org.itbuddy.security.common.security.domain;


public class AuthHeader {

    private final String authHeader;

    public AuthHeader(String authHeader)  {
        this.authHeader = authHeader;
    }

    public boolean isValidate() {
        if (authHeader == null) {
            return false;
        }
        if (!this.authHeader.startsWith("Bearer ")) {
            return false;
        }
        return true;
    }
    public String getJwtToken(){
        return this.authHeader.substring(7);
    }


}
