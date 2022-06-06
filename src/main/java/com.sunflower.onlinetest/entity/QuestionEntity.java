package com.sunflower.onlinetest.entity;

import com.sunflower.onlinetest.dao.iEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question")
public class QuestionEntity implements iEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "question_id")
    private List<AnswerEntity> answers;

    //  TODO  how to save file
//    @Colum/n(name = "attached_file")
//    private File attachedFile;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private TopicEntity topic;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDate createdDate;

    @CreationTimestamp
    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private boolean isDeleted;
}
