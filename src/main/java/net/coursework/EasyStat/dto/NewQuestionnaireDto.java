package net.coursework.EasyStat.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import net.coursework.EasyStat.model.Question;
import net.coursework.EasyStat.model.Questionnaire;

@JsonIgnoreProperties
@Data
public class NewQuestionnaireDto {
    private String name;
    private String description;
    private String type;
    private Long ownerId;
    private boolean isMarked;
    private Long questionsNumber;

    public static NewQuestionnaireDto fromQuestionnaire(Questionnaire questionnaire){
        NewQuestionnaireDto dto = new NewQuestionnaireDto();
        dto.name = questionnaire.getName();
        dto.description = questionnaire.getDescription();
        dto.type = questionnaire.getType();
        dto.ownerId = questionnaire.getOwnerId();
        dto.questionsNumber = questionnaire.getQuestionsNumber();
        dto.isMarked = questionnaire.isMarked();

        return dto;
    }

    public Questionnaire toQuestionnaire(){
        Questionnaire questionnaire = new Questionnaire();

        questionnaire.setName(name);
        questionnaire.setDescription(description);
        questionnaire.setType(type);
        questionnaire.setOwnerId(ownerId);
        questionnaire.setQuestionsNumber(questionsNumber);
        questionnaire.setMarked(isMarked);

        return questionnaire;
    }
}
