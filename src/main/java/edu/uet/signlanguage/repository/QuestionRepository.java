package edu.uet.signlanguage.repository;

import edu.uet.signlanguage.entity.Question;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    Optional<Question> findById(Integer id);
}
