package com.thc.sprbasic2025.mapper;

import com.thc.sprbasic2025.dto.BoardlikeDto;

import java.util.List;

public interface BoardlikeMapper {
    BoardlikeDto.DetailResDto detail(Long id);
    List<BoardlikeDto.DetailResDto> list(BoardlikeDto.ListReqDto param);

    List<BoardlikeDto.DetailResDto> pagedList(BoardlikeDto.PagedListReqDto param);
    int pagedListCount(BoardlikeDto.PagedListReqDto param);
    List<BoardlikeDto.DetailResDto> scrollList(BoardlikeDto.ScrollListReqDto param);
}