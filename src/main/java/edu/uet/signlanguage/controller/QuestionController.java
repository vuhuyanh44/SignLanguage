package edu.uet.signlanguage.controller;

import edu.uet.signlanguage.entity.Question;
import edu.uet.signlanguage.models.ResponseObject;
import edu.uet.signlanguage.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/question")
public class QuestionController {
    @Autowired
    QuestionRepository questionRepository;

    @GetMapping("/all")
    ResponseEntity<ResponseObject> getAllQuestion(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Get all question successfully", questionRepository.findAll() )
        );
    }
    @PostMapping(consumes = {"application/json"})
    ResponseEntity<ResponseObject> addQuestion(@RequestBody Question question){
        Question question1 = new Question(question);
        questionRepository.save(question1);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Add question successfully", "")
        );
    }
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getQuestionById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Get question successfully",questionRepository.findById(id))
        );
    }
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteQuestionById(@PathVariable int id){
        questionRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Delete question" + id + "successfully", "")
        );
    }
}
