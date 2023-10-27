package com.example.wastemanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="category")
public class Category {
    @Id
    @Column(name = "Category_Id")
    private Integer categoryId;
    @Column(name = "Category_Name")
    private String categoryName;
}
