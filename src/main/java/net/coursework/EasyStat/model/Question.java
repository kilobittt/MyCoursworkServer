package net.coursework.EasyStat.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
@Data
public class Question extends BaseQuestionEntity{

    @Column(name = "number")
    private Long number;

    @Column(name = "answer1")
    private String answer1;

    @Column(name = "answer2")
    private String answer2;

    @Column(name = "answer3")
    private String answer3;

    @Column(name = "answer4")
    private String answer4;

    @Column(name = "answer5")
    private String answer5;

    @Column(name = "answer6")
    private String answer6;

    @Column(name = "answer7")
    private String answer7;

    @Column(name = "answer8")
    private String answer8;

    @Column(name = "questionnaire_id")
    private Long questionnaireId;
}
