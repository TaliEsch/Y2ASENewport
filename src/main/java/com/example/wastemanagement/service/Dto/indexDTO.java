package com.example.wastemanagement.service.Dto;

import lombok.AllArgsConstructor;
import lombok.Value;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Value
@AllArgsConstructor
public class indexDTO {
    private Long itemId;
    private String description;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
}
