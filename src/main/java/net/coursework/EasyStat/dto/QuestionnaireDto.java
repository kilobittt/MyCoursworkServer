package net.coursework.EasyStat.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import net.coursework.EasyStat.model.Questionnaire;

@JsonIgnoreProperties
@Data
public class QuestionnaireDto {

    private Long id;
    private String name;
    private String description;
    private String type;
    private Long ownerId;
    private boolean isRepresented;
    private boolean isMarked;
    private String access;
    private Long questionsNumber;

    public static QuestionnaireDto fromQuestionnaire(Questionnaire questionnaire){
        QuestionnaireDto dto = new QuestionnaireDto();
        dto.id = questionnaire.getId();
        dto.name = questionnaire.getName();
        dto.description = questionnaire.getDescription();
        dto.type = questionnaire.getType();
        dto.ownerId = questionnaire.getOwnerId();
        dto.isRepresented = questionnaire.isRepresented();
        dto.access = questionnaire.getAccess();
        dto.questionsNumber = questionnaire.getQuestionsNumber();

        return dto;
    }

    public Questionnaire toQuestionnaire(){
        Questionnaire questionnaire = new Questionnaire();

        questionnaire.setId(id);
        questionnaire.setName(name);
        questionnaire.setDescription(description);
        questionnaire.setType(type);
        questionnaire.setOwnerId(ownerId);
        questionnaire.setRepresented(isRepresented);
        questionnaire.setAccess(access);
        questionnaire.setQuestionsNumber(questionsNumber);

        return questionnaire;
    }
}
