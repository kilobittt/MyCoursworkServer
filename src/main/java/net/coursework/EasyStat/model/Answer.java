package net.coursework.EasyStat.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "answers")
@Data
public class Answer extends BaseAnswerEntity{

    @Column(name = "artifacts")
    private String artifacts;

    @Column(name = "answers")
    private String answers;

    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "questionnaire_id")
    private Long questionnaireId;

    @Column(name = "respondent_id")
    private Long respondentId;
}
