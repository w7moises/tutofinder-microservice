package com.tutofinder.payment.util;

import java.util.List;
import java.util.stream.Collectors;

import com.tutofinder.payment.dto.CardDto;
import com.tutofinder.payment.dto.MembershipDto;
import com.tutofinder.payment.dto.PaymentDto;
import com.tutofinder.payment.dto.create.CreateCardDto;
import com.tutofinder.payment.dto.create.CreateMembershipDto;
import com.tutofinder.payment.dto.create.CreatePaymentDto;
import com.tutofinder.payment.entities.Card;
import com.tutofinder.payment.entities.Membership;
import com.tutofinder.payment.entities.Payment;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityConverter {
    @Autowired
    private ModelMapper modelMapper;

    public Membership convertMembershipToEntity(MembershipDto membershipdto) {
        return modelMapper.map(membershipdto, Membership.class);
    }

    public Membership convertMembershipToEntity(CreateMembershipDto membershipdto) {
        return modelMapper.map(membershipdto, Membership.class);
    }

    public MembershipDto convertMembershipToDto(Membership membership) {
        return modelMapper.map(membership, MembershipDto.class);
    }

    public Membership convertCreateMembershipToEntity(CreateMembershipDto createMembership) {
        return modelMapper.map(createMembership, Membership.class);
    }

    public List<MembershipDto> convertMembershipToDto(List<Membership> memberships) {
        return memberships.stream().map(membership -> modelMapper.map(membership, MembershipDto.class))
                .collect(Collectors.toList());
    }
    public Payment convertPaymentToEntity(PaymentDto paymentdto) {
        return modelMapper.map(paymentdto, Payment.class);
    }

    public Payment convertPaymentToEntity(CreatePaymentDto paymentdto) {
        return modelMapper.map(paymentdto, Payment.class);
    }

    public PaymentDto convertPaymentToDto(Payment payment) {
        return modelMapper.map(payment, PaymentDto.class);
    }

    public Payment convertCreatePaymentToEntity(CreatePaymentDto createpayment) {
        return modelMapper.map(createpayment, Payment.class);
    }

    public List<PaymentDto> convertPaymentToDto(List<Payment> payments) {
        return payments.stream().map(payment -> modelMapper.map(payment, PaymentDto.class))
                .collect(Collectors.toList());
    }

    public List<CardDto> convertCardToDto(List<Card> cards) {
        return cards.stream().map(card -> modelMapper.map(card, CardDto.class))
                .collect(Collectors.toList());
    }
    public CardDto convertCardToDto(Card card) {
        return modelMapper.map(card, CardDto.class);
    }

    public Card convertCreateCardToEntity(CreateCardDto cardDto) {
        return modelMapper.map(cardDto, Card.class);
    }

    //TODO: agregar el resto de mapeos de Card

}
