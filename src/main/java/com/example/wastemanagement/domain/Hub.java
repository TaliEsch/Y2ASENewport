package com.example.wastemanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
// builder automatically generates a builder for the class
@Builder
@Table(name ="hub")
public class Hub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hub_id")
    private Integer hubId;
    @Column(name = "hub_name")
    private String hubName;
    @Column(name = "which_r")
    private String whichR;
    private String categoriesList;
    private String link;
    private String address;
    private String postcode;
    private Double latitude;
    private Double longitude;
    private String phoneNumber;
}
