package com.tutofinder.payment.controllers;

import com.tutofinder.payment.dto.CardDto;
import com.tutofinder.payment.dto.create.CreateCardDto;
import com.tutofinder.payment.entities.Card;
import com.tutofinder.payment.services.CardService;
import com.tutofinder.payment.util.EntityConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin({"http://localhost:4200"})
@Api
@RestController
public class CardController {
    @Autowired
    private CardService cardService;

    @Autowired
    private EntityConverter converter;

    @ApiOperation(value = "Retrieve all existed Cards", notes = "This Operation returns all stored Cards.")
    @GetMapping(value = "Card")
    public ResponseEntity<List<CardDto>> findAll() {
        List<Card> Cards = cardService.getCards();
        return new ResponseEntity<>(converter.convertCardToDto(Cards), HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve a Card based on Id ", notes = "This Operation returns a Card by Card Id")
    @GetMapping(value = "Card/{CardId}")
    public ResponseEntity<CardDto> findById(@PathVariable Long CardId) {
        Card Card = cardService.getCardById(CardId);
        return new ResponseEntity<>(converter.convertCardToDto(Card), HttpStatus.OK);
    }

    @ApiOperation(value = "Creates a  Card", notes = "This Operation creates a new Card.")
    @PostMapping(value = "Card")
    public ResponseEntity<CardDto> createCard(@Valid @RequestBody CreateCardDto CardDto){
        Card Card = cardService.createCard(CardDto);
        return new ResponseEntity<>(converter.convertCardToDto(Card), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Modifies a  Card", notes = "This Operation modifies a Card.")
    @PostMapping(value = "Card/{CardId}")
    public ResponseEntity<CardDto> updateCard(@RequestBody @Valid CreateCardDto CardDto,@PathVariable Long CardId){
        Card Card = converter.convertCreateCardToEntity(CardDto);
        Card = cardService.updateCard(Card, CardId);
        return new ResponseEntity<>(converter.convertCardToDto(Card), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Deletes a Card", notes = "This Operation deletes a Card.")
    @DeleteMapping(value = "Card/{CardId}")
    public String deleteCard(@PathVariable Long CardId) {
        String response = cardService.deleteCard(CardId);
        return response;
    }
    
}
