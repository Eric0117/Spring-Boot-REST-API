package com.example.demo.controller;

import com.example.demo.common.CommonResult;
import com.example.demo.common.SingleResult;
import com.example.demo.config.jwt.CurrentUser;
import com.example.demo.config.jwt.UserPrincipal;
import com.example.demo.model.dto.MemberSummaryResponseDTO;
import com.example.demo.model.dto.SignUpRequestDTO;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 31.
 **/
@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public SingleResult<MemberSummaryResponseDTO> getCurrentMember(@CurrentUser UserPrincipal currentUser) {
        return memberService.getCurrentMember(currentUser);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult addMember(@Valid @RequestBody SignUpRequestDTO member) {
        return memberService.addMember(member);
    }

}
