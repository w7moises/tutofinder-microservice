package com.tutofinder.tutorship.entities;

import javax.persistence.*;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "tutorships")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TutorShip extends  CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tutorship_id")
    private Long id;

    @Positive(message = "the minutes amount must be greater than 0")
    @Column(name = "minutes", nullable = false)
    private int minutes;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Course course;

    @Column(name = "teacher_id")
    private Long teacherId;

    @OneToMany(mappedBy = "tutorShip", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"tutorship"}, allowSetters = true)
    private List<Booking> bookings;

    @OneToMany(mappedBy = "tutorShip", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"tutorship"}, allowSetters = true)
    private List<Report> reports;
}
