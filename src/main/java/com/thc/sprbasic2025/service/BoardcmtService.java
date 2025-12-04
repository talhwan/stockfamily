// 파일 경로: src/main/java/com/thc/sprbasic2025/service/BoardcmtService.java
package com.thc.sprbasic2025.service;

import com.thc.sprbasic2025.dto.BoardcmtDto;
import com.thc.sprbasic2025.dto.DefaultDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardcmtService {

    DefaultDto.CreateResDto create(BoardcmtDto.CreateReqDto param, Long reqUserId);
    void update(BoardcmtDto.UpdateReqDto param, Long reqUserId);
    void delete(DefaultDto.DeleteReqDto param, Long reqUserId);
    void deleteList(DefaultDto.DeleteListReqDto param, Long reqUserId);
    BoardcmtDto.DetailResDto detail(DefaultDto.DetailReqDto param, Long reqUserId);
    List<BoardcmtDto.DetailResDto> list(BoardcmtDto.ListReqDto param, Long reqUserId);

    DefaultDto.PagedListResDto pagedList(BoardcmtDto.PagedListReqDto param, Long reqUserId);
    List<BoardcmtDto.DetailResDto> scrollList(BoardcmtDto.ScrollListReqDto param, Long reqUserId);
}
