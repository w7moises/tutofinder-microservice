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

    @Positive(message = "La cantidad de minutos debe ser mayor a cero")
    @Column(name = "cantidad_minutos", nullable = false)
    private int cantidadMinutos;

    @Column(name = "description_tutoria")
    private String descripcionTutoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Course course;

    @Column(name = "teacher_id")
    private Long teacherId;

    @OneToMany(mappedBy = "tutorship", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"tutorship"}, allowSetters = true)
    private List<Booking> bookings;

    @OneToMany(mappedBy = "tutorship", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"tutorship"}, allowSetters = true)
    private List<Report> reports;
}
