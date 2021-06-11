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

    @Transient
    private Student student;

    @Transient
    private  TutorShip tutorShip;

}
