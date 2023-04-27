package edu.uet.signlanguage.entity;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String email;
    private String phone;
    private String newPassword;
    private String oldPassword;
    private String rePassword;
}
