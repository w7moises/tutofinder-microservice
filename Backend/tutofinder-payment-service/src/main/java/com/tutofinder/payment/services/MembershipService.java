package com.tutofinder.payment.services;

import java.util.List;
import com.tutofinder.payment.entities.Membership;

public interface MembershipService {
    Membership getMembershipById(Long membershipId);

    List<Membership> getMemberships();

    Membership createMembership(Membership createMembership);

    Membership updateMembership(Membership updateMembership, Long membershipId);

    String deleteMembership(Long membershipId);

}
