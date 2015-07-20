package com.weaverprojects.insta;

import android.graphics.Bitmap;

/**
 * Created by kweaver on 20/07/15.
 */
public class UserPost {
    String title;
    String about;
    Bitmap img;
    String likes;

    public UserPost(String title, String about, Bitmap img, String likes){
        super();
        this.title = title;
        this.about = about;
        this.img = img;
        this.likes = likes;
    }
    public String getTitle(){
        return title;
    }
    public String getAbout(){
        return about;
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
    public void setAbout(String s){
        this.about = s;
    }
    public void setImg(Bitmap i){
        this.img = i;
    }
    public void setLikes(String s){
        this.likes = s;
    }
}
