package com.elseveremirli.server.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "exam_results")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExamResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String results;
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
//    private User user;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id")
    private Exam exam;
}

