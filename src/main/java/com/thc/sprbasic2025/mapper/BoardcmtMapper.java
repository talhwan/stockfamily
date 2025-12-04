// 파일 경로: src/main/java/com/thc/sprbasic2025/mapper/BoardcmtMapper.java
package com.thc.sprbasic2025.mapper;

import com.thc.sprbasic2025.dto.BoardcmtDto;

import java.util.List;

public interface BoardcmtMapper {
    BoardcmtDto.DetailResDto detail(Long id);
    List<BoardcmtDto.DetailResDto> list(BoardcmtDto.ListReqDto param);

    List<BoardcmtDto.DetailResDto> pagedList(BoardcmtDto.PagedListReqDto param);
    int pagedListCount(BoardcmtDto.PagedListReqDto param);
    List<BoardcmtDto.DetailResDto> scrollList(BoardcmtDto.ScrollListReqDto param);
}
