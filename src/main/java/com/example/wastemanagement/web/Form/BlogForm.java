package com.example.wastemanagement.web.Form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogForm {
//@notEmpty is only on required fields
    @NotEmpty(message = "Please enter a title")
    private String blogTitle;

    private String username;

    @NotEmpty
    @Email
    private String email;

    String itemName;

    String itemCategory;
    MultipartFile itemImage;

    String blogContact;

    @NotEmpty
    String itemDescription;
}

