package com.tutofinder.payment.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "tarjetas")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Card extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Card number can't be empty")
    @Column(name = "card_number", nullable = false,length = 16)
    private String cardNumber;

    @NotEmpty(message = "Expiration date can't be empty")
    @Column(name = "expire_date", nullable = false)
    private String expireDate;

    @NotEmpty(message = "Owner name can't be empty")
    @Column(name = "owner_name", nullable = false)
    private String ownerName;
}