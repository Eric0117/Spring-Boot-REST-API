package com.example.demo.repository;

import com.example.demo.config.jwt.UserPrincipal;
import com.example.demo.model.dto.MemberSummaryResponseDTO;
import com.example.demo.model.member.Member;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 31.
 **/
public interface MemberRepositoryCustom {
    MemberSummaryResponseDTO getCurrentMember(UserPrincipal currentUser);
}
