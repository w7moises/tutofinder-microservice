package com.tutofinder.payment.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "memberships")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Membership extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "expiration_date", nullable = false)
    private String expirationDate;

    @NotEmpty(message = "Description must not be null")
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "cost")
    private double cost;

    @Column(name= "teacher_id")
    private Long teacherId;

    @Column(name= "card_id")
    private Long cardId;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "teacher_id", nullable = false)
    // private Teacher teacher;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "card_id",nullable = false)
    // private Card card;
}
