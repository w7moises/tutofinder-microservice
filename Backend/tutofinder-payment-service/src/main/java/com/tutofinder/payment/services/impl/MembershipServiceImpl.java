package com.tutofinder.payment.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.tutofinder.payment.client.CustomerServiceClient;
import com.tutofinder.payment.entities.Membership;
import com.tutofinder.payment.exceptions.MembershipNotFoundException;
import com.tutofinder.payment.repositories.MembershipRepository;
import com.tutofinder.payment.services.MembershipService;
import org.springframework.stereotype.Service;

@Service
public class MembershipServiceImpl implements MembershipService {

    @Autowired
    MembershipRepository membershipRepository;

    @Autowired
    private CustomerServiceClient customerServiceClient;


    //TODO: Revisar que funcione llamar a los endpoints xd

    @Override
    @Transactional(readOnly = true)
    public Membership getMembershipById(Long membershipId) {
        Optional<Membership> membership = membershipRepository.findById(membershipId);
        return membership.orElseThrow(() -> new MembershipNotFoundException(membershipId.toString()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Membership> getMemberships() {
        return membershipRepository.findAll();
    }

    @Override
    public Membership createMembership(Membership createMembership) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Membership updateMembership(Membership updateMembership, Long membershipId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String deleteMembership(Long membershipId) {
        if (!membershipRepository.existsById(membershipId)) {
            throw new MembershipNotFoundException(membershipId.toString());
        }
        membershipRepository.deleteById(membershipId);
        return "Membership with id " + membershipId + " deleted";
    }

}
