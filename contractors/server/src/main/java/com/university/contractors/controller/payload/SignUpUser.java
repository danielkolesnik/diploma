package com.university.contractors.controller.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpUser {

    private String username;

    private String password;

    private String confirmationPassword;

}
