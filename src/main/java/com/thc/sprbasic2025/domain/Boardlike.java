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
@Table(indexes = {
        @Index(columnList = "deleted"),
        @Index(columnList = "boardId"),
        @Index(columnList = "userId")
})
@Entity
public class Boardlike extends AuditingFields {

    private Long userId;
    private Long boardId;
    private Boolean liked;

    protected Boardlike(){}
    private Boardlike(Long userId, Long boardId, Boolean liked) {
        this.userId = userId;
        this.boardId = boardId;
        this.liked = liked;
    }

    public static Boardlike of(Long userId, Long boardId, Boolean liked) {
        return new Boardlike(userId, boardId, liked);
    }

    public DefaultDto.CreateResDto toCreateResDto() {
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }
}