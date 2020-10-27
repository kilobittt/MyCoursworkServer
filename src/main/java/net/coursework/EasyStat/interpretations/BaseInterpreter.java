package net.coursework.EasyStat.interpretations;

import liquibase.pro.packaged.S;
import net.coursework.EasyStat.dto.ResultDto;
import net.coursework.EasyStat.model.Answer;
import net.coursework.EasyStat.model.Representation;
import net.coursework.EasyStat.repository.UserRepository;
import net.coursework.EasyStat.repository.UserToRepresentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BaseInterpreter {

    private static Map<String, Long> getStat(List<Answer> answers){
        Map<String, Long> stat = new HashMap<>();
        for(Answer answer : answers){
            for(String artifact : answer.getArtifacts().split(";")) {
                Long counter = stat.get(artifact);
                if (counter == null) {
                    stat.put(artifact, 1L);
                } else {
                    stat.put(artifact, ++counter);
                }
            }
        }

        return stat;
    }

    public static Long getRepresentation(List<Answer> answers, Long questionnaireId){
        Map<String, Long> stat = getStat(answers);
        switch (questionnaireId.intValue()) {
            case 1:{
                return EasyTestInterpreter.interpret(stat);
            }
            case 2:{
                return SuperHeroInterpreter.interpret(stat);
            }
            default: {
                return null;
            }
        }
    }

}
