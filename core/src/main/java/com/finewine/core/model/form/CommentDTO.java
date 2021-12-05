package com.finewine.core.model.form;

import javax.validation.constraints.NotEmpty;

public class CommentDTO {

    @NotEmpty
    private String commentText;

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
