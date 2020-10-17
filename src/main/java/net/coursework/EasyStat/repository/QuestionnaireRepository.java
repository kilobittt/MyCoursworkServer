package net.coursework.EasyStat.repository;

import net.coursework.EasyStat.model.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {

    List<Questionnaire> findAllByAccess(String access);

    List<Questionnaire> findAllByOwnerId(Long id);

    List<Questionnaire> findAllByName(String name);
}
