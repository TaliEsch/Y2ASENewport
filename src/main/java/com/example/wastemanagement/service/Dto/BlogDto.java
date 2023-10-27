package com.example.wastemanagement.service.Dto;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.awt.image.BufferedImage;

@Value
@AllArgsConstructor
public class BlogDto {
    private Integer blogId;
    private String blogTitle;
    private String username;
    private String itemName;
    private String itemCategory;
    private String itemImage;
    private String blogContact;
    private String itemDescription;

}
