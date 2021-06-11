package com.tutofinder.tutorship.entities;

import com.tutofinder.tutorship.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "reports")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Report  extends  CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description_report")
    private String descriptionReport;

    @Column(name = "student_id")
    private Long studentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_ship_id")
    private TutorShip tutoShip;
}
