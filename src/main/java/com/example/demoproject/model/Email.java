package com.example.demoproject.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "emails_data")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Email implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    private User user;

    @Column(name = "email")
    private String email;
}
