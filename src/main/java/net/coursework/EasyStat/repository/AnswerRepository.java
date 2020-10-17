package net.coursework.EasyStat.repository;

import net.coursework.EasyStat.model.Answer;
import net.coursework.EasyStat.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByQuestionnaireId(Long questionnaireId);

    List<Answer> findAllByQuestionId(Long questionId);
}
