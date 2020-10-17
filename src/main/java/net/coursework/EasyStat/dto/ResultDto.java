package net.coursework.EasyStat.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import net.coursework.EasyStat.interpretations.EasyTestInterpreter;
import net.coursework.EasyStat.interpretations.SuperHeroInterpreter;
import net.coursework.EasyStat.model.Answer;

import java.util.List;

@JsonIgnoreProperties
@Data
public class ResultDto {
    String mainMessage;
    String description;
}
