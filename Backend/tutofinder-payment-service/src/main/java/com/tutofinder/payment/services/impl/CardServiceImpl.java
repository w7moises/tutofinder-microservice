package com.tutofinder.payment.services.impl;

import java.util.List;
import java.util.Optional;

import com.tutofinder.payment.exceptions.CardNotFoundException;
import com.tutofinder.payment.exceptions.InternalServerErrorException;
import com.tutofinder.payment.exceptions.MembershipNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tutofinder.payment.dto.create.CreateCardDto;
import com.tutofinder.payment.entities.Card;
import com.tutofinder.payment.repositories.CardRepository;
import com.tutofinder.payment.services.CardService;

import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    @Transactional(readOnly = true)
    public Card getCardById(Long cardId) throws RuntimeException{
        Optional<Card> newCard = cardRepository.findById(cardId);
        return newCard.orElseThrow(() -> new CardNotFoundException(cardId.toString()));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Card updateCard(Card updateCard, Long cardId) throws RuntimeException {
        Card newCard = cardRepository.findById(cardId)
                .orElseThrow(()-> new CardNotFoundException("CARD_NOT_FOUND"));
        newCard.setCardNumber(updateCard.getCardNumber());
        newCard.setExpireDate(updateCard.getExpireDate());
        newCard.setOwnerName(updateCard.getOwnerName());

        try{
            cardRepository.save(newCard);
        }catch (Exception e){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR");
        }
        return getCardById(cardId);
    }

    @Override
    public String deleteCard(Long cardId) throws RuntimeException {
        if (!cardRepository.existsById(cardId)) {
            throw new CardNotFoundException("CARD_NOT_FOUND");
        }
        cardRepository.deleteById(cardId);
        return "Card id deleted: " + cardId;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Card> getCards() {
        return cardRepository.findAll();
    }

    @Override
    @Transactional
    public Card createCard(CreateCardDto createCard) throws RuntimeException {
        Long id;
        Card newCard = new Card();
        newCard.setCardNumber(createCard.getCardNumber());
        newCard.setExpireDate(createCard.getExpireDate());
        newCard.setOwnerName(createCard.getOwnerName());

        try{
            id = cardRepository.save(newCard).getId();
        }catch (Exception e){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR");
        }
        return getCardById(id);
    }


}
