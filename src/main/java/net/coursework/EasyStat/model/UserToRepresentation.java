package net.coursework.EasyStat.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users_to_representation")
@Data
public class UserToRepresentation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "respondent_id")
    private Long respondentId;

    @Column(name = "representation_id")
    private Long representationId;

    @Column(name = "questionnaire_id")
    private Long questionnaireId;
}
