package com.thc.sprbasic2025.service.impl;

import com.thc.sprbasic2025.domain.Boardlike;
import com.thc.sprbasic2025.dto.BoardlikeDto;
import com.thc.sprbasic2025.dto.DefaultDto;
import com.thc.sprbasic2025.exception.NoMatchingDataException;
import com.thc.sprbasic2025.mapper.BoardlikeMapper;
import com.thc.sprbasic2025.repository.BoardRepository;
import com.thc.sprbasic2025.repository.BoardlikeRepository;
import com.thc.sprbasic2025.service.BoardlikeService;
import com.thc.sprbasic2025.service.PermittedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardlikeServiceimpl implements BoardlikeService {

    final String target = "boardlike";

    final BoardlikeRepository boardlikeRepository;
    final BoardRepository boardRepository;
    final BoardlikeMapper boardlikeMapper;
    final PermittedService permittedService;

    @Override
    public DefaultDto.CreateResDto create(BoardlikeDto.CreateReqDto param, Long reqUserId) {
        // permittedService.isPermitted(reqUserId, target, 110);

        DefaultDto.CreateResDto res = boardlikeRepository.save(param.toEntity()).toCreateResDto();

        if(param.getLiked()){
            boardRepository.increaseLike(param.getBoardId());
        } else {
            boardRepository.increaseDislike(param.getBoardId());
        }
        return res;
    }

    @Override
    public void update(BoardlikeDto.UpdateReqDto param, Long reqUserId) {
        // permittedService.isPermitted(reqUserId, target, 120);

        Boardlike boardlike = boardlikeRepository.findById(param.getId()).orElseThrow(() -> new NoMatchingDataException("no data"));
        if(param.getDeleted() != null) boardlike.setDeleted(param.getDeleted());
        if(param.getLiked() != null) boardlike.setLiked(param.getLiked());
        boardlikeRepository.save(boardlike);
    }

    @Override
    public void delete(DefaultDto.DeleteReqDto param, Long reqUserId) {
        Boardlike boardlike = boardlikeRepository.findById(param.getId()).orElseThrow(() -> new NoMatchingDataException("no data"));

        // 1. 게시글 숫자 감소 (먼저 처리)
        if(boardlike.getLiked()){
            boardRepository.decreaseLike(boardlike.getBoardId());
        } else {
            boardRepository.decreaseDislike(boardlike.getBoardId());
        }

        // 2. 삭제
        boardlikeRepository.deleteById(param.getId());
    }

    @Override
    public void deleteList(DefaultDto.DeleteListReqDto param, Long reqUserId) {
        for(Long id : param.getIds()){
            delete(DefaultDto.DeleteReqDto.builder().id(id).build(), reqUserId);
        }
    }

    public BoardlikeDto.DetailResDto get(DefaultDto.DetailReqDto param, Long reqUserId) {
        // permittedService.isPermitted(reqUserId, target, 200);
        return boardlikeMapper.detail(param.getId());
    }

    @Override
    public BoardlikeDto.DetailResDto detail(DefaultDto.DetailReqDto param, Long reqUserId) {
        return get(param, reqUserId);
    }

    @Override
    public List<BoardlikeDto.DetailResDto> list(BoardlikeDto.ListReqDto param, Long reqUserId) {
        return detailList(boardlikeMapper.list(param), reqUserId);
    }

    public List<BoardlikeDto.DetailResDto> detailList(List<BoardlikeDto.DetailResDto> list, Long reqUserId){
        List<BoardlikeDto.DetailResDto> newList = new ArrayList<>();
        for(BoardlikeDto.DetailResDto each : list){
            newList.add(get(DefaultDto.DetailReqDto.builder().id(each.getId()).build(), reqUserId));
        }
        return newList;
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(BoardlikeDto.PagedListReqDto param, Long reqUserId) {
        DefaultDto.PagedListResDto res = param.init(boardlikeMapper.pagedListCount(param));
        res.setList(detailList(boardlikeMapper.pagedList(param), reqUserId));
        return res;
    }

    @Override
    public List<BoardlikeDto.DetailResDto> scrollList(BoardlikeDto.ScrollListReqDto param, Long reqUserId) {
        param.init();
        return detailList(boardlikeMapper.scrollList(param), reqUserId);
    }
}