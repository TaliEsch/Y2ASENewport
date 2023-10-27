package com.example.wastemanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "buy_rent")
public class buyRent {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "item_Id")
    private Long itemId;
    @Column(name = "item_Name")
    private String itemName;
    private String username;
    private String email;
    private String description;
    private String price;
    private String category;
    @Column(name = "item_Sale_Type")
    private String itemSaleType;
    private byte[] image;

    public buyRent(String category, String itemSaleType) {
        this.category = category;
        this.itemSaleType = itemSaleType;
    }

    public buyRent(String itemName, String category, String itemSaleType) {
        this.itemName = itemName;
        this.category = category;
        this.itemSaleType = itemSaleType;
    }

//    public buyRent(Long itemId, String itemName, String username, String email, String description) {
//        this.itemId = itemId;
//        this.itemName = itemName;
//        this.username = username;
//        this.email = email;
//        this.description = description;
//    }
}

