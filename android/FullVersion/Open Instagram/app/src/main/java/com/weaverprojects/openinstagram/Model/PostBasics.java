package com.weaverprojects.openinstagram.Model;

/**
 * Created by kweaver on 18/12/15.
 */
public class PostBasics {
    String postId;
    String imgUrl;

    public PostBasics(String postId, String imgUrl) {
        this.postId = postId;
        this.imgUrl = imgUrl;
    }

    public String getPostId() {
        return postId;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
