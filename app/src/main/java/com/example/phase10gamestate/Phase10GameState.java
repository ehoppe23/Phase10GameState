//Need to add header comment to every java file
//Did we remember a external citation?
package com.example.phase10gamestate;

import java.util.ArrayList;
import java.util.Stack;

public class Phase10GameState {

    //Resources
    private int turnId; //1 or 2 based on id of player turn
    private ArrayList<Card> player1Hand; //Player hands start at 10 cards
    private ArrayList<Card> player2Hand;
    private ArrayList<Card> player1PhaseContent;
    private ArrayList<Card> player2PhaseContent;
    private ArrayList<Card> drawPile;
    private Stack<Card> discardPile;
    private boolean player1HasPhased;
    private boolean player2HasPhased;
    private boolean playerHasDrawn; //Only one draw allowed per round
    private int goesFirst; //Player Id of who goes first, alternates each round
    private int player1Score; //Lower scores are better
    private int player2Score;
    private int player1Phase;
    private int player2Phase;
    private int hasGoneOut; //set to zero until a player goes out, then set to player Id

    /**
     * attempts to draw a face down card from the draw pile and add it to the player's hand
     * player does not know what the card is until they draw it
     *
     * @param playerId id of the player attempting to draw, this param could be replaced by a player object later on,
     *                 in which case a .getId() call would be added to the method
     * @return true if the action was successful, else will return false
     */
    public boolean drawFaceDown(int playerId){

        if(playerId != this.turnId ||this.drawPile.size() == 0 || this.hasGoneOut == playerId || this.playerHasDrawn) return false;

        Card drawn = this.drawPile.remove(0); //Remove top card from draw pile
        this.playerHasDrawn = true;

        //determine which player hand it goes to
        if(playerId == 1){
            this.player1Hand.add(drawn); //add to hand
            return true;
        }
        else if(playerId == 2){
            this.player2Hand.add(drawn); //add to hand
            return true;
        }

        else return false;
    }

    /**
     * attempts to draw a face up card from the discard pile and add it to the player's hand
     * the card drawn will be the last card discarded onto the pile, so player knows which card they are drawing
     *
     * @param playerId id of the player attempting to draw, this param could be replaced by a player object later on,
     *                 in which case a .getId() call would be added to the method
     * @return true if the action was successful, else will return false
     */
    public boolean drawFaceUp(int playerId){

        if(playerId != this.turnId ||this.discardPile.size() == 0 || this.hasGoneOut == playerId || this.playerHasDrawn) return false;

        Card drawn = this.discardPile.pop(); //remove top card from discard
        this.playerHasDrawn = true;

        //determine which player hand it goes to
        if(playerId == 1){
            this.player1Hand.add(drawn); //add to hand
            return true;
        }
        else if(playerId == 2){
           this.player2Hand.add(drawn); //add to hand
            return true;
        }
        else return false;
    }

    /**
     * attempts to discard a card from player hand to the discard pile
     *
     * @param playerId id of the player attempting to discard, this param could be replaced by a player object later on,
     *                 in which case a .getId() call would be added to the method
     * @param cardLoc the location in player hand of the card being discarded
     * @return true if the action was successful, else will return false
     */
    public boolean discard(int playerId, int cardLoc){

        if(playerId != this.turnId ||this.hasGoneOut == playerId || cardLoc < 0) return false;

        //determine which player is discarding
        if(playerId == 1){
            if(this.player1Hand.size() < cardLoc) return false;

            discardPile.push(this.player1Hand.remove(cardLoc)); //discard to discard pile

            if(this.player1Hand.size() == 0)  this.hasGoneOut = playerId; //If a player's hand is empty, the other player gets one turn before round ends
            if(!discardPile.peek().isSkip()) this.turnId = 2; //Skips in 2 player mode allow current player to take 2 back-to-back turns

            return true;
        }
        else if(playerId == 2){
            if(this.player2Hand.size() < cardLoc) return false;

            discardPile.push(this.player2Hand.remove(cardLoc)); //discard to discard pile

            if(this.player2Hand.size() == 0)  this.hasGoneOut = playerId;
            if(!discardPile.peek().isSkip()) this.turnId = 1;

            return true;
        }
        else return false;
    }
}
