package com.thc.sprbasic2025.dto;

import com.thc.sprbasic2025.domain.Boardlike;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class BoardlikeDto {

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateReqDto {
        private Long userId;
        private Long boardId;
        private Boolean liked;

        public Boardlike toEntity(){
            return Boardlike.of(getUserId(), getBoardId(), getLiked());
        }
    }

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto{
        private Long userId;
        private Long boardId;
        private Boolean liked;
    }

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto extends DefaultDto.DetailResDto{
        private Long userId;
        private Long boardId;
        private Boolean liked;
    }

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class ListReqDto extends DefaultDto.ListReqDto{
        private Long userId;
        private Long boardId;
        private Boolean liked;
    }

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto{
        private Long userId;
        private Long boardId;
        private Boolean liked;
    }

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto{
        private Long userId;
        private Long boardId;
        private Boolean liked;
    }
}