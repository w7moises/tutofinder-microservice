package com.tutofinder.tutorship.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String status;
}
