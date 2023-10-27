package com.example.wastemanagement.web.Form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class indexForm {
    @NotEmpty(message = "Please enter a Description")
    private String description;
    @NotNull
    private MultipartFile image1;
    @NotNull
    private MultipartFile image2;
    @NotNull
    private MultipartFile image3;
    @NotNull
    private MultipartFile image4;

}
