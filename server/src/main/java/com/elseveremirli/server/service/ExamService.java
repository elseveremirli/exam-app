package com.elseveremirli.server.service;

import com.elseveremirli.server.entities.Exam;
import com.elseveremirli.server.entities.ExamResult;
import com.elseveremirli.server.entities.User;
import com.elseveremirli.server.repository.ExamRepository;
import com.elseveremirli.server.repository.ExamResultRepository;
import com.elseveremirli.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;
    private final ExamResultRepository examResultRepository;
    private final UserRepository userRepository;

    public String saveExam(Exam exam) {
        examRepository.save(exam);
        return "Exam saved";
    }

    public String saveExamResult(User user,int examId,ExamResult examResult) {
        List<ExamResult> newExamResults = user.getExamResults();
        Optional<Exam> exam = examRepository.findById(examId);
        exam.ifPresent(value -> value.getExamResults().add(examResult));
        exam.ifPresent(examRepository::save);

        newExamResults.add(examResult);
        User newUser = User.builder()
                .name(user.getName())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .surname(user.getSurname())
                .examResults(newExamResults)
                .accountNonExpired(user.isAccountNonExpired())
                .accountNonLocked(user.isAccountNonLocked())
                .isCredentialsNonExpired(user.isCredentialsNonExpired())
                .isEnabled(user.isEnabled())
                .build();
        userRepository.save(newUser);
        ExamResult newExamResult = ExamResult.builder()
                .results(examResult.getResults())
                .user(newUser)
                .build();
        examResultRepository.save(newExamResult);

        return "Exam result saved";
    }

}
