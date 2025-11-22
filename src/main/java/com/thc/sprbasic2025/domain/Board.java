package com.thc.sprbasic2025.domain;

import com.thc.sprbasic2025.dto.DefaultDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Table(indexes = {@Index(columnList = "deleted")})
@Entity
public class Board extends AuditingFields {

    private String title;
    private String content;
    private Integer cate;
    private Integer minamount;
    private Integer countlikeup;
    private Integer countlikedown;
    private String img;

    protected Board(){}
    private Board(String title, String content, Integer cate, Integer minamount, String img) {
        this.title = title;
        this.content = content;
        this.countlikeup = 0;
        this.countlikedown = 0;
        this.cate = cate;
        this.minamount = minamount;
        this.img = img;
    }

    public static Board of(String title, String content, Integer cate, Integer minamount, String img) {
        return new Board(title, content, cate, minamount, img);
    }

    public DefaultDto.CreateResDto toCreateResDto() {
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }
}