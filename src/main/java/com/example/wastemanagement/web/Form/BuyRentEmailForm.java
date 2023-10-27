package com.example.wastemanagement.web.Form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyRentEmailForm {

    private Long itemId;

    @NotEmpty
    private String message;

    @NotEmpty
    @Email
    private String emailAddress;

}
