package com.example.demoproject.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirthday;

    @Column(name = "password", nullable = false)
    private String password;

    @EqualsAndHashCode.Exclude
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Phone> phones;

    @EqualsAndHashCode.Exclude
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Email> emails;

    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Account account;
}
