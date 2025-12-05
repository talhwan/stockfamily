// 파일 경로: src/main/java/com/thc/sprbasic2025/repository/BoardcmtRepository.java
package com.thc.sprbasic2025.repository;

import com.thc.sprbasic2025.domain.Boardcmt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardcmtRepository extends JpaRepository<Boardcmt, Long> {

    List<Boardcmt> findByBoardIdAndDeletedFalse(Long boardId);

    List<Boardcmt> findByBoardIdAndDeletedFalseOrderByCreatedAtDesc(Long boardId);
}
