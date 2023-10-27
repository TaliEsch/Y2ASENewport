package com.example.wastemanagement.domain;

import com.example.wastemanagement.web.Form.Validation.Profanity;
import com.example.wastemanagement.web.Form.Validation.SpecialCharacters;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name ="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    private int blogId;
    // Length from database
    @Length(max = 40)
    @Profanity
    @SpecialCharacters
    @NonNull
    // can't be just be spaces or empty
    @Pattern(regexp = "^[^\\s].*[^\\s]$", message = "Please input a username")
    private String username;
    @Column(name = "comment")
    // Length of comment is limited to 1000 characters (database)
    @Length(max = 1000)
    @Profanity
    @SpecialCharacters
    @NonNull
    // can't be just be spaces or empty
    @Pattern(regexp = "^[^\\s].*[^\\s]$", message = "Please input a comment")
    private String commentText;
}
