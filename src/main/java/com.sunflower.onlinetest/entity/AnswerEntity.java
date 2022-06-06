package com.sunflower.onlinetest.entity;


import com.sunflower.onlinetest.dao.iEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "answer")
public class AnswerEntity implements iEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    @Column(name = "right_answer", columnDefinition = "boolean default false")
    private boolean rightAnswer;

    //  TODO  how to save file
//    @Column(name = "attached_file")
//    private File attachedFile;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDate createDate;

    @CreationTimestamp
    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private boolean isDeleted;
}
