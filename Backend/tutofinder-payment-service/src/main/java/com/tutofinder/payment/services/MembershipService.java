package com.tutofinder.payment.services;

import java.util.List;

import com.tutofinder.payment.dto.create.CreateMembershipDto;
import com.tutofinder.payment.entities.Membership;

public interface MembershipService {
    Membership getMembershipById(Long membershipId);
    List<Membership> getMemberships();
    Membership createMembership(CreateMembershipDto createMembership) throws RuntimeException;
    Membership updateMembership(CreateMembershipDto updateMembership, Long membershipId) throws RuntimeException;
    String deleteMembership(Long membershipId);
}
