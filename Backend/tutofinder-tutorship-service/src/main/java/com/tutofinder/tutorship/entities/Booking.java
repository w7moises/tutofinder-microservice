package com.tutofinder.tutorship.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "bookings")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking extends CommonEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_Id")
    private Long studentId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_ship_Id")
    private TutorShip tutorShip;
}
