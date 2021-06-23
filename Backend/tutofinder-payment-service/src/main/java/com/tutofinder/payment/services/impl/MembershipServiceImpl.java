package com.tutofinder.payment.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.transaction.annotation.Propagation;
import com.tutofinder.payment.client.CustomerServiceClient;
import com.tutofinder.payment.dto.TeacherDto;
import com.tutofinder.payment.dto.create.CreateMembershipDto;
import com.tutofinder.payment.entities.Card;
import com.tutofinder.payment.entities.Membership;
import com.tutofinder.payment.exceptions.CardNotFoundException;
import com.tutofinder.payment.exceptions.CustomerNotFoundException;
import com.tutofinder.payment.exceptions.InternalServerErrorException;
import com.tutofinder.payment.exceptions.InvalidRequestException;
import com.tutofinder.payment.exceptions.MembershipNotFoundException;
import com.tutofinder.payment.repositories.CardRepository;
import com.tutofinder.payment.repositories.MembershipRepository;
import com.tutofinder.payment.services.MembershipService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MembershipServiceImpl implements MembershipService {

    @Autowired
    MembershipRepository membershipRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    private CustomerServiceClient customerServiceClient;


    @Override
    @Transactional(readOnly = true)
    public Membership getMembershipById(Long membershipId) throws RuntimeException{
        Optional<Membership> membership = membershipRepository.findById(membershipId);
        return membership.orElseThrow(() -> new MembershipNotFoundException(membershipId.toString()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Membership> getMemberships() {
        return membershipRepository.findAll();
    }


    // TODO: VER ESTO -> @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    @Transactional
    public Membership createMembership(CreateMembershipDto createMembership) throws RuntimeException {

        TeacherDto teacherDto = customerServiceClient.findTeacherById(createMembership.getTeacherId())
        .orElseThrow(() -> new CustomerNotFoundException("TEACHER_NOT_FOUND"));
        log.info("TEACHER: ", teacherDto);

        Card card = cardRepository.findById(createMembership.getCardId())
        .orElseThrow(() -> new CardNotFoundException("CARD_NOT_FOUND"));
        log.info("CARD:", card);

        if(membershipRepository.findByTeacherIdAndCardId(teacherDto.getId(), card.getId()).isPresent()){
            throw new InvalidRequestException("MEMBERSHIP_IS_CURRENTLY_ACTIVE");
        }
        Long id;
        Membership membership = new Membership();
        membership.setTeacherId(teacherDto.getId());
        membership.setCardId(card.getId());
        membership.setExpirationDate(createMembership.getExpirationDate());
        membership.setDescription(createMembership.getDescription());
        membership.setCost(createMembership.getCost());

        try{
            id = membershipRepository.save(membership).getId();
        } catch (Exception e){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR");
        }
        return getMembershipById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Membership updateMembership(CreateMembershipDto updateMembership, Long membershipId) throws RuntimeException{
        TeacherDto teacherDto = customerServiceClient.findTeacherById(updateMembership.getTeacherId())
        .orElseThrow(() -> new CustomerNotFoundException("TEACHER_NOT_FOUND"));
        log.info("TEACHER: ", teacherDto);

        Card card = cardRepository.findById(updateMembership.getCardId())
        .orElseThrow(() -> new CardNotFoundException("CARD_NOT_FOUND"));
        log.info("CARD: ", card);

        if(membershipRepository.findByTeacherIdAndCardId(teacherDto.getId(), card.getId()).isPresent()){
            throw new InvalidRequestException("MEMBERSHIP_IS_CURRENTLY_ACTIVE");
        }
        Membership membership = membershipRepository.findById(membershipId)
        .orElseThrow(()-> new MembershipNotFoundException("MEMBERSHIP_NOT_FOUND"));
        membership.setTeacherId(teacherDto.getId());
        membership.setCardId(card.getId());
        membership.setExpirationDate(updateMembership.getExpirationDate());
        membership.setDescription(updateMembership.getDescription());
        membership.setCost(updateMembership.getCost());

        try{
            membershipRepository.save(membership);
        } catch (Exception e){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR");
        }
        return getMembershipById(membershipId);
    }

    @Override
    public String deleteMembership(Long membershipId) {
        if (!membershipRepository.existsById(membershipId)) {
            throw new MembershipNotFoundException("MEMBERSHIP_NOT_FOUND");
        }
        membershipRepository.deleteById(membershipId);
        return "Membership deleted";
    }

}
