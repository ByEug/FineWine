package com.finewine.core.model.comment;

import java.util.List;

public interface CommentDao {

    Integer checkCommentsForProduct(Long productId);

    List<Comment> getCommentsForProduct(Long productId);

    void save(String commentText, Long userId, Long productId);
}
