package edu.uet.signlanguage.controller;

import edu.uet.signlanguage.entity.Answer;
import edu.uet.signlanguage.entity.Question;
import edu.uet.signlanguage.models.ResponseObject;
import edu.uet.signlanguage.repository.AnswerRepository;
import edu.uet.signlanguage.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/answer")
public class AnswerController {
    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionRepository questionRepository;

    @GetMapping("/all")
    ResponseEntity<ResponseObject> getAllAnswer(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Get all question successfully", answerRepository.findAll() )
        );
    }
    @PostMapping()
    ResponseEntity<ResponseObject> addAnswer(@RequestBody Answer answer,@RequestParam("question_id") int id){
        Answer answer1 = new Answer(answer);
        Question question = questionRepository.findById(id).orElse(null);
        answer1.setQuestion(question);
        answerRepository.save(answer1);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Add question successfully", "")
        );
    }
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getAnswerById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Get question successfully",answerRepository.findById(id))
        );
    }
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteAnswerById(@PathVariable int id){
        answerRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Delete question" + id + "successfully", "")
        );
    }

    @GetMapping("/answer")
    ResponseEntity<ResponseObject> findAnswerByQuestion(@RequestParam("question_id") int id){
        Question question = questionRepository.findById(id).orElse(null);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Get answer successfully", answerRepository.findByQuestion(question))
        );
    }
}
