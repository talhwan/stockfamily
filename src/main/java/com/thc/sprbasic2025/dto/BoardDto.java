package com.thc.sprbasic2025.dto;

import com.thc.sprbasic2025.domain.Board;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

public class BoardDto {

    /**/

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateReqDto {
        private String title;
        private String content;
        private Integer cate;
        private Integer minamount;
        String img;

        private MultipartFile file;

        public Board toEntity(){
            return Board.of(getTitle(), getContent(), getCate(), getMinamount(), getImg());
        }
    }

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto{
        private String title;
        private String content;
        private Integer countlikeup;
        private Integer countlikedown;
        private Integer cate;
        private Integer minamount;
        private String img;
    }

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto extends DefaultDto.DetailResDto{
        private String title;
        private String content;
        private Integer countlikeup;
        private Integer countlikedown;
        private Integer cate;
        private Integer minamount;
        private String img;
    }

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class ListReqDto extends DefaultDto.ListReqDto{
        private String title;
    }

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto{
        private String title;
    }
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto{
        private String title;
    }

}
