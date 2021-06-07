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

    @Column(unique = true,nullable = false,length = 8)
    private String dni;

    /*@Column(nullable = false)
    private String domicilio;*/

    @Email(message = "@ required")
    @Column(unique = true,nullable = false)
    private String email;

   /* @Column(unique = true,nullable = false,name = "numero_cuenta")
    private String numeroCuenta;*/

    @Column(name = "hourly_cost",nullable = false)
    private Double hourlyCost;

    private Boolean membresia;

    @Lob
    @JsonIgnore
    @Column(name = "profile_picture")
    private byte[] profilePicture;

    /*@OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"teacher"}, allowSetters = true)
    private List<Tutorship> tutorships;*/

}
