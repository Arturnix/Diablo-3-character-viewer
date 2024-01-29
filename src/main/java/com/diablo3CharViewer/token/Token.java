package com.diablo3CharViewer.token;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Token {
    private static String access_token = "EUY4G9gg3VGNX7SV6Rvf4OW02bXgZMB2A2";
    @JsonIgnore
    private String token_type;
    @JsonIgnore
    private String expires_in;
    @JsonIgnore
    private String sub;

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public static String getAccess_token() {
        return access_token;
    }

    @JsonIgnore
    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    @JsonIgnore
    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    @JsonIgnore
    public void setSub(String sub) {
        this.sub = sub;
    }

}