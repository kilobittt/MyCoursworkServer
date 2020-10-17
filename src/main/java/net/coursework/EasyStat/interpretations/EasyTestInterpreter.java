package net.coursework.EasyStat.interpretations;

import net.coursework.EasyStat.dto.ResultDto;
import net.coursework.EasyStat.model.Answer;
import net.coursework.EasyStat.repository.UserToRepresentationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class EasyTestInterpreter extends BaseInterpreter{

    private static final Long trueAnswerId = 1L;
    private static final Long falseAnswerId = 8L;

    public static Long interpret(Map<String, Long> stat){
        Long trueAnswerCounter = 0L;
        Long falseAnswerCounter = 0L;

        if(stat.get("ВЕРНЫЙ") != null){
            trueAnswerCounter = stat.get("ВЕРНЫЙ");
        }

        if(stat.get("НЕ_ВЕРНЫЙ") != null){
            falseAnswerCounter = stat.get("НЕ_ВЕРНЫЙ");
        }

        if(trueAnswerCounter > falseAnswerCounter){
            return trueAnswerId;
        }

        if(falseAnswerCounter > trueAnswerCounter){
            return falseAnswerId;
        }

        return null;
    }
}
