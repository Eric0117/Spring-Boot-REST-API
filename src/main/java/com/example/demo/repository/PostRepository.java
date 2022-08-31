package com.example.demo.repository;

import com.example.demo.model.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 31.
 **/
public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
}
