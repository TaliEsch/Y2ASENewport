package com.example.wastemanagement.domain;

import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public class RsId implements Serializable {
    // used with @IdClass in Use, so that the composite key is used
    private Integer categoryId;
    private String Name;
    public RsId(){

    }

}
