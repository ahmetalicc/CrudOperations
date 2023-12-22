package com.sau.spring.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "user_name", nullable = false, length = 50)
    private String username;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    public User(String username, String firstName, String lastName){
        this.username = username;
        this.firstName = firstName;
        this.lastName= lastName;
    }

}
