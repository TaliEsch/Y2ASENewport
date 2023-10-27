package com.example.wastemanagement.web.Form;

import com.example.wastemanagement.web.Form.Validation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Data
@NoArgsConstructor
@UniqueEditHub
public class HubForm {

    private Integer hubId;

    @NotEmpty
//    @UniqueHub
    private String hubName;

    @NotEmpty
    @URL
    private String link;

    @HubSpecialising // custom validator
    //"recycling", "composting", "landfill", "reuse", "tip", "repair", "up-cycling"
    private String whichR;

    @NotEmpty
//    @Category
    private String[] categoriesList;

    private ArrayList<String> otherCategoriesList;

    @NotEmpty
    @Address // custom validation annotation
    private String address;

    @NotEmpty
    @Postcode // custom validation annotation
    private String postcode;

    @NotEmpty
    @PhoneNumber // Annotation for PhoneNumber validator
    private String phoneNumber;

}
