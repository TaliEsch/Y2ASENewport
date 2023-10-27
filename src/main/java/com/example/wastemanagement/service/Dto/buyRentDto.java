package com.example.wastemanagement.service.Dto;

import lombok.Value;

import javax.persistence.*;

@Value
public class buyRentDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_Id")
    Long itemId;
    @Column(name = "item_Name")
    String itemName;
    String username;
    String description;
    String price;
    String category;
    @Column(name = "item_Sale_Type")
    String itemSaleType;
    String imageB64;

}