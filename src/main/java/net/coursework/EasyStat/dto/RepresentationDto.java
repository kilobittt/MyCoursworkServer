package net.coursework.EasyStat.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import net.coursework.EasyStat.model.Representation;

@JsonIgnoreProperties
@Data
public class RepresentationDto {
    Long id;
    private String name;
    private String description;
    Long questionnaireId;

    public Representation toRepresentation(){
        Representation representation = new Representation();
        representation.setId(id);
        representation.setName(name);
        representation.setDescription(description);
        representation.setQuestionnaireId(questionnaireId);

        return representation;
    }
}
