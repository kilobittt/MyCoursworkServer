package net.coursework.EasyStat.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import net.coursework.EasyStat.model.Answer;
import net.coursework.EasyStat.model.Question;
import net.coursework.EasyStat.model.Questionnaire;
import net.coursework.EasyStat.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties
@Data
public class QuestionStatDto {
    private Long questionId;
    private Long questionnaireId;
    private Long respondentsNumber;
    private Long number;
    private String name;
    private String description;
    private String type;
    private boolean isMarked;
    private Map<String, Long> answers = new HashMap<String, Long>();
    private Map<String, Long> artifacts = new HashMap<String, Long>();

    public QuestionStatDto(List<Answer> answers, Question question){
        boolean isFirst = true;
        for(Answer answer : answers){
            if(isFirst){
                this.questionId = question.getId();
                this.respondentsNumber = (long)answers.size();
                this.name = question.getName();
                this.description = question.getDescription();
                this.type = question.getType();
                this.questionnaireId = question.getQuestionnaireId();
                this.number = question.getNumber();

                isFirst = false;
            }

            String[] stringAnswers = answer.getAnswers().split(";");
            for(String stringAnswer : stringAnswers) {
                Long counter = this.answers.get(stringAnswer);
                if (counter == null) {
                    this.answers.put(stringAnswer, 1L);
                } else {
                    this.answers.put(stringAnswer, ++counter);
                }
            }

            String[] artifacts = answer.getArtifacts().split(";");
            for(String artifact : artifacts) {
                Long counter = this.artifacts.get(artifact);
                if (counter == null) {
                    this.artifacts.put(artifact, 1L);
                } else {
                    this.artifacts.put(artifact, ++counter);
                }
            }

        }
    }
}
