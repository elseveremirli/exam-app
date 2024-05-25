package com.elseveremirli.server.dto.exam;

import com.elseveremirli.server.entities.Exam;
import com.elseveremirli.server.entities.ExamResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamResultResponse {
    //private Exam exam;
    private int id;
    private String results;
    private int exam_id;
    public static ExamResultResponse examResutlConvertToExamResultResponse(ExamResult examResult){
        ExamResultResponse examResultResponse = ExamResultResponse.builder()
      //          .exam(examResult.getExam())
                .id(examResult.getId())
                .results(examResult.getResults())
                .exam_id(examResult.getExam().getId())
                .build();
        return examResultResponse;
    }
}
