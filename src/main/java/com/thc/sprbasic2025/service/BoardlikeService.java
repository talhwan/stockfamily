package com.thc.sprbasic2025.service;

import com.thc.sprbasic2025.dto.BoardlikeDto;
import com.thc.sprbasic2025.dto.DefaultDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardlikeService {
    DefaultDto.CreateResDto create(BoardlikeDto.CreateReqDto param, Long reqUserId);
    void update(BoardlikeDto.UpdateReqDto param, Long reqUserId);
    void delete(DefaultDto.DeleteReqDto param, Long reqUserId);
    void deleteList(DefaultDto.DeleteListReqDto param, Long reqUserId);
    BoardlikeDto.DetailResDto detail(DefaultDto.DetailReqDto param, Long reqUserId);
    List<BoardlikeDto.DetailResDto> list(BoardlikeDto.ListReqDto param, Long reqUserId);

    DefaultDto.PagedListResDto pagedList(BoardlikeDto.PagedListReqDto param, Long reqUserId);
    List<BoardlikeDto.DetailResDto> scrollList(BoardlikeDto.ScrollListReqDto param, Long reqUserId);
}