// 파일 경로: src/main/java/com/thc/sprbasic2025/service/impl/BoardcmtServiceimpl.java
package com.thc.sprbasic2025.service.impl;

import com.thc.sprbasic2025.domain.Board;
import com.thc.sprbasic2025.domain.Boardcmt;
import com.thc.sprbasic2025.dto.BoardcmtDto;
import com.thc.sprbasic2025.dto.DefaultDto;
import com.thc.sprbasic2025.exception.NoMatchingDataException;
import com.thc.sprbasic2025.mapper.BoardcmtMapper;
import com.thc.sprbasic2025.repository.BoardRepository;
import com.thc.sprbasic2025.repository.BoardcmtRepository;
import com.thc.sprbasic2025.service.BoardcmtService;
import com.thc.sprbasic2025.service.PermittedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardcmtServiceimpl implements BoardcmtService {

    final String target = "boardcmt";

    final BoardcmtRepository boardcmtRepository;
    final BoardRepository boardRepository;
    final BoardcmtMapper boardcmtMapper;
    final PermittedService permittedService;

    @Override
    public DefaultDto.CreateResDto create(BoardcmtDto.CreateReqDto param, Long reqUserId) {
        permittedService.isPermitted(reqUserId, target, 110);
        Board board = boardRepository.findById(param.getBoardId())
                .orElseThrow(() -> new NoMatchingDataException("no board data"));
        DefaultDto.CreateResDto res = boardcmtRepository.save(param.toEntity(board, reqUserId)).toCreateResDto();
        return res;
    }

    @Override
    public void update(BoardcmtDto.UpdateReqDto param, Long reqUserId) {
        permittedService.isPermitted(reqUserId, target, 120);
        Boardcmt boardcmt = boardcmtRepository.findById(param.getId())
                .orElseThrow(() -> new NoMatchingDataException("no data"));
        if (param.getDeleted() != null) boardcmt.setDeleted(param.getDeleted());
        if (param.getContent() != null) boardcmt.setContent(param.getContent());
        boardcmtRepository.save(boardcmt);
    }

    @Override
    public void delete(DefaultDto.DeleteReqDto param, Long reqUserId) {
        update(BoardcmtDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build(), reqUserId);
    }

    @Override
    public void deleteList(DefaultDto.DeleteListReqDto param, Long reqUserId) {
        for (Long id : param.getIds()) {
            delete(DefaultDto.DeleteReqDto.builder().id(id).build(), reqUserId);
        }
    }

    public BoardcmtDto.DetailResDto get(DefaultDto.DetailReqDto param, Long reqUserId) {
        permittedService.isPermitted(reqUserId, target, 200);
        BoardcmtDto.DetailResDto res = boardcmtMapper.detail(param.getId());
        return res;
    }

    @Override
    public BoardcmtDto.DetailResDto detail(DefaultDto.DetailReqDto param, Long reqUserId) {
        return get(param, reqUserId);
    }

    @Override
    public List<BoardcmtDto.DetailResDto> list(BoardcmtDto.ListReqDto param, Long reqUserId) {
        return detailList(boardcmtMapper.list(param), reqUserId);
    }

    public List<BoardcmtDto.DetailResDto> detailList(List<BoardcmtDto.DetailResDto> list, Long reqUserId) {
        List<BoardcmtDto.DetailResDto> newList = new ArrayList<>();
        for (BoardcmtDto.DetailResDto each : list) {
            newList.add(get(DefaultDto.DetailReqDto.builder().id(each.getId()).build(), reqUserId));
        }
        return newList;
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(BoardcmtDto.PagedListReqDto param, Long reqUserId) {
        DefaultDto.PagedListResDto res = param.init(boardcmtMapper.pagedListCount(param));
        res.setList(detailList(boardcmtMapper.pagedList(param), reqUserId));
        return res;
    }

    @Override
    public List<BoardcmtDto.DetailResDto> scrollList(BoardcmtDto.ScrollListReqDto param, Long reqUserId) {
        param.init();
        return detailList(boardcmtMapper.scrollList(param), reqUserId);
    }
}
