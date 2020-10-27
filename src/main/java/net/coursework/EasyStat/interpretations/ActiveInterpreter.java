package net.coursework.EasyStat.interpretations;

import java.util.Map;

public class ActiveInterpreter {

    private static final Long activeId = 10L;
    private static final Long passiveId = 9L;

    public static Long interpret(Map<String, Long> stat) {

        Long activeCounter = 0L;
        final Long activeLim = 9L;

        if(stat.get("АКТИВНОСТЬ") != null){
            activeCounter = stat.get("АКТИВНОСТЬ");
        }

        if(activeCounter > activeLim){
            return activeId;
        }else{
            return passiveId;
        }

    }
}
