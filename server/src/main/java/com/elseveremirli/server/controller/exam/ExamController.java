package com.elseveremirli.server.controller.exam;
import java.util.List;

import com.elseveremirli.server.dto.exam.ExamRequest;
import com.elseveremirli.server.dto.exam.ExamResponse;
import com.elseveremirli.server.dto.exam.ExamResultRequest;
import com.elseveremirli.server.dto.exam.ExamResultResponse;
import com.elseveremirli.server.entities.Exam;
import com.elseveremirli.server.entities.ExamResult;
import com.elseveremirli.server.entities.User;
import com.elseveremirli.server.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @PostMapping("/save")
    public String saveExam(@RequestBody ExamRequest examRequest){
        return examService.saveExam(examRequest);
    }

    @PostMapping("/saveResult/{examId}")
    public String saveExamResult(@AuthenticationPrincipal User user, @RequestBody ExamResultRequest examResultRequest, @PathVariable  int examId){
        return examService.saveExamResult(user,examId,examResultRequest);
    }


    @GetMapping("/getAllExamResults")
    public List<ExamResultResponse> getAllExamResults(@AuthenticationPrincipal User user){
        return examService.getAllExamResults(user);
    }

    @GetMapping("/getExamResults/{examId}")
    public List<ExamResultResponse> getExamResults(@AuthenticationPrincipal User user, @PathVariable int examId){
        return examService.getExamResults(user, examId);
    }

    @GetMapping("/getAllExams")
    public List<ExamResponse> getAllExams(){
        return examService.getAllExams();
    }

    @GetMapping("/getExam/{examId}")
    public ExamResponse getExam(@PathVariable int examId){
        return examService.getExam(examId);
    }

}
