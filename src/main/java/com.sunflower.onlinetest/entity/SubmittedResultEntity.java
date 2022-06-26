package com.sunflower.onlinetest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "submitted_result")
public class SubmittedResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity question;


    @ManyToMany
    @JoinTable(
            name = "submitted_answer",
            joinColumns = @JoinColumn(name = "submitted_answer_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id")
    )
    private List<AnswerEntity> answers;
}
