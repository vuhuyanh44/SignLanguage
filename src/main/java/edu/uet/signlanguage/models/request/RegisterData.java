package edu.uet.signlanguage.models.request;

import lombok.Data;

import java.util.Set;

@Data
public class RegisterData {
    private int id;
    private String username;
    private String password;
    private String rePassword;

    private String email;
    private String phone;
    private Set<Integer> roles;

}
