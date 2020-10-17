package net.coursework.EasyStat.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import net.coursework.EasyStat.model.Question;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties
@Data
public class NewQuestionDto {

    private Long questionnaireId;
    private String name;
    private String description;
    private Long number;
    private String type;
    private List<OptionDto> options = new ArrayList<>();

    void addOption(String option){
        options.add(OptionDto.fromString(option));
    }

    public static NewQuestionDto fromQuestion(Question question){
        NewQuestionDto dto = new NewQuestionDto();
        dto.setQuestionnaireId(question.getQuestionnaireId());
        dto.setName(question.getName());
        dto.setDescription(question.getDescription());
        dto.setNumber(question.getNumber());
        dto.setType(question.getType());

        if(question.getAnswer1() != null){
            dto.addOption(question.getAnswer1());
            if(question.getAnswer2() != null){
                dto.addOption(question.getAnswer2());
                if(question.getAnswer3() != null){
                    dto.addOption(question.getAnswer3());
                    if(question.getAnswer4() != null){
                        dto.addOption(question.getAnswer4());
                        if(question.getAnswer5() != null){
                            dto.addOption(question.getAnswer5());
                            if(question.getAnswer6() != null){
                                dto.addOption(question.getAnswer6());
                                if(question.getAnswer7() != null){
                                    dto.addOption(question.getAnswer7());
                                    if(question.getAnswer8() != null){
                                        dto.addOption(question.getAnswer8());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }//Сложный if из-за незнания как хранить массивы в БД

        return dto;
    }

    public Question toQuestion(){
        Question question = new Question();

        question.setQuestionnaireId(questionnaireId);
        question.setName(name);
        question.setDescription(description);
        question.setNumber(number);
        question.setType(type);

        int size = options.size();

        if(size >= 1){
            question.setAnswer1(options.get(0).getArtifact() + "/" + options.get(0).getAnswer());
            if(size >= 2){
                question.setAnswer2(options.get(1).getArtifact() + "/" + options.get(1).getAnswer());
                if(size >= 3){
                    question.setAnswer3(options.get(2).getArtifact() + "/" + options.get(2).getAnswer());
                    if(size >= 4){
                        question.setAnswer4(options.get(3).getArtifact() + "/" + options.get(3).getAnswer());
                        if(size >= 5){
                            question.setAnswer5(options.get(4).getArtifact() + "/" + options.get(4).getAnswer());
                            if(size >= 6){
                                question.setAnswer6(options.get(5).getArtifact() + "/" + options.get(5).getAnswer());
                                if(size >= 7){
                                    question.setAnswer7(options.get(6).getArtifact() + "/" + options.get(6).getAnswer());
                                    if(size >= 8){
                                        question.setAnswer8(options.get(7).getArtifact() + "/" + options.get(7).getAnswer());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return question;
    }
}
