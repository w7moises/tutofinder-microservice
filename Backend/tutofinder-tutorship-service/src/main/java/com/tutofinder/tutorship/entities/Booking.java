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

    @Column(name = "Student_Id")
    private Long StudentId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutorship_Id")
    private TutorShip tutorShip;




}
