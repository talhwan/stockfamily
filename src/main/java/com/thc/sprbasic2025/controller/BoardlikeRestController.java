package com.thc.sprbasic2025.controller;

import com.thc.sprbasic2025.dto.BoardlikeDto;
import com.thc.sprbasic2025.dto.DefaultDto;
import com.thc.sprbasic2025.security.PrincipalDetails;
import com.thc.sprbasic2025.service.BoardlikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/boardlike")
@RestController
public class BoardlikeRestController {

    final BoardlikeService boardlikeService;

    // 유저 ID 꺼내는 함수 (로그인 안했으면 null 대신 1L 리턴해서 에러 방지)
    public Long getReqUserId(PrincipalDetails principalDetails){
        if(principalDetails == null || principalDetails.getUser() == null || principalDetails.getUser().getId() == null){
            return 1L; // 로그인 안했으면 무조건 1번 유저로 인식
        }
        return principalDetails.getUser().getId();
    }

    // hasRole('USER') 제거 -> 누구나 접속 가능하게 변경
    // @PreAuthorize("hasRole('USER')")
    @PostMapping("")
    public ResponseEntity<DefaultDto.CreateResDto> create(@RequestBody BoardlikeDto.CreateReqDto params, @AuthenticationPrincipal PrincipalDetails principalDetails){
        Long reqUserId = getReqUserId(principalDetails);
        return ResponseEntity.ok(boardlikeService.create(params, reqUserId));
    }

    // @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody DefaultDto.DeleteReqDto params, @AuthenticationPrincipal PrincipalDetails principalDetails){
        Long reqUserId = getReqUserId(principalDetails);
        boardlikeService.delete(params, reqUserId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // @PreAuthorize("hasRole('USER')")
    @GetMapping("/list")
    public ResponseEntity<List<BoardlikeDto.DetailResDto>> list(BoardlikeDto.ListReqDto params
            , @AuthenticationPrincipal PrincipalDetails principalDetails){
        Long reqUserId = getReqUserId(principalDetails);
        return ResponseEntity.ok(boardlikeService.list(params, reqUserId));
    }
}