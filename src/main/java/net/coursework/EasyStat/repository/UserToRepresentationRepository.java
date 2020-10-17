package net.coursework.EasyStat.repository;

import net.coursework.EasyStat.model.UserToRepresentation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserToRepresentationRepository extends JpaRepository<UserToRepresentation, Long> {
    List<UserToRepresentation> findAllByQuestionnaireId(Long questionnaireId);
}
