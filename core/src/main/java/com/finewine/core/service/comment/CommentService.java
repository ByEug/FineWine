package com.finewine.core.service.comment;

import com.finewine.core.model.comment.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentsForProductId(String productId);

    void save(String commentText, Long userId, String productId);
}
