package net.coursework.EasyStat.repository;

import net.coursework.EasyStat.model.Question;
import net.coursework.EasyStat.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByQuestionnaireId(Long id);
}
