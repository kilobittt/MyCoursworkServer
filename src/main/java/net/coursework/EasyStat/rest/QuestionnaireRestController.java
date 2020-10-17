package net.coursework.EasyStat.rest;

import net.coursework.EasyStat.dto.*;
import net.coursework.EasyStat.model.Answer;
import net.coursework.EasyStat.model.Question;
import net.coursework.EasyStat.model.Questionnaire;
import net.coursework.EasyStat.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/questionnaire")
public class QuestionnaireRestController {

    private final QuestionnaireService questionnaireService;

    @Autowired
    public QuestionnaireRestController(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<QuestionnaireDto>> getAllQuestionnaire(@RequestParam(name = "access") String access){
        List<Questionnaire> questionnaires = questionnaireService.getAllQuestionnaires(access);

        if(questionnaires == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<QuestionnaireDto> result = new ArrayList<>();
        for(Questionnaire questionnaire : questionnaires){
            result.add(QuestionnaireDto.fromQuestionnaire(questionnaire));
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<List<QuestionnaireDto>> getQuestionnairesByUserId(@PathVariable(name = "id") Long id) {
        List<Questionnaire> questionnaires = questionnaireService.getQuestionnairesByUserId(id);

        if(questionnaires == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<QuestionnaireDto> result = new ArrayList<>();
        for(Questionnaire questionnaire : questionnaires){
            result.add(QuestionnaireDto.fromQuestionnaire(questionnaire));
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<QuestionnaireDto> getQuestionnaireById(@PathVariable(name = "id") Long id){
        Questionnaire questionnaire = questionnaireService.getQuestionnaireById(id);

        if(questionnaire == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        QuestionnaireDto result = QuestionnaireDto.fromQuestionnaire(questionnaire);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<QuestionnaireDto>> getQuestionnaireByName(@RequestParam(name = "name") String name) {
        List<Questionnaire> questionnaires = questionnaireService.getQuestionnairesByName(name);

        if(questionnaires == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<QuestionnaireDto> result = new ArrayList<>();
        for(Questionnaire questionnaire : questionnaires){
            result.add(QuestionnaireDto.fromQuestionnaire(questionnaire));
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("questions/{id}")
    public ResponseEntity<List<QuestionDto>> getQuestionsForQuestionnaire(@PathVariable(name = "id") Long questionnaireId){
        List<Question> questions = questionnaireService.getQuestionsByQuestionnaireId(questionnaireId);

        if(questions == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<QuestionDto> result = new ArrayList<>();
        for(Question question : questions){
            result.add(QuestionDto.fromQuestion(question));
        }


        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ResultDto> sendAnswer(@RequestBody List<AnswerDto> answers){
        List<Answer> userAnswers = new ArrayList<>();

        for(AnswerDto answer : answers){
            userAnswers.add(answer.toAnswer());
        }

        ResultDto result = questionnaireService.sendAnswer(userAnswers);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/stat/{id}")
    public ResponseEntity<List<QuestionnaireStatDto>> getQuestionnaireWithStatByUserId(@PathVariable(name = "id") Long id){
        List<QuestionnaireStatDto> result = questionnaireService.getQuestionnairesStatByUserId(id);

        if(result == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/stat/question/{id}")
    public ResponseEntity<QuestionStatDto> getQuestionStat(@PathVariable(name = "id") Long id){
        QuestionStatDto result = questionnaireService.getQuestionStatById(id);

        if(result == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Long> saveNewQuestionnaire(@RequestBody NewQuestionnaireDto newQuestionnaire){
        Questionnaire inputQuestionnaire = newQuestionnaire.toQuestionnaire();
        inputQuestionnaire.setAccess("PRIVATE");
        Questionnaire createdQuestionnaire = questionnaireService.createNewQuestionnaire(inputQuestionnaire);

        if(createdQuestionnaire == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(createdQuestionnaire.getId(), HttpStatus.OK);
    }

    @PostMapping(value = "/new/questions")
    public ResponseEntity<String> saveNewQuestionnaire(@RequestBody List<NewQuestionDto> newQuestions){

        List<Question> questions = new ArrayList<>();

        for(NewQuestionDto questionDto : newQuestions){
            questions.add(questionDto.toQuestion());
        }

        questionnaireService.createQuestionsForQuestionnaire(questions);

        return new ResponseEntity<>("Successfully", HttpStatus.OK);
    }
}
