package com.techelevator.BlackJack;

public class Card {

    private String suit;
    private int rank;



    //Constructor
    public Card(int suit,int rank){
        switch(suit){
            case 1: this.suit="\u2660";//Spade
            break;
            case 2: this.suit="\u2661";//Heart
            break;
            case 3: this.suit="\u2662";//Diamond
            break;
            case 4: this.suit="\u2663";//Club
        }
        this.rank=rank;
    }
    //////Methods//////////




//////Getters/////////;
    public String getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }
    public String getCard(){
        String face;
        switch(rank){
            case 1: face="A";
            break;
            case 11: face="J";
            break;
            case 12: face = "Q";
            break;
            case 13: face = "K";
            break;
            default: face = Integer.toString(rank);
            break;
        }
        return (face+suit);
    }
}
