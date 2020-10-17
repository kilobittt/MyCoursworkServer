package net.coursework.EasyStat.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import liquibase.pro.packaged.A;
import liquibase.pro.packaged.S;
import lombok.Data;
import net.coursework.EasyStat.model.Answer;

import java.util.List;

@JsonIgnoreProperties
@Data
public class AnswerDto {

    private List<OptionDto> options;
    private Long questionId;
    private Long respondentId;

    public Answer toAnswer(){
        Answer answer = new Answer();

        String answers = "";
        String artifacts = "";
        boolean isFirst = true;
        for(OptionDto option : options){

            if(!isFirst){
                answers = answers.concat(";");
                artifacts = artifacts.concat(";");
            }
            isFirst = false;

            answers = answers.concat(option.getAnswer());
            artifacts = artifacts.concat(option.getArtifact());
        }
        answer.setAnswers(answers);
        answer.setArtifacts(artifacts);
        answer.setQuestionId(this.questionId);
        answer.setRespondentId(this.respondentId);

        return answer;
    }
}
