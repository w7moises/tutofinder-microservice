package com.tutofinder.tutorship.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Tutorship extends  CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tutorship_id")
    private Long id;

    @Positive(message = "La cantidad de minutos debe ser mayor a cero")
    @Column(name = "cantidad_minutos", nullable = false)
    private int cantidadMinutos;

    @Column(name = "descripcion_tutoria")
    private String descripcionTutoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Course course;

    /*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "tutoria_alumno",
            joinColumns = {@JoinColumn(name = "tutoria_id")},
            inverseJoinColumns = { @JoinColumn(name = "alumno_id") })
//    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Set<Alumno> alumnos = new HashSet<>();
*/
    /*@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pago_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Pago pago;*/

   /* @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "docente_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Docente docente;
    */

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "informe_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Report report;

    private String status;

}
