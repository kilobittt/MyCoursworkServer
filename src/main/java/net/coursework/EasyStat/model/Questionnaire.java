package net.coursework.EasyStat.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "questionnaires")
@Data
public class Questionnaire extends BaseQuestionEntity{

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "is_represented")
    private boolean isRepresented;

    @Column(name = "is_marked")
    private boolean isMarked;

    @Column(name = "access")
    private String access;

    @Column(name = "questions_number")
    private Long questionsNumber;

}
