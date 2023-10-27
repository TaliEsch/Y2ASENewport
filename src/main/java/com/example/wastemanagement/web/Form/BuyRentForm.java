package com.example.wastemanagement.web.Form;

import com.example.wastemanagement.web.Form.Validation.Price;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.text.DecimalFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Buy_Rent")
public class BuyRentForm {
    @NotEmpty
    private String itemName;
    @NotEmpty
    private String username;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String description;
    @NotEmpty
    // lombok price constraint
    @Price
    private String price;
    @NotEmpty
    private String category;
    @NotEmpty
    // has to be either Sell or Rent
    @Pattern(regexp = "^(Sell|Rent - Per Week)$", message = "Invalid type, please enter sell or Rent - Per Week)")
    private String itemSaleType;
    private MultipartFile image;

}