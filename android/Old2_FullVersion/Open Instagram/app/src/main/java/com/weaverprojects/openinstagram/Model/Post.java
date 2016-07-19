package com.weaverprojects.openinstagram.Model;

import java.util.ArrayList;

/**
 * Created by kweaver on 18/12/15.
 */
public class Post {
    String postId;
    String userName;
    String profileUrl;
    String imgUrl;
    int likes;
    ArrayList<Comment> comments;
    String caption;
    boolean following;
    boolean sponsored;

    public Post(String postId, String userName, String profileUrl, String imgUrl, int likes, ArrayList<Comment> comments, String caption, boolean following, boolean sponsored) {
        this.postId = postId;
        this.userName = userName;
        this.profileUrl = profileUrl;
        this.imgUrl = imgUrl;
        this.likes = likes;
        this.comments = comments;
        this.caption = caption;
        this.following = following;
        this.sponsored = sponsored;
    }

    public String getPostId() {
        return postId;
    }

    public String getUserName() {
        return userName;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public int getLikes() {
        return likes;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public String getCaption() {
        return caption;
    }

    public boolean isFollowing() {
        return following;
    }

    public boolean isSponsored() {
        return sponsored;
    }
}
