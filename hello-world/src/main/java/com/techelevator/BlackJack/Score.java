package com.techelevator.BlackJack;

public class Score {

    private int houseTotal=0;
    private int playerTotal=0;
    private boolean houseHasAce=false;
    private boolean playerHasAce=false;

    /**
     *
     * @param card the dealt card will add to the House's score
     * @return returns whether or not an ace was added, which will just add 1 until adding 10 more will be necessary
     */
    public boolean addToHouseTotal(Card card){
        int rank = card.getRank();
        if(rank==13 || rank==12 || rank==11){
            houseTotal+=10;
        }
        else if(rank==1){
            houseTotal+=1;
            houseHasAce=true;
        }
        else{
            houseTotal+=rank;
        }
       return houseHasAce;
    }
    /**
     *
     * @param card the dealt card will add to the Player's score
     * @return returns whether or not an ace was added, which will just add 1 until adding 10 more will be necessary
     */
    public boolean addToPlayerTotal(Card card){
        int rank = card.getRank();
        if(rank==13 || rank==12 || rank==11){
            playerTotal+=10;
        }
        else if(rank==1){
            playerTotal+=1;
            playerHasAce=true;
        }
        else{
            playerTotal+=rank;
        }
        return playerHasAce;
    }
    public int getHouseTotal() {
        return houseTotal;
    }

    public int getPlayerTotal() {
        return playerTotal;
    }

    public void resetScores(){
        houseTotal=0;
        playerTotal=0;
        houseHasAce=false;
        playerHasAce=false;
    }
}
