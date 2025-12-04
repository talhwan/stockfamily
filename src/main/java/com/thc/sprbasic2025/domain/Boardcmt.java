// 파일 경로: src/main/java/com/thc/sprbasic2025/domain/Boardcmt.java
package com.thc.sprbasic2025.domain;

import com.thc.sprbasic2025.dto.DefaultDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Table(indexes = {
        @Index(columnList = "deleted"),
        @Index(columnList = "board_id")
})
@Entity
public class Boardcmt extends AuditingFields {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    protected Boardcmt() {}

    private Boardcmt(Board board, Long userId, String content) {
        this.board = board;
        this.userId = userId;
        this.content = content;
    }

    public static Boardcmt of(Board board, Long userId, String content) {
        return new Boardcmt(board, userId, content);
    }

    public DefaultDto.CreateResDto toCreateResDto() {
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }
}
