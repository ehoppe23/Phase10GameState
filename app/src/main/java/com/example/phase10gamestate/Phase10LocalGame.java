/**
* @author Kirsten Foster, Alexis Molina, Emily Hoppe, Grace Penunuri
 * Holds information about each card (particularly number and color)
 * Also includes getters/setters for card info
 * Includes a "to string" method to explain what the card is
*/

package com.example.phase10gamestate;

/*
EXTERNAL CITATION https://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/
 */
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;

public class Phase10LocalGame extends LocalGame {

    /* @author Kirsten Foster, Alexis Molina, Emily Hoppe, Grace Penunuri
*/
    private Phase10GameState pgs;

    /**
     * This ctor creates a new game state
     */
    public Phase10LocalGame() {
        this.pgs = new Phase10GameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        if (playerIdx == this.pgs.getPlayerTurn()) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        if (action instanceof PhaseAction) {
            this.pgs.playPhase(this.pgs.getTurnID(), PhaseAction.getPhaseContent());
            return true;
        } else if (action instanceof DiscardAction) {
            this.pgs.discard(this.pgs.getTurnID(), DiscardAction.getCard());
            return true;
        } else if (action instanceof HitAction) {
            this.pgs.hit(this.pgs.getTurnID(), HitAction.getCard());
            return true;
        } else if (action instanceof DrawFaceDownAction) {
            this.pgs.drawFaceDown(this.pgs.getTurnID());
            return true;
        } else if (action instanceof DrawFaceUpAction) {
            this.pgs.drawFaceUpAction(this.pgs.getTurnID());
            return false;
        }
    }//makeMove
        /**
         * send the updated state to a given player
         */
        @Override
        protected void sendUpdatedStateTo(GamePlayer p) {
            Phase10GameState cp = new Phase10GameState(this.pgs);
            p.sendInfo(cp);
        }//sendUpdatedSate

        /**
         * Check if the game is over
         *
         * @return
         * 		a message that tells who has won the game, or null if the
         * 		game is not over
         */
        @Override
        protected String checkIfGameOver() {
            int s0= this.pgs.getPlayer1Phase();
            int s1= this.pgs.getPlayer2Phase();
            if(s0 == 10) {
                return this.playerNames[1]+ " wins!";
            }
            if (s1 == 10) {
                return this.playerNames[2]+ " wins!";
            }
            return null;
        }

    }// class LocalGame