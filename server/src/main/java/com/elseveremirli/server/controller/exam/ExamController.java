package com.elseveremirli.server.controller.exam;

import com.elseveremirli.server.entities.ExamResult;
import com.elseveremirli.server.entities.User;
import com.elseveremirli.server.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    public String saveExam(){
        return "exam";
    }

    @PostMapping("/save/{examId}")
    public String saveExamResult(@AuthenticationPrincipal User user, @RequestBody ExamResult examResult, @PathVariable  int examId){
        return examService.saveExamResult(user,examId,examResult);
    }
}
