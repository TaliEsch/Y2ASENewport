package com.example.wastemanagement.service.Dto;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class AccountDto {
    private Integer account_Id;
    private String username;
    private String password;
    private String user_Role;
}
