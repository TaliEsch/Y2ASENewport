package com.example.wastemanagement.domain;

import com.example.wastemanagement.web.Form.Validation.Profanity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "index_page")
public class index {
    @Id
    @Column(name = "index_Id")
    private Long itemId;
    @Profanity
    @NotEmpty
    private String description;
    @NotNull
    private byte[] image1;
    @NotNull
    private byte[] image2;
    @NotNull
    private byte[] image3;
    @NotNull
    private byte[] image4;
}
