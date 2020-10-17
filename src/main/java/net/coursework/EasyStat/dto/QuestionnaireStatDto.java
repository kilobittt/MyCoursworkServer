package net.coursework.EasyStat.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import net.coursework.EasyStat.model.Answer;
import net.coursework.EasyStat.model.Questionnaire;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@JsonIgnoreProperties
@Data
public class QuestionnaireStatDto {

    private Long id;
    private String name;
    private String description;
    private String type;
    private String ownerName;
    private Long ownerId;
    private boolean isRepresented;
    private boolean isMarked;
    private String access;
    private Long questionsNumber;
    private Long respondentsNumber;
    private Map<String, Long> artifactStat = new HashMap<>();
    private Map<String, Long> representationStat = new HashMap<>();

    public QuestionnaireStatDto(Questionnaire questionnaire){
        this.id = questionnaire.getId();
        this.name = questionnaire.getName();
        this.description = questionnaire.getDescription();
        this.type = questionnaire.getType();
        this.ownerId = questionnaire.getOwnerId();
        this.isRepresented = questionnaire.isRepresented();
        this.isMarked = questionnaire.isMarked();
        this.access = questionnaire.getAccess();
        this.questionsNumber = questionnaire.getQuestionsNumber();
    }


    public void setRepresentationsStats(List<String> representations){
        if(this.isRepresented) {
            for (String representation : representations) {
                Long counter = this.representationStat.get(representation);
                if (counter == null) {
                    this.representationStat.put(representation, 1L);
                } else {
                    this.representationStat.put(representation, ++counter);
                }
            }
        }
    }

    public void setArtifactStats(List<Answer> answers){
        if(this.isMarked) {
            for (Answer answer : answers) {
                for (String artifact : answer.getArtifacts().split(";")) {
                    Long counter = this.artifactStat.get(artifact);
                    if (counter == null) {
                        this.artifactStat.put(artifact, 1L);
                    } else {
                        this.artifactStat.put(artifact, ++counter);
                    }
                }
            }
        }
    }
}
