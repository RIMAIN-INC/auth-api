package io.orangetech.entity;

public class AccessToken {
   public  String accessToken;

    public  AccessToken(String accessToken){
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "accessToken='" + accessToken + '\'' +
                '}';
    }
}


