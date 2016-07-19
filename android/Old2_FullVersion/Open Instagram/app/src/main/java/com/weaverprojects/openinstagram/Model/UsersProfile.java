package com.weaverprojects.openinstagram.Model;

import java.util.ArrayList;

/**
 * Created by kweaver on 18/12/15.
 */
public class UsersProfile {
    String userId;
    String profileImg;
    String userName;
    String name;
    String about;
    int followers;
    int following;
    ArrayList<PostBasics> posts;

    public UsersProfile(String userId, String profileImg, String userName, String name, String about, int followers, int following, ArrayList<PostBasics> posts) {
        this.userId = userId;
        this.profileImg = profileImg;
        this.userName = userName;
        this.name = name;
        this.about = about;
        this.followers = followers;
        this.following = following;
        this.posts = posts;
    }

    public String getUserId() {
        return userId;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public String getAbout() {
        return about;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    public ArrayList<PostBasics> getPosts() {
        return posts;
    }
}
