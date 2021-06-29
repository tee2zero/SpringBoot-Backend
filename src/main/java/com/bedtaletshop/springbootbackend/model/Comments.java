package com.bedtaletshop.springbootbackend.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.Date;

// Test Code

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comments {

    // Embeddable can use with out id

    String postedBy;
    String commentText;
    Date datePosted;
    String user; // auth0 username
    Long commentVideoId;
}