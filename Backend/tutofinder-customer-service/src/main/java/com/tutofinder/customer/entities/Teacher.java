package com.tutofinder.customer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "teachers")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true,name = "dni",nullable = false,length = 8)
    private String dni;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Email(message = "@ required")
    @Column(unique = true,nullable = false)
    private String email;

    @Column(unique = true,nullable = false,name = "deposit_number")
    private String depositNumber;

    @Column(name = "hourly_cost",nullable = false)
    private Double hourlyCost;

    @Column(name = "membership")
    private String membership;

    @Lob
    @JsonIgnore
    @Column(name = "profile_picture")
    private byte[] profilePicture;

    /*@OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"teacher"}, allowSetters = true)
    private List<Tutorship> tutorships;*/

}
