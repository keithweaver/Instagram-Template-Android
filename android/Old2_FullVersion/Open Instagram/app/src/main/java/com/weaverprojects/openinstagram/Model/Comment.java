package com.weaverprojects.openinstagram.Model;

/**
 * Created by kweaver on 18/12/15.
 */
public class Comment {
    String commentId;
    String userName;
    String comment;

    public Comment(String commentId, String userName, String comment) {
        this.commentId = commentId;
        this.userName = userName;
        this.comment = comment;
    }

    public String getCommentId() {
        return commentId;
    }

    public String getUserName() {
        return userName;
    }

    public String getComment() {
        return comment;
    }
}
