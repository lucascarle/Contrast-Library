package com.techelevator.BlackJack;

import java.sql.SQLOutput;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    Menu menu = new Menu();
    Deck deck = new Deck();
    Score score = new Score();

    private final char HOUSE='H';
    private final char PLAYER='P';

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    public void run() {

        ////Print Intro Screen////////
        menu.showStartMenu();

        //Show player deck has been shuffled

        //Get user to continue when ready for deal
        menu.readyToDeal();
        //Deal 2 Cards to House, one face up, one face down
        menu.showHouseHand(dealCard(HOUSE),dealCard(HOUSE));

        //Deal 2 cards to player, face up
        menu.showPlayerHand(dealCard(PLAYER),dealCard(PLAYER));



    }

    public List<Card> createDeck() {
        Deck prime = new Deck();
        return prime.getDeck();

    }
    private void shuffle(){
        deck.shuffle();
        menu.showShuffle();
    }
    private Card dealCard(char recipient){
        Card card = deck.getDeck().removeFirst();
        deck.addCardToHand(card,recipient);
        if (recipient=='H'){
            score.addToHouseTotal(card);

        }
        if (recipient=='P'){
            score.addToPlayerTotal(card);
        }
        return card;
    }
}

