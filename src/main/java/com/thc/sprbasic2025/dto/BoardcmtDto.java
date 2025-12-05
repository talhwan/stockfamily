// 파일 경로: src/main/java/com/thc/sprbasic2025/dto/BoardcmtDto.java
package com.thc.sprbasic2025.dto;

import com.thc.sprbasic2025.domain.Board;
import com.thc.sprbasic2025.domain.Boardcmt;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class BoardcmtDto {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReqDto {
        private Long boardId;
        private String content;

        public Boardcmt toEntity(Board board, Long userId) {
            return Boardcmt.of(board, userId, getContent());
        }
    }

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto {
        private String content;
    }

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailResDto extends DefaultDto.DetailResDto {
        private Long boardId;
        private Long userId;
        private String content;
    }

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListReqDto extends DefaultDto.ListReqDto {
        private Long boardId;
    }

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto {
        private Long boardId;
    }

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto {
        private Long boardId;
    }
}
