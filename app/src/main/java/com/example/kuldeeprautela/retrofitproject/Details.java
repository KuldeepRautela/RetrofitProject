package com.example.kuldeeprautela.retrofitproject;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kuldeep Rautela on 9/5/2019.
 */

public class Details {
    @SerializedName("name")
private String name;
@SerializedName("avatar_url")
private String avatar;
@SerializedName("email")
private  String email;
    @SerializedName("bio")
private  String bio;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
