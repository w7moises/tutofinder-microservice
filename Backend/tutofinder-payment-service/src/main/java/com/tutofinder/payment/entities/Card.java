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

    @NotEmpty(message = "El número de tarjeta no puede estar vacío")
    @Column(name = "card_number", nullable = false,length = 16)
    private String cardNumber;

    @NotEmpty(message = "La fecha de expiración no puede estar vacía")
    @Column(name = "expire_date", nullable = false)
    private String expireDate;

    @NotEmpty(message = "El nombre del poseedor no puede estar vacío")
    @Column(name = "owner_name", nullable = false)
    private String ownerName;
}