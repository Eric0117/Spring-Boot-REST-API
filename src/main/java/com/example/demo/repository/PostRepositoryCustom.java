package com.example.demo.repository;

import com.example.demo.model.dto.MemberSummaryResponseDTO;
import com.example.demo.model.dto.PostResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 31.
 **/
public interface PostRepositoryCustom {
    Page<PostResponseDTO> getPosts(Pageable pageable);
    PostResponseDTO getPost(Long id);
}
