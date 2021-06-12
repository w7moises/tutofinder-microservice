package com.tutofinder.payment.entities;

import com.tutofinder.payment.models.Father;
import com.tutofinder.payment.models.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "pagos")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_description")
    @NotEmpty(message = "La descripcion no debe estar vacia")
    private String paymentDescription;

    @Transient
    private Father father;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id",nullable = false)
    private Card card;

    @Column(name = "payment_cost")
    private double paymentCost;

    @Transient
    private Reservation reservation;
}