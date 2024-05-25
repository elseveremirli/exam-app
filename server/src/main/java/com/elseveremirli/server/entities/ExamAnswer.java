package com.elseveremirli.server.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "exam_answers")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExamAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String answer;

    @OneToOne
    @JoinColumn(name = "exam_id",referencedColumnName = "id")
    private Exam exam;
}
