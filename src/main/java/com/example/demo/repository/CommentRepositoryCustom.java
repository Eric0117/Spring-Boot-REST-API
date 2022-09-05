package com.example.demo.repository;

import com.example.demo.model.dto.CommentResponseDTO;
import com.example.demo.model.dto.PostResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author Eric
 * @Description
 * @Since 22. 9. 5.
 **/
public interface CommentRepositoryCustom {
    Page<CommentResponseDTO> getComments(Long postId, Pageable pageable);
    CommentResponseDTO getComment(Long postId, Long id);
}
