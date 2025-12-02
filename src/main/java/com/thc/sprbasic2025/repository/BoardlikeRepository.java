package com.thc.sprbasic2025.repository;

import com.thc.sprbasic2025.domain.Boardlike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardlikeRepository extends JpaRepository<Boardlike, Long> {
}