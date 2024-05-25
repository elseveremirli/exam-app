package com.elseveremirli.server.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.elseveremirli.server.dto.exam.ExamResponse;
import com.elseveremirli.server.entities.ExamAnswer;
import com.elseveremirli.server.repository.ExamAnswerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elseveremirli.server.dto.exam.ExamRequest;
import com.elseveremirli.server.dto.exam.ExamResultRequest;
import com.elseveremirli.server.dto.exam.ExamResultResponse;
import com.elseveremirli.server.entities.Exam;
import com.elseveremirli.server.entities.ExamResult;
import com.elseveremirli.server.entities.User;
import com.elseveremirli.server.repository.ExamRepository;
import com.elseveremirli.server.repository.ExamResultRepository;
import com.elseveremirli.server.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;
    private final ExamResultRepository examResultRepository;
    private final UserRepository userRepository;
    private final ExamAnswerRepository examAnswerRepository;

    @Transactional
    public String saveExam(ExamRequest examRequest) {
        ExamAnswer examAnswer = ExamAnswer.builder()
                .answer(examRequest.getAnswer())
                .build();
        Exam exam = Exam.builder()
                .imgUrl(examRequest.getImgUrl())
                .examAnswer(examAnswer)
                .build();
        examRepository.save(exam);
        examAnswer.setExam(exam);
        examAnswerRepository.save(examAnswer);
        return "Exam saved";
    }

    @Transactional
    public String saveExamResult(User user, int examId, ExamResultRequest examResult) {
        List<ExamResult> newExamResults = user.getExamResults() == null ? Collections.emptyList() : user.getExamResults();
        Optional<Exam> examOptional = examRepository.findById(examId);

        if (examOptional.isPresent()) {
            Exam exam = examOptional.get();

            ExamResult examResult1 = ExamResult.builder()
                    .results(examResult.getResults())
                    .exam(exam)
                    .build();

            // ExamResult'ı kaydedin
            examResultRepository.save(examResult1);

            // ExamResult'ı User'ın examResults listesine ekleyin
            newExamResults.add(examResult1);
            user.setExamResults(newExamResults);

            // User'ı kaydedin
            userRepository.save(user);

            return "Exam result saved";
        } else {
            throw new IllegalArgumentException("Exam with id " + examId + " not found.");
        }
    }

    public List<ExamResultResponse> getAllExamResults(User user) {
        List<ExamResult> examResults = user.getExamResults();
        return examResults.stream().map(ExamResultResponse::examResutlConvertToExamResultResponse).toList();
    }

    public List<ExamResultResponse> getExamResults(User user, int examId) {
        List<ExamResult> examResults = user.getExamResults();
        return examResults.stream().filter(examResult -> examResult.getExam().getId() == examId)
                .map(ExamResultResponse::examResutlConvertToExamResultResponse).toList();
    }

    public List<ExamResponse> getAllExams() {
        List<Exam> exams = examRepository.findAll();
        return exams.stream().map(ExamResponse::examConvertToExamResponse).toList();
    }

    public ExamResponse getExam(int examId) {
        Exam exam = examRepository.findById(examId).orElseThrow();
        return ExamResponse.examConvertToExamResponse(exam);
    }
}
