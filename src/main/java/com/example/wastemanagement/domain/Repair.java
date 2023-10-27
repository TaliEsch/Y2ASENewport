package com.example.wastemanagement.domain;

import com.example.wastemanagement.web.Form.Validation.Address;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "repair_booking")
@Builder
public class Repair {
    @Id
    // the id is generated automatically
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer bookingId;
    @NotEmpty
    private String itemName;
    @NotEmpty
    private String itemDescription;
    // foreign key to the hub table
    private Integer hubId;
    @NotEmpty
    private String fullName;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Address // custom validation annotation
    private String address;
}
