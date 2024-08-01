package io.orangetech.entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class UserRequest {
    private String username;
    private String password;
    private String email;


    public UserRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserRequest(){};

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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
