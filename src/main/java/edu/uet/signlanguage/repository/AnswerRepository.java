package edu.uet.signlanguage.repository;

import edu.uet.signlanguage.entity.Answer;
import edu.uet.signlanguage.entity.Question;
import edu.uet.signlanguage.entity.Sentence;
import edu.uet.signlanguage.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    Optional<Answer> findById(Integer id);

    @Query("SELECT a FROM Answer a WHERE a.question = ?1")
    List<Answer> findByQuestion(Question question);
}
