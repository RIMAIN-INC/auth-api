package io.orangetech.entity;


public class LoginRequest {
    private String password;
    private String email;


    public LoginRequest(String email, String password) {
        this.password = password;
        this.email = email;
    }

    public LoginRequest(){};

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
