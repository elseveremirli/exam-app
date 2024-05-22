package com.elseveremirli.server.repository;

import com.elseveremirli.server.entities.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResult, Integer> {
}
