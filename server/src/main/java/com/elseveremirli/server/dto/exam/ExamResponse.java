package com.elseveremirli.server.dto.exam;

import com.elseveremirli.server.entities.Exam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamResponse {
    private int id;
    private String imgUrl;
    private String answer;

    public static ExamResponse examConvertToExamResponse(Exam exam) {
        return ExamResponse.builder()
                .id(exam.getId())
                .imgUrl(exam.getImgUrl())
                .answer(exam.getExamAnswer().getAnswer())
                .build();
    }
}
