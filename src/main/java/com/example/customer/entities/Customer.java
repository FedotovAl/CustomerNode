package com.example.customer.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phonenumber", unique = true)
    private String phoneNumber;

    //address
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "adressid")
    private Adress adress;

    //paidType
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST},
                fetch = FetchType.LAZY)
    @JoinTable(
            name = "customers_paidtype",
            joinColumns = @JoinColumn(name = "customerid"),
            inverseJoinColumns = @JoinColumn(name = "paidtypeid")
    )
    private Set<PaidType> paidTypeSet = new HashSet<>();
}
