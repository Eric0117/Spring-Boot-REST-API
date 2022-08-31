package com.example.demo.service.impl;

import com.example.demo.advice.exception.exceptions.EmailDuplicatedException;
import com.example.demo.advice.exception.exceptions.NameDuplicatedException;
import com.example.demo.advice.exception.exceptions.RoleNotSetException;
import com.example.demo.common.CommonResult;
import com.example.demo.common.SingleResult;
import com.example.demo.config.jwt.UserPrincipal;
import com.example.demo.model.dto.MemberSummaryResponseDTO;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.management.relation.RoleNotFoundException;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;
/**
 * @Author Eric
 * @Description
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
    public CommonResult addMember(SignUpRequestDTO member) {
        if (memberRepository.existsByUsername(member.getUsername())) {
            throw new NameDuplicatedException();
        }

        if (memberRepository.existsByEmail(member.getEmail())) {
            throw new EmailDuplicatedException();
        }

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(RoleNotSetException::new));

        List<Role> roles2 = Collections.singletonList(roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(RoleNotSetException::new));

        Member member2 = new Member(member.getUsername(),member.getEmail(),passwordEncoder.encode(member.getPassword()),roles2);
//        Member newMember = Member.builder()
//                .username(member.getUsername())
//                .email(member.getEmail())
//                .password(passwordEncoder.encode(member.getPassword()))
//                .roles(roles2).build();

        memberRepository.save(member2);

        return responseService.getSuccessResult();
    }


}
