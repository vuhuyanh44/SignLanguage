package edu.uet.signlanguage.controller;

import edu.uet.signlanguage.entity.Role;
import edu.uet.signlanguage.models.ResponseObject;
import edu.uet.signlanguage.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(path = "api/role")
public class RoleController {
    @Autowired
    RoleRepository roleRepository;

    @PostMapping("")
    ResponseEntity<ResponseObject> addRole(@RequestBody Role role){
        Role role1 = new Role(role);
        roleRepository.save(role1);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Add role successfully","")
        );
    }
    @GetMapping("")
    ResponseEntity<ResponseObject> getcl(@RequestParam("rolename") String name){
//        Set<Role> roleByName = new HashSet<>();


        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","đay", roleRepository.findByName(name).stream())
        );
    }
}
