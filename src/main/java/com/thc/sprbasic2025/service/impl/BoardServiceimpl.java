package com.thc.sprbasic2025.service.impl;

import com.thc.sprbasic2025.domain.Board;
import com.thc.sprbasic2025.dto.BoardDto;
import com.thc.sprbasic2025.dto.DefaultDto;
import com.thc.sprbasic2025.exception.NoMatchingDataException;
import com.thc.sprbasic2025.mapper.BoardMapper;
import com.thc.sprbasic2025.repository.BoardRepository;
import com.thc.sprbasic2025.service.BoardService;
import com.thc.sprbasic2025.service.PermittedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardServiceimpl implements BoardService {

    final String target = "board";

    final BoardRepository boardRepository;
    final BoardMapper boardMapper;
    final PermittedService permittedService;


    @Override
    public DefaultDto.CreateResDto create(BoardDto.CreateReqDto param, Long reqUserId) {
        permittedService.isPermitted(reqUserId, target, 110);
        DefaultDto.CreateResDto res = boardRepository.save(param.toEntity()).toCreateResDto();
        return res;
    }

    @Override
    public void update(BoardDto.UpdateReqDto param, Long reqUserId) {
        permittedService.isPermitted(reqUserId, target, 120);
        Board board = boardRepository.findById(param.getId()).orElseThrow(() -> new NoMatchingDataException("no data"));
        if(param.getDeleted() != null) board.setDeleted(param.getDeleted());
        if(param.getTitle() != null) board.setTitle(param.getTitle());
        if(param.getContent() != null) board.setContent(param.getContent());
        if(param.getCate() != null) board.setCate(param.getCate());
        if(param.getMinamount() != null) board.setMinamount(param.getMinamount());
        if(param.getCountlikeup() != null) board.setCountlikeup(param.getCountlikeup());
        if(param.getCountlikedown() != null) board.setCountlikedown(param.getCountlikedown());
        if(param.getImg() != null) board.setImg(param.getImg());
        boardRepository.save(board);
    }

    @Override
    public void delete(DefaultDto.DeleteReqDto param, Long reqUserId) {
        update(BoardDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build(), reqUserId);
    }
    @Override
    public void deleteList(DefaultDto.DeleteListReqDto param, Long reqUserId) {
        for(Long id : param.getIds()){
            delete(DefaultDto.DeleteReqDto.builder().id(id).build(), reqUserId);
        }
    }

    public BoardDto.DetailResDto get(DefaultDto.DetailReqDto param, Long reqUserId) {
        permittedService.isPermitted(reqUserId, target, 200);
        BoardDto.DetailResDto res = boardMapper.detail(param.getId());
        return res;
    }
    @Override
    public BoardDto.DetailResDto detail(DefaultDto.DetailReqDto param, Long reqUserId) {
        return get(param, reqUserId);
    }

    @Override
    public List<BoardDto.DetailResDto> list(BoardDto.ListReqDto param, Long reqUserId) {
        return detailList(boardMapper.list(param), reqUserId);
    }
    public List<BoardDto.DetailResDto> detailList(List<BoardDto.DetailResDto> list, Long reqUserId){
        List<BoardDto.DetailResDto> newList = new ArrayList<>();
        for(BoardDto.DetailResDto each : list){
            newList.add(get(DefaultDto.DetailReqDto.builder().id(each.getId()).build(), reqUserId));
        }
        return newList;
    }


    @Override
    public DefaultDto.PagedListResDto pagedList(BoardDto.PagedListReqDto param, Long reqUserId) {
        DefaultDto.PagedListResDto res = param.init(boardMapper.pagedListCount(param));
        res.setList(detailList(boardMapper.pagedList(param), reqUserId));
        return res;
    }

    @Override
    public List<BoardDto.DetailResDto> scrollList(BoardDto.ScrollListReqDto param, Long reqUserId) {
        param.init();

        //타이틀 로 스크롤 더 요청하는 경우 어쩔수 없이 작업!
        if ("title".equals(param.getOrderby())) {
            Long cursor = param.getCursor();
            if (cursor != null) {
                BoardDto.DetailResDto board = boardMapper.detail(cursor);
                if (board != null) {
                    param.setMark(board.getTitle() + "_" + board.getId());
                }
            }
        }
        return detailList(boardMapper.scrollList(param), reqUserId);
    }
}
