package com.tutofinder.tutorship.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tutofinder.tutorship.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Positive;

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

    @Transient
    private Teacher teacher;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Report report;

    private String status;

}
