package com.weaverprojects.insta;

import android.graphics.Bitmap;

/**
 * Created by kweaver on 20/07/15.
 */
public class UserPost {

    String postCode;
    String title;
    Bitmap profileImg;
    String username;
    Bitmap img;
    String likes;

    public UserPost(String postCode, String title,Bitmap profileImg, String username, Bitmap img, String likes){
        super();
        this.postCode = postCode;
        this.title = title;
        this.profileImg = profileImg;
        this.username = username;
        this.img = img;
        this.likes = likes;
    }
    public String getTitle(){
        return title;
    }
    public String getUsername(){
        return username;
    }
    public Bitmap getImg(){
        return img;
    }
    public String getLikes(){
        return likes;
    }
    public void setTitle(String s){
        this.title = s;
    }
    public void setUsername(String s){
        this.username = s;
    }
    public void setImg(Bitmap i){
        this.img = i;
    }
    public void setLikes(String s){
        this.likes = s;
    }
    public Bitmap getProfileImg(){
        return (this.profileImg);
    }
    public void setProfileImg(Bitmap b){
        this.profileImg = b;
    }
}
