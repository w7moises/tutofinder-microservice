package com.tutofinder.payment.services;

import java.util.List;

import com.tutofinder.payment.dto.create.CreateCardDto;
import com.tutofinder.payment.entities.Card;

public interface CardService {
    Card getCardById(Long cardId);
    List<Card> getCards();
    Card createCard(CreateCardDto createCard) throws RuntimeException;
    Card updateCard(Card updateCard, Long cardId) throws RuntimeException;
    String deleteCard(Long cardId) throws RuntimeException;
}
