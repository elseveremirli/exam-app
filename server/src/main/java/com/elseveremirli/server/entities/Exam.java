package com.elseveremirli.server.entities;
import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "exams")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String imgUrl;

//    @ManyToOne
//    private User user;

    @OneToMany(mappedBy = "exam",fetch = FetchType.LAZY)
    private List<ExamResult> examResults;


    @OneToOne(mappedBy = "exam", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private ExamAnswer examAnswer;

}
