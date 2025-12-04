// 파일 경로: src/main/java/com/thc/sprbasic2025/controller/BoardcmtRestController.java
package com.thc.sprbasic2025.controller;

import com.thc.sprbasic2025.dto.BoardcmtDto;
import com.thc.sprbasic2025.dto.DefaultDto;
import com.thc.sprbasic2025.security.PrincipalDetails;
import com.thc.sprbasic2025.service.BoardcmtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/boards/{boardId}/comments")
@RestController
public class BoardcmtRestController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    final BoardcmtService boardcmtService;

    public Long getReqUserId(PrincipalDetails principalDetails) {
        if (principalDetails == null || principalDetails.getUser() == null || principalDetails.getUser().getId() == null) {
            return null;
        }
        return principalDetails.getUser().getId();
    }

    // POST /api/boards/{boardId}/comments : 댓글 생성
    @PreAuthorize("hasRole('USER')")
    @PostMapping("")
    public ResponseEntity<DefaultDto.CreateResDto> create(
            @PathVariable Long boardId,
            @RequestBody BoardcmtDto.CreateReqDto params,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long reqUserId = getReqUserId(principalDetails);
        params.setBoardId(boardId);
        return ResponseEntity.ok(boardcmtService.create(params, reqUserId));
    }

    // GET /api/boards/{boardId}/comments : 해당 게시글의 댓글 목록 조회
    @PreAuthorize("hasRole('USER')")
    @GetMapping("")
    public ResponseEntity<List<BoardcmtDto.DetailResDto>> list(
            @PathVariable Long boardId,
            BoardcmtDto.ListReqDto params,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long reqUserId = getReqUserId(principalDetails);
        params.setBoardId(boardId);
        params.setDeleted(false);
        return ResponseEntity.ok(boardcmtService.list(params, reqUserId));
    }

    // PUT /api/boards/{boardId}/comments/{commentId} : 댓글 수정
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{commentId}")
    public ResponseEntity<Void> update(
            @PathVariable Long boardId,
            @PathVariable Long commentId,
            @RequestBody BoardcmtDto.UpdateReqDto params,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long reqUserId = getReqUserId(principalDetails);
        params.setId(commentId);
        boardcmtService.update(params, reqUserId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // DELETE /api/boards/{boardId}/comments/{commentId} : 댓글 삭제 (soft delete)
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long boardId,
            @PathVariable Long commentId,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long reqUserId = getReqUserId(principalDetails);
        boardcmtService.delete(DefaultDto.DeleteReqDto.builder().id(commentId).build(), reqUserId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // GET /api/boards/{boardId}/comments/{commentId} : 댓글 상세 조회
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{commentId}")
    public ResponseEntity<BoardcmtDto.DetailResDto> detail(
            @PathVariable Long boardId,
            @PathVariable Long commentId,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long reqUserId = getReqUserId(principalDetails);
        return ResponseEntity.ok(boardcmtService.detail(DefaultDto.DetailReqDto.builder().id(commentId).build(), reqUserId));
    }

    // GET /api/boards/{boardId}/comments/pagedList : 페이징 댓글 목록
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/pagedList")
    public ResponseEntity<DefaultDto.PagedListResDto> pagedList(
            @PathVariable Long boardId,
            BoardcmtDto.PagedListReqDto params,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long reqUserId = getReqUserId(principalDetails);
        params.setBoardId(boardId);
        params.setDeleted(false);
        return ResponseEntity.ok(boardcmtService.pagedList(params, reqUserId));
    }

    // GET /api/boards/{boardId}/comments/scrollList : 무한스크롤 댓글 목록
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/scrollList")
    public ResponseEntity<List<BoardcmtDto.DetailResDto>> scrollList(
            @PathVariable Long boardId,
            BoardcmtDto.ScrollListReqDto params,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long reqUserId = getReqUserId(principalDetails);
        params.setBoardId(boardId);
        params.setDeleted(false);
        return ResponseEntity.ok(boardcmtService.scrollList(params, reqUserId));
    }
}
