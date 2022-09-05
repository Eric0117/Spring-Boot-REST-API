package com.example.demo.repository;

import com.example.demo.model.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Eric
 * @Description
 * @Since 22. 9. 5.
 **/
public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom{
}
