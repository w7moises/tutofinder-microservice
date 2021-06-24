package com.tutofinder.payment.entities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_description")
    @NotEmpty(message = "The description must not be null")
    private String paymentDescription;

    @Column(name = "father_id")
    private Long fatherId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id",nullable = false)
    private Card card;

    @Column(name = "payment_cost")
    private double paymentCost;

    @Column(name = "booking_id")
    private Long bookingId;
}