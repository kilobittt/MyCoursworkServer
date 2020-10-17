package net.coursework.EasyStat.model;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public class BaseQuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;
}
