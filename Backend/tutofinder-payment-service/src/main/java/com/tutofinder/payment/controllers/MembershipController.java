package com.tutofinder.payment.controllers;

import java.util.List;

import com.tutofinder.payment.dto.MembershipDto;
import com.tutofinder.payment.entities.Membership;
import com.tutofinder.payment.services.MembershipService;
import com.tutofinder.payment.util.EntityConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin({"http://localhost:4200"})
@Api
@RestController
public class MembershipController {
    @Autowired
    private MembershipService membershipService;

    @Autowired
    private EntityConverter converter;

    @ApiOperation(value = "Retrieve all existed memberships", notes = "This Operation returns all stored memberships.")
    @GetMapping(value = "membership")
    public ResponseEntity<List<MembershipDto>> findAll() {
        List<Membership> memberships = membershipService.getMemberships();
        return new ResponseEntity<>(converter.convertMembershipToDto(memberships), HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve a membership based on Id ", notes = "This Operation returns a membership by Membership Id")
    @GetMapping(value = "membership/{membershipId}")
    public ResponseEntity<MembershipDto> findById(@PathVariable Long membershipId) {
        Membership membership = membershipService.getMembershipById(membershipId);
        return new ResponseEntity<>(converter.convertMembershipToDto(membership), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes a Membership", notes = "This Operation deletes a membership.")
    @DeleteMapping(value = "membership/{membershipId}")
    public String deleteMembership(@PathVariable Long membershipId) {
        String response = membershipService.deleteMembership(membershipId);
        return response;
    }
    // TODO: El resto de oper. crud
}
