package com.tutofinder.payment.util;

import java.util.List;
import java.util.stream.Collectors;

import com.tutofinder.payment.dto.MembershipDto;
import com.tutofinder.payment.dto.create.CreateMembershipDto;
import com.tutofinder.payment.entities.Membership;

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

    //TODO: agregar el resto de mapeos

}
