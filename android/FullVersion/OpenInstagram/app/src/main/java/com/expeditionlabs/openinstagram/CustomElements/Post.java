package com.expeditionlabs.openinstagram.CustomElements;

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



}
