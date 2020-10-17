package net.coursework.EasyStat.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties
@Data
public class OptionDto {
    private String artifact;
    private String answer;

    public static OptionDto fromString(String answer){
        OptionDto result = new OptionDto();
        if(answer.contains("/")){
            String[] option = answer.split("/");
            result.setArtifact(option[0]);
            result.setAnswer(option[1]);
        }else{
            result.setAnswer(answer);
        }

        return result;
    }
}
