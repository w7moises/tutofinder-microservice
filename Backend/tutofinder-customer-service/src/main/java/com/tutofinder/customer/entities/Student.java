package com.tutofinder.customer.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tutofinder.customer.util.EducationLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Data
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true,nullable = false,length = 8)
    private String dni;

    @Column(name = "education_level")
    @Enumerated(value = EnumType.STRING)
    private EducationLevel educationLevel;

    @Email(message = "@ required")
    @Column(unique = true,nullable = false)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "father_id",nullable = false)
    @JsonIgnoreProperties(value = {"students", "hibernateLazyInitializer", "handler"})
    private Father father;

}
