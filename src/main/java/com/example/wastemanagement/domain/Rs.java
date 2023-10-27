package com.example.wastemanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="rs")
// 2 ways of using composite keys in JPA
// https://www.baeldung.com/jpa-composite-primary-keys
// 1. @IdClass = the query is simpler
// 2. @EmbeddedId = query is more complex(requiring more traversal)
@IdClass(RsId.class)
public class Rs {
    @Id
    @Column(name = "category_id")
    private Integer categoryId;
    @Id
    @Column(name = "name")
    private String Name;

    private String description;
}
