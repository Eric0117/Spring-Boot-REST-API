package com.example.demo.service.impl;

import com.example.demo.advice.exception.exceptions.*;
import com.example.demo.common.CommonResult;
import com.example.demo.common.ListResult;
import com.example.demo.common.PageResult;
import com.example.demo.common.SingleResult;
import com.example.demo.config.jwt.UserPrincipal;
import com.example.demo.model.dto.MemberSummaryResponseDTO;
import com.example.demo.model.dto.MemberUpdateRequestDTO;
import com.example.demo.model.dto.SignUpRequestDTO;
import com.example.demo.model.member.Member;
import com.example.demo.model.role.MemberRole;
import com.example.demo.model.role.Role;
import com.example.demo.model.role.RoleName;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.MemberService;
import com.example.demo.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.management.relation.RoleNotFoundException;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;
/**
 * @Author Eric
 * @Description https://developer-ping9.tistory.com/225
 * @Since 22. 8. 31.
 **/
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MemberServiceImpl.class);

    private final PasswordEncoder passwordEncoder;
    private final ResponseService responseService;
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;

    @Override
    public SingleResult<MemberSummaryResponseDTO> getCurrentMember(UserPrincipal currentUser) {
        MemberSummaryResponseDTO memberSummary = memberRepository.getCurrentMember(currentUser);
        return responseService.getSingleResult(memberSummary);
    }

    @Override
    public PageResult<MemberSummaryResponseDTO> getMembers(Pageable pageable) {
        Page<MemberSummaryResponseDTO> memberSummary = memberRepository.getMembers(pageable);
        return responseService.getPageResult(memberSummary);
    }

    @Override
    public SingleResult<MemberSummaryResponseDTO> getMember(Long id) {
        MemberSummaryResponseDTO memberSummary = memberRepository.getMemberById(id);
        return responseService.getSingleResult(memberSummary);
    }


    @Override
    public CommonResult addMember(SignUpRequestDTO member) {
        if (memberRepository.existsByUsername(member.getUsername())) {
            throw new NameDuplicatedException();
        }

        if (memberRepository.existsByEmail(member.getEmail())) {
            throw new EmailDuplicatedException();
        }

        List<Role> roles2 = Collections.singletonList(roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(RoleNotSetException::new));

        Member newMember = new Member(member.getUsername(),member.getEmail(),passwordEncoder.encode(member.getPassword()),roles2,true);

        memberRepository.save(newMember);

        return responseService.getSuccessResult();
    }

    @Override
    public CommonResult updateMember(Long id, MemberUpdateRequestDTO memberUpdateRequestDTO, UserPrincipal currentUser) {
        Member member = memberRepository.findById(id)
                .orElseThrow(MemberNotExistException::new);
        if (!member.getId().equals(currentUser.getId()) && !currentUser.getAuthorities()
                .contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
            throw new AccessDeniedException();
        }

        String encryptedPassword = passwordEncoder.encode(memberUpdateRequestDTO.getPassword());
        memberUpdateRequestDTO.setPassword(encryptedPassword);

        member.updateMember(memberUpdateRequestDTO.getUsername(), memberUpdateRequestDTO.getPassword());
        memberRepository.save(member);
        return responseService.getSuccessResult();
    }

    @Override
    public CommonResult deleteMember(Long id, UserPrincipal currentUser) {
        Member member = memberRepository.findById(id)
                .orElseThrow(MemberNotExistException::new);
        if (!member.getId().equals(currentUser.getId()) && !currentUser.getAuthorities()
                .contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
            throw new AccessDeniedException();
        }

        if(member.getIsActive()) {
            member.deActive();
        }

        memberRepository.save(member);
        return responseService.getSuccessResult();
    }

}
