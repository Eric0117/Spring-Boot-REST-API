package com.example.demo.service;

import com.example.demo.common.CommonResult;
import com.example.demo.common.ListResult;
import com.example.demo.common.PageResult;
import com.example.demo.common.SingleResult;
import com.example.demo.config.jwt.UserPrincipal;
import com.example.demo.model.dto.MemberSummaryResponseDTO;
import com.example.demo.model.dto.MemberUpdateRequestDTO;
import com.example.demo.model.dto.SignUpRequestDTO;
import org.springframework.data.domain.Pageable;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 31.
 **/
public interface MemberService {
    SingleResult<MemberSummaryResponseDTO> getCurrentMember(UserPrincipal currentUser);
    CommonResult addMember(SignUpRequestDTO member);
    CommonResult updateMember(Long id, MemberUpdateRequestDTO memberUpdateRequestDTO, UserPrincipal currentUser);
    CommonResult deleteMember(Long id, UserPrincipal currentUser);
    PageResult<MemberSummaryResponseDTO> getMembers(Pageable pageable);
    SingleResult<MemberSummaryResponseDTO> getMember(Long id);
}
