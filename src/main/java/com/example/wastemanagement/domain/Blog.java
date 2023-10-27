package com.example.wastemanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.awt.image.BufferedImage;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name ="blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "blog_id")
    private Integer blogId;

    @NotEmpty
//    @Column(name = "Blog_Title")
    private String blogTitle;

//    @NotEmpty
    private String username;
    // Username can be real name or can be the users preferred handle

 //   @NotEmpty
//    @Column(name = "Item_Name")
    private String itemName;


//    @Column(name = "Item_Category")
    private String itemCategory;

//    @Column(name = "Item_Image")
    private byte[] itemImage;
    // Warning these can be upwards of 60,000 entries for one image

    private String blogContact;

    @NotEmpty
//    @Column(name = "Item_Description")
    private String itemDescription;

}
