package com.example.wastemanagement.web.Form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminCreationForm {

    private String username;
    private String password;
    private String confirmPassword;
    private Boolean ENABLED = true;
    private String user_Role;

}
