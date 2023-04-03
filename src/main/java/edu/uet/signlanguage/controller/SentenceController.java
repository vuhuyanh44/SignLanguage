package edu.uet.signlanguage.controller;

import edu.uet.signlanguage.entity.Sentence;
import edu.uet.signlanguage.entity.User;
import edu.uet.signlanguage.models.ResponseObject;
import edu.uet.signlanguage.repository.SentenceRepository;
import edu.uet.signlanguage.repository.UserRepository;
import edu.uet.signlanguage.security.jwt.AuthTokenFilter;
import edu.uet.signlanguage.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/sentence")
public class SentenceController {
    @Autowired
    SentenceRepository sentenceRepository;
    @Autowired
    AuthTokenFilter authTokenFilter;

    @Autowired
    UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("")
    ResponseEntity<ResponseObject> addSentence(@RequestBody Sentence sentence, @RequestAttribute("userID") int id ){
//        String jwt = authTokenFilter.parseJwt((jakarta.servlet.http.HttpServletRequest) request);
//        System.out.println(jwt);
//        int userId = 2; //jwtUtils.getUserIdFromJwtToken(jwt);
        Sentence sentenceEntity = new Sentence(sentence);
        sentenceEntity.setViewTime(new Timestamp(System.currentTimeMillis()));
        sentenceEntity.setUser(userRepository.findById(id).orElse(null));
        sentenceRepository.save(sentenceEntity);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","add sentence successfully","")
        );
    }
    @PostMapping("/phrased")
    ResponseEntity<ResponseObject> phraseSentence(@RequestBody Sentence sentence){
        Sentence sentence1 = new Sentence(sentence);
        String input = sentence1.getContent();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            // Kiểm tra ký tự có phải là ký tự Tiếng Việt không
            if ((c >= '\u0041' && c <= '\u005A') || (c >= '\u0061' && c <= '\u007A') || (c >= '\u00C0' && c <= '\u1EF9')) {
                result.add(String.valueOf(c));
            }
            // Nếu là ký tự có dấu thì lấy luôn 2 ký tự
            else if (c >= '\u00C0' && c <= '\u1EF9') {
                result.add(input.substring(i, i + 2));
                i++;
            }
        }
//        result.toArray(new String[0]);
        for (int i = 0; i < result.size(); i++){
            result.set(i,result.get(i).toLowerCase() + ".png");
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","phrased", result)
        );
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getById(@PathVariable int id){
        Sentence sentence = sentenceRepository.findById(id).orElse(null);
        if(sentence != null){
            sentenceRepository.save(sentence);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK","Get sentence " + id + " successfully",sentence )
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("OK","Can't find sentence " + id,"" )
        );
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deletedSentenceById(@PathVariable int id){
        Sentence sentence = sentenceRepository.findById(id).orElse(null);
        if(sentence != null){
            sentenceRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK","Deleted sentence " + id + " successfully","" )
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("OK","Can't find sentence " + id,"" )
        );
    }
    @GetMapping("/all")
    ResponseEntity<ResponseObject> getAllSentence(@RequestAttribute("userID") int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Get all sentence successfully",sentenceRepository.findByUser(userRepository.findById(id).orElse(null)))
        );
    }

    @GetMapping("/favour")
    ResponseEntity<ResponseObject> getFavouriteSentence(@RequestAttribute("userID") int id){
        User user = userRepository.findById(id).orElse(null);
        System.out.println(user.getId()+user.getUsername());

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Get favourite list successfully", sentenceRepository.findByUserAndFavor(user,false))
        );
    }
}
