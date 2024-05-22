package com.elseveremirli.server.repository;

import com.elseveremirli.server.entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {
    Exam findExamById(int id);
}
