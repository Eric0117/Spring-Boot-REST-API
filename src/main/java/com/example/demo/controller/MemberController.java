package com.example.demo.controller;

import com.example.demo.common.CommonResult;
import com.example.demo.common.SingleResult;
import com.example.demo.config.jwt.CurrentUser;
import com.example.demo.config.jwt.UserPrincipal;
import com.example.demo.model.dto.MemberSummaryResponseDTO;
import com.example.demo.model.dto.SignUpRequestDTO;
import com.example.demo.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Get Current Member", description = "Get signed in user info")
    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public SingleResult<MemberSummaryResponseDTO> getCurrentMember(@CurrentUser UserPrincipal currentUser) {
        return memberService.getCurrentMember(currentUser);
    }

    @Operation(summary = "Add Member", description = "Create user (ADMIN only)")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult addMember(@Valid @RequestBody SignUpRequestDTO member) {
        return memberService.addMember(member);
    }

    @Operation(summary = "Update Member", description = "Update Member (If profile belongs to logged in user or logged in user is admin)")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void updateMember(@PathVariable Long id) {

    }

    @Operation(summary = "Delete Member", description = "Delete Member (If profile belongs to logged in user or logged in user is admin)")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public CommonResult deleteMember(@PathVariable Long id, @CurrentUser UserPrincipal currentUser) {
        return memberService.deleteMember(id, currentUser);
    }

}
