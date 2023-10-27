package com.example.wastemanagement.web.Form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/* Object to contain the inputted username and password from the admin/login form
   get and post requests provided by @Data */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminLoginForm {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

}
