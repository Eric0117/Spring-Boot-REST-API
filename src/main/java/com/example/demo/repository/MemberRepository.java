package com.example.demo.repository;

import com.example.demo.model.dto.MemberSummaryResponseDTO;
import com.example.demo.model.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 30.
 **/
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
    Optional<Member> findByUsernameOrEmail(String username, String email);
    Boolean existsByUsername(@NotBlank String username);
    Boolean existsByEmail(@NotBlank String email);
}
