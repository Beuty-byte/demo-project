package com.example.demoproject.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "phones_data")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Phone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    private User user;

    @Column(name = "phone")
    private String phoneNumber;


}