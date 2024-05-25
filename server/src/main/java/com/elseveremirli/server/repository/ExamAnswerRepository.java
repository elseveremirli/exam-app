package com.elseveremirli.server.repository;
import com.elseveremirli.server.entities.ExamAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamAnswerRepository extends JpaRepository<ExamAnswer, Integer>{
}
