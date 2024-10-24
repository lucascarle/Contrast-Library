package com.techelevator.BlackJack;

import java.util.Scanner;

public class Menu {
    private Scanner keyscan = new Scanner(System.in);

    public void showStartMenu(){
        System.out.println("♠♡♣♢♠♡♣♢BLACKJACK♠♡♣♢♠♡♣♢");
        System.out.println("♢♠♡♣♢♠♡♣♢♠♡♣♢♠♡♣♢♠♡♣♢♠♡♣");
        System.out.println("--------------------------");
        System.out.println("---Press Any Key To PLAY---");

        keyscan.nextLine();

    }
    public void readyToDeal(){

        System.out.println("---Press Any Key to DEAL---");
        keyscan.nextLine();

    }

    public void showShuffle(){
        System.out.println("Shuffling....");
        slow();
    }
    public void showCard(Card cardToShow){
        System.out.println(cardToShow.getCard());
        slow();
    }
    public void showPlayerHand(Card cardToShow1,Card cardToShow2){
        System.out.println("Your hand: "+cardToShow1.getCard()+" "+cardToShow2.getCard());
        slow();
    }
    public void showHouseHand(Card houseCard1, Card houseCard2){
        System.out.println("House hand: \u2754 "+houseCard2.getCard());
        slow();
    }
    public void slow(){
        try {
            Thread.sleep(700);
        } catch (InterruptedException e){}
    }
}
