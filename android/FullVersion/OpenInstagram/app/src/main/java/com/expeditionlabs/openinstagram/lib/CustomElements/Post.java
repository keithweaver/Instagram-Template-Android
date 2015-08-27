package com.expeditionlabs.openinstagram.lib.CustomElements;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by kweaver on 28/07/15.
 */
public class Post {
    public static String TAG= "";

    String postCode;

    //Top elements
    int timesincepost;
    String username;
    Bitmap userProfileImg;
    String userProfileImgLink;

    //Post
    Bitmap postImg;
    String postImgLink;

    //Bottom
    int likes;
    String caption;
    ArrayList<PostComment> comments;

    public Post(String postCode, int timeSincePost, String username, Bitmap userProfileImg,
                String userProfileImgLink, Bitmap postImg, String postImgLink, int likes,
                String caption, ArrayList<PostComment> comments){
        super();
        this.postCode = postCode;
        this.timesincepost = timeSincePost;
        this.username = username;
        this.userProfileImg = userProfileImg;
        this.userProfileImgLink = userProfileImgLink;
        this.postImg = postImg;
        this.postImgLink = postImgLink;
        this.likes = likes;
        this.caption = caption;
        this.comments = comments;
    }

    public String getPostCode(){
        return this.postCode;
    }
    public int getTimeSincePost(){
        return this.timesincepost;
    }
    public String getUsername(){
        return this.username;
    }
    public Bitmap getUserProfileImg(){
        return this.userProfileImg;
    }
    public String getUserProfileImgLink(){
        return this.userProfileImgLink;
    }
    public Bitmap getImg(){
        return this.postImg;
    }
    public String getPostImgLink(){
        return this.postImgLink;
    }
    public int getLikes(){
        return this.likes;
    }
    public String getCaption(){
        return this.caption;
    }
    public ArrayList<PostComment> getComments(){
        return this.comments;
    }
    public void setImg(Bitmap b){
        this.postImg = b;
    }
    public void setUserProfileImg(Bitmap b){
        this.userProfileImg = b;
    }
}
