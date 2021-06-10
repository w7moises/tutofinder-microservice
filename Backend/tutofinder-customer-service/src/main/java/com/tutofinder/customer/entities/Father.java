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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "father", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"father", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    private List<Student> students;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "favorite",joinColumns = @JoinColumn(name = "father_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"father_id","teacher_id"})})
    private List<Teacher> teachers;

    @JsonIgnore
    @Column(name = "profile_picture")
    private byte[] profilePicture;

}