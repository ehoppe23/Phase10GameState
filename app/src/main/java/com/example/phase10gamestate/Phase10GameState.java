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
    private ArrayList<Card> drawPile; //108 cards before dealing
    private Stack<Card> discardPile;
    private boolean player1HasPhased;
    private boolean player2HasPhased;
    private boolean playerHasDrawn; //Only one draw allowed per round
    private int goesFirst; //Player Id of who goes first, alternates each round
    private int player1Score; //Lower scores are better
    private int player2Score;
    private int player1Phase; //Standard is 10 phases, optional: set different phases for game
    private int player2Phase;
    private int hasGoneOut; //set to zero until a player goes out, then set to player Id

    //Setters
    public void setTurnId(int turnId) {
        this.turnId = turnId;
    }

    public void setPlayerHasDrawn(boolean playerHasDrawn) {
        this.playerHasDrawn = playerHasDrawn;
    }

    public void setGoesFirst(int goesFirst) {
        this.goesFirst = goesFirst;
    }

    public void setHasGoneOut(int hasGoneOut) {
        this.hasGoneOut = hasGoneOut;
    }

    public void setDiscardPile(Stack<Card> discardPile) {
        this.discardPile = discardPile;
    }

    public void setDrawPile(ArrayList<Card> drawPile) {
        this.drawPile = drawPile;
    }

    public void setPlayer1HasPhased(boolean player1HasPhased) {
        this.player1HasPhased = player1HasPhased;
    }

    public void setPlayer2HasPhased(boolean player2HasPhased) {
        this.player2HasPhased = player2HasPhased;
    }

    public void setPlayer1PhaseContent(ArrayList<Card> player1PhaseContent) {
        this.player1PhaseContent = player1PhaseContent;
    }

    public void setPlayer2PhaseContent(ArrayList<Card> player2PhaseContent) {
        this.player2PhaseContent = player2PhaseContent;
    }

    public void setPlayer1Phase(int player1Phase) {
        this.player1Phase = player1Phase;
    }

    public void setPlayer2Phase(int player2Phase) {
        this.player2Phase = player2Phase;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
    }

    public void setPlayer1Hand(ArrayList<Card> player1Hand) {
        this.player1Hand = player1Hand;
    }

    public void setPlayer2Hand(ArrayList<Card> player2Hand) {
        this.player2Hand = player2Hand;
    }

    //Getters
    public boolean getPlayerHasDrawn() {
        return this.playerHasDrawn;
    }

    public int getHasGoneOut() {
        return this.hasGoneOut;
    }

    public int getTurnId() {
        return this.turnId;
    }

    public Stack<Card> getDiscardPile() {
        return this.discardPile;
    }

    public int getGoesFirst() {
        return this.goesFirst;
    }

    public ArrayList<Card> getDrawPile() {
        return this.drawPile;
    }

    public int getPlayer1Score() {
        return this.player1Score;
    }

    public int getPlayer2Score() {
        return this.player2Score;
    }

    public int getPlayer1Phase() {
        return this.player1Phase;
    }

    public int getPlayer2Phase() {
        return this.player2Phase;
    }

    public ArrayList<Card> getPlayer1Hand() {
        return this.player1Hand;
    }

    public ArrayList<Card> getPlayer2Hand() {
        return this.player2Hand;
    }

    public ArrayList<Card> getPlayer1PhaseContent() {
        return this.player1PhaseContent;
    }

    public ArrayList<Card> getPlayer2PhaseContent() {
        return this.player2PhaseContent;
    }

    public boolean getPlayer1HasPhased() {
        return this.player1HasPhased;
    }

    public boolean getPlayer2HasPhased() {
        return this.player2HasPhased;
    }

    /**
     * Constructor - Initializes variables with 0/null values
     */
    public Phase10GameState() {
        turnId = 0;
        hasGoneOut = 0;
        goesFirst = 0;
        playerHasDrawn = false;
        player1HasPhased = false;
        player2HasPhased = false;
        player1Score = 0;
        player2Score = 0;
        player1Phase = 0;
        player2Phase = 0;
        player1Hand = null;
        player2Hand = null;
        player1PhaseContent = null;
        player2PhaseContent = null;
        discardPile = null;
        drawPile = null;
    }

    /**
     * Copy constructor - initializes with given values
     */
    public Phase10GameState(Phase10GameState PhaseGS) {
        this.setTurnId(PhaseGS.getTurnId());
        this.setHasGoneOut(PhaseGS.getHasGoneOut());
        this.setGoesFirst(PhaseGS.getGoesFirst());
        this.setDiscardPile(PhaseGS.getDiscardPile());
        this.setDrawPile(PhaseGS.getDrawPile());
        this.setPlayerHasDrawn(PhaseGS.getPlayerHasDrawn());
        this.setPlayer1Hand(PhaseGS.getPlayer1Hand());
        this.setPlayer2Hand(PhaseGS.getPlayer2Hand());
        this.setPlayer1HasPhased(PhaseGS.getPlayer1HasPhased());
        this.setPlayer2HasPhased(PhaseGS.getPlayer2HasPhased());
        this.setPlayer1Score(PhaseGS.getPlayer1Score());
        this.setPlayer2Score(PhaseGS.getPlayer2Score());
        this.setPlayer1Phase(PhaseGS.getPlayer1Phase());
        this.setPlayer2Phase(PhaseGS.getPlayer2Phase());
        this.setPlayer1PhaseContent(PhaseGS.getPlayer1PhaseContent());
        this.setPlayer2PhaseContent(PhaseGS.getPlayer2PhaseContent());
    }

    /**
     * attempts to draw a face down card from the draw pile and add it to the player's hand
     * player does not know what the card is until they draw it
     *
     * @param playerId id of the player attempting to draw, this param could be replaced by a player object later on,
     *                 in which case a .getId() call would be added to the method
     * @return true if the action was successful, else will return false
     */
    public boolean drawFaceDown(int playerId) {

        if (playerId != this.turnId || this.drawPile.size() <= 0 || this.hasGoneOut == playerId || this.playerHasDrawn)
            return false;

        Card drawn = this.drawPile.remove(0); //Remove top card from draw pile
        this.playerHasDrawn = true;

        //determine which player hand it goes to
        if (playerId == 1) {
            this.player1Hand.add(drawn); //add to hand
            return true;
        } else if (playerId == 2) {
            this.player2Hand.add(drawn); //add to hand
            return true;
        } else return false;
    }

    /**
     * attempts to draw a face up card from the discard pile and add it to the player's hand
     * the card drawn will be the last card discarded onto the pile, so player knows which card they are drawing
     *
     * @param playerId id of the player attempting to draw, this param could be replaced by a player object later on,
     *                 in which case a .getId() call would be added to the method
     * @return true if the action was successful, else will return false
     */
    public boolean drawFaceUp(int playerId) {

        if (playerId != this.turnId || this.discardPile.size() <= 0 || this.hasGoneOut == playerId || this.playerHasDrawn)
            return false;

        Card drawn = this.discardPile.pop(); //remove top card from discard
        this.playerHasDrawn = true;

        //determine which player hand it goes to
        if (playerId == 1) {
            this.player1Hand.add(drawn); //add to hand
            return true;
        } else if (playerId == 2) {
            this.player2Hand.add(drawn); //add to hand
            return true;
        } else return false;
    }

    /**
     * attempts to discard a card from player hand to the discard pile
     *
     * @param playerId id of the player attempting to discard, this param could be replaced by a player object later on,
     *                 in which case a .getId() call would be added to the method
     * @param cardLoc  the location in player hand of the card being discarded
     * @return true if the action was successful, else will return false
     */
    public boolean discard(int playerId, int cardLoc) {

        if (playerId != this.turnId || this.hasGoneOut == playerId || cardLoc < 0) return false;

        //determine which player is discarding
        if (playerId == 1) {
            if (this.player1Hand.size() < cardLoc) return false;

            discardPile.push(this.player1Hand.remove(cardLoc)); //discard to discard pile

            if (this.player1Hand.size() == 0)
                this.hasGoneOut = playerId; //If a player's hand is empty, the other player gets one turn before round ends
            if (!discardPile.peek().isSkip())
                this.turnId = 2; //Skips in 2 player mode allow current player to take 2 back-to-back turns

            return true;
        } else if (playerId == 2) {
            if (this.player2Hand.size() < cardLoc) return false;

            discardPile.push(this.player2Hand.remove(cardLoc)); //discard to discard pile

            if (this.player2Hand.size() == 0) this.hasGoneOut = playerId;
            if (!discardPile.peek().isSkip()) this.turnId = 1;

            return true;
        } else return false;
    }


    /* checks if the play can play a phase, first by seeing what phase
    * the player is on, then my referencing two different methods that checks
    * each card to make sure the play can hit */
    public boolean checkPhase(Phase phase, ArrayList<Card> list1, ArrayList<Card> list2) {
        switch (phase) {
            case PHASE_1:
                return checkIfPhaseOne(cardList1, cardList2);
            case PHASE_2:
                return checkIfPhaseTwo(cardList1, cardList2);
            case PHASE_3:
                return checkIfPhaseThree(cardList1, cardList2);
            case PHASE_4:
                return sevenInARow(cardList1);
            case PHASE_5:
                return eightInARow(cardList1);
            case PHASE_6:
                return nineInARow(cardList1);
            case PHASE_7:
                return checkIfPhaseSeven(cardList1, cardList2);
            case PHASE_8:
                return checkIfPhaseEight(cardList1);
            case PHASE_9:
                return checkIfPhaseNine(cardList1, cardList2);
            case PHASE_10:
                return checkIfPhaseTen(cardList1, cardList2);
            default:
                return false;
        }
    }

    private boolean isTwin(ArrayList<Card> list) {
        if (list.size() == 2) {
            return checkForEqualNumbers(list);
        }
        return false;
    }

    private boolean isTriplet(ArrayList<Card> list) {
        if (list.size() == 3) {
            return checkForEqualNumbers(list);
        }
        return false;
    }

    private boolean isQuad(ArrayList<Card> list) {
        if (list.size() == 4) {
            return checkForEqualNumbers(list);
        }
        return false;
    }

    private boolean isQuintuple(ArrayList<Card> list) {
        if (list.size() == 5) {
            return checkForEqualNumbers(list);
        }
        return false;
    }

    private boolean fourInARow(ArrayList<Card> list) {

        if (list.size() == 4) {
            return checkIfInARow(list);
        }
        return false;
    }

    private boolean sevenInARow(ArrayList<Card> list) {
        if (list.size() == 7) {
            return checkIfInARow(list);
        }
        return false;
    }

    private boolean eightInARow(ArrayList<Card> list) {
        if (list.size() == 8) {
            return checkIfInARow(list);
        }
        return false;
    }

    private boolean nineInARow(ArrayList<Card> list) {
        if (list.size() == 9) {
            return checkIfInARow(list);
        }
        return false;
    }

    private boolean checkIfPhaseOne(ArrayList<Card> list, ArrayList<Card> list2) {
        if (isTriplet(list) && isTriplet(list2)) {
            return true;
        }
        return false;
    }

    private boolean checkIfPhaseTwo(ArrayList<Card> list, ArrayList<Card> list2) {
        return ((isTriplet(list) && fourInARow(list2)) || (isTriplet(list2) && fourInARow(list)));
    }

    private boolean checkIfPhaseThree(ArrayList<Card> list, ArrayList<Card> list2) {
        return ((isQuad(list) && fourInARow(list2)) || (isQuad(list2) && fourInARow(list)));
    }

    private boolean checkIfPhaseSeven(ArrayList<Card> list, ArrayList<Card> list2) {
        if (isQuad(list) && isQuad(list2)) {
            return true;
        }
        return false;
    }

    private boolean checkIfPhaseEight(List<Card> list) {
        if (list.size() == 7) {
            return checkSameColors(list);
        }
        return false;
    }

    public boolean checkSameColors(ArrayList<Card> list) {
        Card col = null;
        for (Card c : list) {
            if (col == null) {
                col = c.getColor();
            } else if (c.getColor() != col) {
                return false;
            }
            return true;
        }
    }

    // allows player to hit
    private boolean checkIfPhaseNine(ArrayList<Card> list, ArrayList<Card> list2) {
        return ((isQuintuple(list) && isTwin(list2)) || (isQuintuple(list2) && isTwin(list)));
    }

    private boolean checkIfPhaseTen(ArrayList<Card> list, ArrayList<Card> list2) {
        return ((isQuintuple(list) && isTriplet(list2)) || (isQuintuple(list2) && isTriplet(list)));
    }

    public boolean checkForEqualNumbers(List<Card> list) {
        int number = 0;
        for (Card c : list) {
            if (number == 0) {
                number = c.getNumber();
            } else if (c.getNumber() != number) {
                return false;
            }
            return true;
        }
    }

    public boolean checkIfInARow(List<Card> list) {
        int number = 0;
        boolean firstNumber = false;

        for (Card c : list) {
            if (!firstNumber) {
                if (number < (c.getNumber()) {
                    number = (c.getNumber());
                    firstNumber = true;
                }
            } else {
                if ((number + 1) == (c.getNumber()) {
                    number = (c.getNumber());
                } else {
                    return false;
                }
            }
            return true;
        }
    }
}
