package com.goaaal.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Document(collection = "user")  // Spécifie la collection MongoDB "user" (ou modifiez selon votre préférence)
public class User {
    @Id
    private String id;
    private String email;
    private String password;
}
