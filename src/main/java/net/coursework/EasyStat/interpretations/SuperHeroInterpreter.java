package net.coursework.EasyStat.interpretations;

import net.coursework.EasyStat.dto.ResultDto;
import net.coursework.EasyStat.model.Answer;

import java.util.List;
import java.util.Map;

public class SuperHeroInterpreter extends BaseInterpreter{



    private static final Long batManId = 2L;
    private static final Long superManId = 6L;
    private static final Long superWomanId = 7L;
    private static final Long robocopId = 4L;
    private static final Long ironManId = 5L;
    private static final Long catWomanId = 3L;

    public static Long interpret(Map<String, Long> stat){
        String importantArtifact = "";
        Long maxValue = 0L;
        for(Map.Entry<String, Long> variant : stat.entrySet()){
            if(variant.getValue() > maxValue){
                importantArtifact = variant.getKey();
                maxValue = variant.getValue();
            }
        }

        Long representationId = 0L;
        switch(importantArtifact){
            case "BatMan":{
                representationId = batManId;
                break;
            }
            case "SuperMan":{
                representationId = superManId;
                break;
            }
            case "SuperWoman":{
                representationId = superWomanId;
                break;
            }
            case "Robocop":{
                representationId = robocopId;
                break;
            }
            case "IronMan":{
                representationId = ironManId;
                break;
            }
            case "CatWoman":{
                representationId = catWomanId;
                break;
            }
        }

        return representationId;
    }
}
