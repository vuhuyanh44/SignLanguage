package edu.uet.signlanguage.models.response;

public class LoginResponse {
    private Object data;
    private Object token;

    public LoginResponse() {
    }

    public LoginResponse(Object data, Object token) {
        this.data = data;
        this.token = token;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }
}
