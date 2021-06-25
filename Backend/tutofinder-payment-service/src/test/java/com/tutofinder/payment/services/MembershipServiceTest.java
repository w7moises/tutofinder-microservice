package com.tutofinder.payment.services;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.tutofinder.payment.client.CustomerServiceClient;
import com.tutofinder.payment.dto.TeacherDto;
import com.tutofinder.payment.dto.create.CreateMembershipDto;
import com.tutofinder.payment.entities.Card;
import com.tutofinder.payment.entities.Membership;
import com.tutofinder.payment.repositories.CardRepository;
import com.tutofinder.payment.repositories.MembershipRepository;
import com.tutofinder.payment.services.impl.MembershipServiceImpl;

import static org.assertj.core.util.DateUtil.now;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class MembershipServiceTest {
    private static final Long MEMBERSHIP_ID = 1L;
    private static final String DESCRIPTION_MEMBERSHIP = "DESCRIPTION";
    private static final Double COST_MEMBERSHIP = 45.8;
    private static final Date DATE_EXPIRACION = now();
    private static final Long CARD_ID = 1L;
    private static final Long TEACHER_ID = 1L;
    public static final Membership MEMBERSHIP = new Membership();

    CreateMembershipDto CREATE_MEMBERSHIP_DTO = new CreateMembershipDto();
    private static final String MEMBERSHIP_DELETED = "Membership deleted";
    private static final Optional<Membership> OPTIONAL_EMPTY = Optional.empty();
    private static final Optional<Membership> OPTIONAL_MEMBERSHIP = Optional.of(MEMBERSHIP);
    private static final Optional<Card> OPTIONAL_CARD = Optional.of(new Card());
    private static final Optional<TeacherDto> OPTIONAL_TEACHER = Optional.of(new TeacherDto());
    @Mock
    private CustomerServiceClient customerClient;

    @Mock
    private MembershipRepository membershipRepository;

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private MembershipServiceImpl membershipService;


    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);

        MEMBERSHIP.setId(MEMBERSHIP_ID);
        MEMBERSHIP.setDescription(DESCRIPTION_MEMBERSHIP);

        CREATE_MEMBERSHIP_DTO.setDescription(DESCRIPTION_MEMBERSHIP);
        CREATE_MEMBERSHIP_DTO.setCost(COST_MEMBERSHIP);
        CREATE_MEMBERSHIP_DTO.setExpirationDate(DATE_EXPIRACION);
        CREATE_MEMBERSHIP_DTO.setCardId(CARD_ID);
        CREATE_MEMBERSHIP_DTO.setTeacherId(TEACHER_ID);

    }

    @Test
    public void getMembershipByIdTest() throws RuntimeException{
        Mockito.when(membershipRepository.findById(MEMBERSHIP_ID)).thenReturn(OPTIONAL_MEMBERSHIP);
        membershipService.getMembershipById(MEMBERSHIP_ID);
    }

    @Test
    public void getMembershipsTest(){
        final Membership membership = new Membership();
        Mockito.when(membershipRepository.findAll()).thenReturn(Arrays.asList(membership));
        final List<Membership> response = membershipService.getMemberships();
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(response.size(), 1);
    }
    // @Test(expected = RuntimeException.class)
    // public void getMembershipByIdTestError() throws RuntimeException {
    //     Mockito.when(membershipRepository.findById(MEMBERSHIP_ID)).thenReturn(OPTIONAL_EMPTY);
    //     membershipService.getMembershipById(MEMBERSHIP_ID);
    //     fail();
    // }

    @Test
    public void createMembershipTest() throws RuntimeException{
        Mockito.when(cardRepository.findById(CARD_ID)).thenReturn(OPTIONAL_CARD);
        Mockito.when(customerClient.findTeacherById(TEACHER_ID)).thenReturn(OPTIONAL_TEACHER);
        Mockito.when(membershipRepository.findById(MEMBERSHIP_ID)).thenReturn(OPTIONAL_MEMBERSHIP);
        Mockito.when(membershipRepository.save(Mockito.any(Membership.class))).thenReturn(MEMBERSHIP);
        Membership membership = membershipService.createMembership(CREATE_MEMBERSHIP_DTO);
        assertEquals(membership,MEMBERSHIP);
    }

    @Test
    public void deleteMembresiaOk() throws RuntimeException {
        Mockito.when(membershipRepository.existsById(MEMBERSHIP_ID)).thenReturn(true);
        final String response = membershipService.deleteMembership(MEMBERSHIP_ID);
        assertEquals(response, MEMBERSHIP_DELETED);
    }
}
