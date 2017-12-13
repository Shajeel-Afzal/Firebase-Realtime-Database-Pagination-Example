package com.shajeelafzal.firebaserealtimedatabasepagination.models;

/**
 * Created by shajeelafzal on 05/12/2017.
 */

public class UserModel {

    private String email;
    private String name;
    private String profileImageLink;
    private String provider;
    private String uid;

    public UserModel() {
    }

    public UserModel(String email, String name, String profileImageLink, String provider, String uid) {
        this.email = email;
        this.name = name;
        this.profileImageLink = profileImageLink;
        this.provider = provider;
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImageLink() {
        return profileImageLink;
    }

    public void setProfileImageLink(String profileImageLink) {
        this.profileImageLink = profileImageLink;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
