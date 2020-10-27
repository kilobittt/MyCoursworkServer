package net.coursework.EasyStat.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.coursework.EasyStat.dto.QuestionStatDto;
import net.coursework.EasyStat.dto.QuestionnaireStatDto;
import net.coursework.EasyStat.dto.ResultDto;
import net.coursework.EasyStat.interpretations.BaseInterpreter;
import net.coursework.EasyStat.model.*;
import net.coursework.EasyStat.repository.*;
import net.coursework.EasyStat.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private final AnswerRepository answerRepository;
    private final QuestionnaireRepository questionnaireRepository;
    private final QuestionRepository questionRepository;
    private final RepresentationRepository representationRepository;
    private final UserToRepresentationRepository userToRepresentationRepository;
    private final UserRepository userRepository;

    @Autowired
    public QuestionnaireServiceImpl(AnswerRepository answerRepository,
                                    QuestionnaireRepository questionnaireRepository,
                                    QuestionRepository questionRepository,
                                    RepresentationRepository representationRepository,
                                    UserToRepresentationRepository userToRepresentationRepository,
                                    UserRepository userRepository) {
        this.answerRepository = answerRepository;
        this.questionnaireRepository = questionnaireRepository;
        this.questionRepository = questionRepository;
        this.representationRepository = representationRepository;
        this.userToRepresentationRepository = userToRepresentationRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Questionnaire> getAllQuestionnaires(String access) {
        System.out.println("ПОПАЛИ КУДА НАДО С АКСЕСОМ " + access);
        List<Questionnaire> result;
        if(access.equals("ALL")){
            result = questionnaireRepository.findAll();
            System.out.println("ОТРАБОТАЛ FIND_ALL");
        }else {
            result = questionnaireRepository.findAllByAccess(access);
            System.out.println("ОТРАБОТАЛ FIND_BY_ACCESS");
        }
        log.info("IN getAllPublicQuestionnaires - {} questionnaire found", result.size());
        return result;
    }

    @Override
    public List<Questionnaire> getQuestionnairesByUserId(Long userId) {
        List<Questionnaire> result = questionnaireRepository.findAllByOwnerId(userId);
        log.info("IN getQuestionnairesByUserId - {} questionnaire found", result.size());
        return result;
    }

    @Override
    public Questionnaire getQuestionnaireById(Long questionnaireId) {
        Questionnaire result = questionnaireRepository.findById(questionnaireId).orElse(null);
        log.info("IN getQuestionnaireById - {} questionnaire found by id {}", result, questionnaireId);
        return result;
    }

    @Override
    public List<Questionnaire> getQuestionnairesByName(String questionnaireName) {
        List<Questionnaire> result = questionnaireRepository.findAllByName(questionnaireName);
        log.info("IN getQuestionnairesByName - {} questionnaire found", result.size());
        return result;
    }

    @Override
    public List<Question> getQuestionsByQuestionnaireId(Long questionnaireId) {
        List<Question> result = questionRepository.findAllByQuestionnaireId(questionnaireId);
        log.info("IN getQuestionsByQuestionnaireId - {} questions found", result.size());
        return result;
    }

    @Override
    public ResultDto sendAnswer(List<Answer> answers) {
        if(answers == null){
            log.info("IN sendAnswer - [in]answers is NULL");
            return null;
        }

        Long questionnaireId = null;
        //TODO зарезервировать id незарегистрированного пользователя
        Long respondentId = null;
        boolean isFirst = true;
        for(Answer answer : answers){
            if(isFirst){
                Question question = questionRepository.findById(answer.getQuestionId()).orElse(null);
                if(question == null){
                    log.info("IN sendAnswer - can't find question by questionId {} for answer with id {}",
                            answer.getQuestionId(),
                            answer.getId());
                    return null;
                }

                Questionnaire questionnaire =
                        questionnaireRepository
                                .findById(question.getQuestionnaireId())
                                .orElse(null);
                if(questionnaire == null){
                    log.info("IN sendAnswer - can't find questionnaire by questionnaireId {} for answer with id {}",
                            answer.getQuestionId(),
                            answer.getId());
                    return null;
                }
                questionnaireId = questionnaire.getId();

                User respondent = userRepository.findById(answer.getRespondentId()).orElse(null);
                if(respondent == null){
                    log.info("IN sendAnswer - can't find user by respondentId {} for answer with id {}",
                            answer.getRespondentId(),
                            answer.getId());
                    return null;
                }
                respondentId = respondent.getId();

                isFirst = false;
            }

            answer.setQuestionnaireId(questionnaireId);

            answerRepository.save(answer);
        }


        Long representationId = BaseInterpreter.getRepresentation(answers,questionnaireId);
        if(representationId == null){
            log.info("IN sendAnswer - can't represent answers {} ",
                    answers);
            return null;
        }

        Representation representation = representationRepository.findById(representationId).orElse(null);
        if(representation == null){
            log.info("IN sendAnswer - can't find representation by representationId {}",
                    representationId);
            return null;
        }

        UserToRepresentation userToRepresentation = new UserToRepresentation();
        userToRepresentation.setRepresentationId(representationId);
        userToRepresentation.setRespondentId(respondentId);
        userToRepresentation.setQuestionnaireId(questionnaireId);

        userToRepresentationRepository.save(userToRepresentation);

        ResultDto result = new ResultDto();
        result.setMainMessage(representation.getName());
        result.setDescription(representation.getDescription());

        log.info("IN sendAnswer - answer successfully send");
        return result;
    }

    @Override
    public List<QuestionnaireStatDto> getQuestionnairesStatByUserId(Long userId) {
        List<Questionnaire> questionnaires = questionnaireRepository.findAllByOwnerId(userId);
        List<QuestionnaireStatDto> result = new ArrayList<>();
        for(Questionnaire questionnaire : questionnaires){
            QuestionnaireStatDto statDto = new QuestionnaireStatDto(questionnaire);

            User owner = userRepository.findById(questionnaire.getOwnerId()).orElse(null);
            if(owner != null) {
                statDto.setOwnerName(owner.getUsername());
            }else{
                statDto.setOwnerName("UNKNOWN_USER");
            }

            List<Answer> answers = answerRepository.findAllByQuestionnaireId(questionnaire.getId());
            statDto.setRespondentsNumber(answers.size() / questionnaire.getQuestionsNumber());
            System.out.println("ОН ВИДИТ ТОЛЬКО " + answers.size() + " ОТВЕТОВ НА ЭТОТ ОПРОСНИК");
            System.out.println("И ВИДИТ ЧТО В ОПРОСНИКЕ" + questionnaire.getQuestionsNumber() + "ОТВЕТОВ");
            System.out.println("ТЕПЕРЬ В СТАТ ДТО РЕСПОНДЕНТОВ" + statDto.getRespondentsNumber());
            if(questionnaire.isMarked()) {
                statDto.setArtifactStats(answers);
            }

            if(questionnaire.isRepresented()) {
                List<UserToRepresentation> usersToRepresentations =
                        userToRepresentationRepository.findAllByQuestionnaireId(questionnaire.getId());
                List<String> representationsNames = new ArrayList<>();
                for(UserToRepresentation userToRepresentation : usersToRepresentations){
                    Representation representation =
                            representationRepository
                                    .findById(userToRepresentation.getRepresentationId())
                                    .orElse(null);
                    if(representation == null){
                        log.info("IN getQuestionnairesStatByUserId - can't find representation by representationId {}" +
                                " for isRepresented questionnaire with id {}",
                                userToRepresentation.getRepresentationId(),
                                questionnaire.getId());
                        return null;
                    }
                    representationsNames.add(representation.getName());
                }
                statDto.setRepresentationsStats(representationsNames);
            }
            System.out.println("А НА ВЫХОД В СТАТ ДТО РЕСПОНДЕНТОВ" + statDto.getRespondentsNumber());
            result.add(statDto);
        }

        log.info("IN getQuestionnairesStatByUserId - {} questionnaire found", result.size());
        return result;
    }

    @Override
    public QuestionStatDto getQuestionStatById(Long id) {

        List<Answer> answers = answerRepository.findAllByQuestionId(id);
        Question question = questionRepository.findById(id).orElse(null);
        if(question == null){
            log.info("IN getQuestionStatById - can't find question with id {}", id);
            return null;
        }
        QuestionStatDto questionStat = new QuestionStatDto(answers, question);

        Questionnaire questionnaire
                = questionnaireRepository
                .findById(question.getQuestionnaireId())
                .orElse(null);
        if(questionnaire == null){
            log.info("IN getQuestionStatById - can't find questionnaire by questionnaireId {}" +
                    " for question by id {}",
                    question.getQuestionnaireId(),
                    id);
            return null;
        }
        questionStat.setMarked(questionnaire.isMarked());

        log.info("IN getQuestionStatById - stat for question with id {} successfully generated", id);
        return questionStat;
    }

    @Override
    public Questionnaire createNewQuestionnaire(Questionnaire questionnaire) {
        questionnaireRepository.save(questionnaire);
        log.info("IN createNewQuestionnaire - new questionnaire {} successfully saved", questionnaire);
        return questionnaire;
    }

    @Override
    public void createQuestionsForQuestionnaire(List<Question> questions) {
        questionRepository.saveAll(questions);
        log.info("IN createQuestionsForQuestionnaire - new questions successfully saved");
    }
}
