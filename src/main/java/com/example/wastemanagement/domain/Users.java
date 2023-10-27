package com.example.wastemanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.test.context.support.WithUserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="users")
public class Users {


    @Id
    @NotNull
    @Column(name = "username")
    private String accountName;

    @NotNull
    @Column(name = "password")
    private String account_Password;

    @NotNull
    @Column(name = "ENABLED")
    private Boolean ENABLED = true;

    @NotNull
    @ElementCollection
    @JoinTable(
            name = "authorities",
            joinColumns = {@JoinColumn(name = "username")})
    @Column(name = "authority")
    private Set<String> authority;

}
