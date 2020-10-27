package net.coursework.EasyStat.service;

import net.coursework.EasyStat.dto.QuestionStatDto;
import net.coursework.EasyStat.dto.QuestionnaireStatDto;
import net.coursework.EasyStat.dto.ResultDto;
import net.coursework.EasyStat.model.Answer;
import net.coursework.EasyStat.model.Question;
import net.coursework.EasyStat.model.Questionnaire;


import java.util.List;

public interface QuestionnaireService {

    List<Questionnaire> getAllQuestionnaires(String access);

    List<Questionnaire> getQuestionnairesByUserId(Long userId);

    Questionnaire getQuestionnaireById(Long questionnaireId);

    List<Questionnaire> getQuestionnairesByName(String questionnaireName);

    List<Question> getQuestionsByQuestionnaireId(Long questionnaireId);

    ResultDto sendAnswer(List<Answer> answers);

    List<QuestionnaireStatDto> getQuestionnairesStatByUserId(Long userId);

    QuestionStatDto getQuestionStatById(Long id);

    Questionnaire createNewQuestionnaire(Questionnaire questionnaire);

    void createQuestionsForQuestionnaire(List<Question> questions);
}
