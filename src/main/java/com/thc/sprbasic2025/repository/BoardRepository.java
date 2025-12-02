package com.thc.sprbasic2025.repository;

import com.thc.sprbasic2025.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    // 좋아요 1 증가
    @Transactional
    @Modifying
    @Query("UPDATE Board b SET b.countlikeup = COALESCE(b.countlikeup, 0) + 1 WHERE b.id = :id")
    void increaseLike(@Param("id") Long id);

    // 좋아요 1 감소
    @Transactional
    @Modifying
    @Query("UPDATE Board b SET b.countlikeup = CASE WHEN COALESCE(b.countlikeup, 0) > 0 THEN COALESCE(b.countlikeup, 0) - 1 ELSE 0 END WHERE b.id = :id")
    void decreaseLike(@Param("id") Long id);

    // 싫어요 1 증가
    @Transactional
    @Modifying
    @Query("UPDATE Board b SET b.countlikedown = COALESCE(b.countlikedown, 0) + 1 WHERE b.id = :id")
    void increaseDislike(@Param("id") Long id);

    // 싫어요 1 감소
    @Transactional
    @Modifying
    @Query("UPDATE Board b SET b.countlikedown = CASE WHEN COALESCE(b.countlikedown, 0) > 0 THEN COALESCE(b.countlikedown, 0) - 1 ELSE 0 END WHERE b.id = :id")
    void decreaseDislike(@Param("id") Long id);
}