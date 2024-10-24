package com.techelevator.BlackJack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> deck = new ArrayList<>();
    private List<Card> houseCards = new ArrayList<>();
    private List<Card> playerCards = new ArrayList<>();

    ///Constructor/////////////////
    public Deck() {

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 13; j++) {
                deck.add(new Card(i, j));
            }
        }
    }

    /////Methods/////////////////////////
//    public Card drawCard(boolean isFaceUp) {
//        return null;
//    }
    public void shuffle() {
        Collections.shuffle(deck);
    }


/////Getters/////////////////////////

    public List<Card> getDeck() {
        return deck;
    }
    public void addCardToHand(Card card, char recipient){
        if(recipient=='H'){
            houseCards.add(card);
        }
        else if(recipient=='P'){
            playerCards.add(card);
        }
    }
}


