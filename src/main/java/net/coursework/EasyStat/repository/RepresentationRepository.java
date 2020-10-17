package net.coursework.EasyStat.repository;

import net.coursework.EasyStat.model.Representation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepresentationRepository extends JpaRepository<Representation, Long> {
    Representation findByQuestionnaireIdAndName(Long questionnaireId, String name);
}
