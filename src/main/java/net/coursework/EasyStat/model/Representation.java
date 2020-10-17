package net.coursework.EasyStat.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "representations")
@Data
public class Representation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "questionnaire_id")
    private Long questionnaireId;
}
