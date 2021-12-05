package com.finewine.core.service.comment;

import com.finewine.core.exception.NoElementWithSuchIdException;
import com.finewine.core.model.comment.Comment;
import com.finewine.core.model.comment.CommentDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentDao commentDao;

    @Override
    public List<Comment> getCommentsForProductId(String productId) {
        Long longId;
        try {
            longId = Long.valueOf(productId);
        } catch (NumberFormatException e) {
            throw new NoElementWithSuchIdException(productId);
        }
        if (commentDao.checkCommentsForProduct(longId) > 0) {
            return commentDao.getCommentsForProduct(longId);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void save(String commentText, Long userId, String productId) {
        Long longId;
        try {
            longId = Long.valueOf(productId);
        } catch (NumberFormatException e) {
            throw new NoElementWithSuchIdException(productId);
        }
        commentDao.save(commentText, userId, longId);
    }
}
