package com.tutofinder.customer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Data
@Table(name = "fathers")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Father extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true,nullable = false,length = 8)
    private String dni;

    @Email(message = "@ required")
    @Column(unique = true,nullable = false)
    private String email;

    @OneToMany(mappedBy = "father", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"father"}, allowSetters = true)
    private List<Student> students;

    @Lob
    @JsonIgnore
    @Column(name = "profile_picture")
    private byte[] profilePicture;

}