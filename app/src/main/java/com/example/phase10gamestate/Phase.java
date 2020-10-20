/**
 * @author Kirsten Foster, Alexis Molina, Emily Hoppe, Grace Penunuri
 * Holds information about phases including phase rules, the cards used for the phase
 * Checks if phase is successful
 */

package com.example.phase10gamestate;

import java.util.ArrayList;
import java.util.List;

public class Phase {

    //Phase Rules
    private String Phase1 = "2 sets of 3";
    private String Phase2 = "1 set of 3 and 1 run of 4";
    private String Phase3 = "1 set of 4 and 1 run of 4";
    private String Phase4 = "1 run of 7";
    private String Phase5 = "1 run of 8";
    private String Phase6 = "1 run of 9";
    private String Phase7 = "2 sets of 4";
    private String Phase8 = "7 cards of one color";
    private String Phase9 = "1 set of 5 and 1 set of 2";
    private String Phase10 = "1 set of 5 and 1 set of 3";

    //Player 1
    Card[] play1Run;
    Card[] play1Set1;
    Card[] play1Set2;
    Card[] play1Color;

    //Player 2
    Card[] play2Run;
    Card[] play2Set1;
    Card[] play2Set2;
    Card[] play2Color;
    //Are placed in these variables when phasing happens

    //needs constructor

    /** checks if the play can play a phase, first by seeing what phase
     * the player is on, then by referencing two different methods that checks
     * each card to make sure the play can hit */
    public boolean checkPhase(int playerPhase, ArrayList<Card> phaseContent, int playerNum) {
        Card[] sorted = sortCards(phaseContent);
        switch (playerPhase) {
            case 1:
                return checkIfPhaseOne(sorted, playerNum);
            case 2:
                return checkIfPhaseTwo(sorted, playerNum);
            case 3:
                return checkIfPhaseThree(sorted, playerNum);
            case 4:
                return checkIfPhaseFour(sorted, playerNum);
            case 5:
                return checkIfPhaseFive(sorted, playerNum);
            case 6:
                return checkIfPhaseSix(sorted, playerNum);
            case 7:
                return checkIfPhaseSeven(sorted, playerNum);
            case 8: //Special Boy - 7 cards of 1 color
                if(isColorGroup(sorted, 7,playerNum) != null) return true;
                else return false;
            case 9:
                return checkIfPhaseNine(sorted, playerNum);
            case 10:
                return checkIfPhaseTen(sorted, playerNum);
            default:
                return false;
        }
    }

    /* Each boolean method checks for a specific phase as specified
        in the name, except for 8, which is a simple case that only
        checks for 7 cards of 1 color, and sets whatever parameter is not used to null
        --i.e. if it calls for sets but no runs, then runs for the player is set to null

     */


    public boolean checkIfPhaseOne(Card[] phasecontent, int playerNum){ //2 sets of 3
        if(isSet(phasecontent,3, playerNum,1) != null) {
            Card[] temp = isSet(phasecontent,3, playerNum,1);
            if(isSet(temp,3,playerNum,2) != null){
                if(playerNum == 1) this.play1Run = null;
                else if(playerNum == 2) this.play2Run = null;
                return true;
            }
        }
        return false;
    }

    public boolean checkIfPhaseTwo(Card[] phasecontent, int playerNum){ //1 set of 3 and 1 run of 4
        if(isRun(phasecontent,4, playerNum) != null) {
            Card[] temp = isRun(phasecontent,4, playerNum);
            if(isSet(temp,3,playerNum,1) != null){
                if(playerNum == 1) this.play1Set2 = null;
                else if(playerNum == 2) this.play2Set2 = null;
                return true;
            }
        }
        return false;
    }

    public boolean checkIfPhaseThree(Card[] phasecontent, int playerNum){ //1 set of 4 and 1 run of 4
        if(isRun(phasecontent,4, playerNum) != null) {
            Card[] temp = isRun(phasecontent,4, playerNum);
            if(isSet(temp,4,playerNum,1) != null){
                if(playerNum == 1) this.play1Set2 = null;
                else if(playerNum == 2) this.play2Set2 = null;
                return true;
            }
        }
        return false;
    }

    public boolean checkIfPhaseFour(Card[] phaseContent, int playerNum){//1 run of 7
        if(isRun(phaseContent,7,playerNum) == null) return false;
        else{
            if(playerNum == 1) {
                this.play1Set1 = null;
                this.play1Set2 = null;
            }
            else if(playerNum == 2){
                this.play2Set1 = null;
                this.play2Set2 = null;
            }
            return true;
        }
    }

    public boolean checkIfPhaseFive(Card[] phaseContent, int playerNum){//1 run of 8
        if(isRun(phaseContent,8,playerNum) == null) return false;
        else{
            if(playerNum == 1) {
                this.play1Set1 = null;
                this.play1Set2 = null;
            }
            else if(playerNum == 2){
                this.play2Set1 = null;
                this.play2Set2 = null;
            }
            return true;
        }
    }

    public boolean checkIfPhaseSix(Card[] phaseContent, int playerNum){//1 run of 9
        if(isRun(phaseContent,9,playerNum) == null) return false;
        else{
            if(playerNum == 1) {
                this.play1Set1 = null;
                this.play1Set2 = null;
            }
            else if(playerNum == 2){
                this.play2Set1 = null;
                this.play2Set2 = null;
            }
            return true;
        }
    }

    public boolean checkIfPhaseSeven(Card[] phasecontent, int playerNum){ //2 sets of 4
        if(isSet(phasecontent,4, playerNum,1) != null) {
            Card[] temp = isSet(phasecontent,3, playerNum,1);
            if(isSet(temp,4,playerNum,2) != null){
                if(playerNum == 1) this.play1Run = null;
                else if(playerNum == 2) this.play2Run = null;
                return true;
            }
        }
        return false;
    }

    public boolean checkIfPhaseNine(Card[] phasecontent, int playerNum){ //1 set of 5 and 1 set of 2
        if(isSet(phasecontent,5, playerNum,1) != null) {
            Card[] temp = isSet(phasecontent,3, playerNum,1);
            if(isSet(temp,2,playerNum,2) != null){
                if(playerNum == 1) this.play1Run = null;
                else if(playerNum == 2) this.play2Run = null;
                return true;
            }
        }
        return false;
    }

    public boolean checkIfPhaseTen(Card[] phasecontent, int playerNum){ //1 set of 5 and 1 set of 3
        if(isSet(phasecontent,5, playerNum,1) != null) {
            Card[] temp = isSet(phasecontent,3, playerNum,1);
            if(isSet(temp,3,playerNum,2) != null){
                if(playerNum == 1) this.play1Run = null;
                else if(playerNum == 2) this.play2Run = null;
                return true;
            }
        }
        return false;
    }
    // end of checkIfPhases

    /* isSet checks to find the same number within the phaseContent of a player,
       then sorts those that are the same number into a temporary array
       which is then assigned to a set for a specific player whether that set
       be the first or second set, depending on the checkIfPhase()
       parameters. The remaining not used are stored in a seperate array.

     */
    public Card[] isSet(Card[] checkForSet, int size, int playerNum, int setNum){
        Card[] temp = new Card[size];
        Card[] notInSet = new Card[checkForSet.length-size];
        int notInSetLoc = 0;
        int tempLoc = 0;
        for(int i = 0; i<checkForSet.length; i++) {
            temp[0] = checkForSet[i];
            for (int j = i + 1; j < checkForSet.length; j++) {

                if (checkForSet[j].getNumber() == temp[tempLoc].getNumber()) {
                    temp[tempLoc + 1] = checkForSet[j];
                    tempLoc++;
                } else {
                    notInSet[notInSetLoc] = checkForSet[j];
                    notInSetLoc++;
                }
            }
                if(tempLoc >= temp.length -1){
                    if(playerNum == 1) {
                        if(setNum == 1){
                            this.play1Set1 = temp;
                            if(notInSetLoc == 0){
                                return checkForSet;
                            }
                            return notInSet;
                        }
                        else if(setNum == 2){
                            this.play1Set2 = temp;
                            if(notInSetLoc == 0){
                                return checkForSet;
                            }
                            return notInSet;
                        }

                    }
                    else if(playerNum == 2){
                        if(setNum == 1){
                            this.play2Set1 = temp;
                            if(notInSetLoc == 0){
                                return checkForSet;
                            }
                            return notInSet;
                        }
                        else if(setNum == 2){
                            this.play2Set2 = temp;
                            if(notInSetLoc == 0){
                                return checkForSet;
                            }
                            return notInSet;
                        }
                    }
                }
        }
        return null;
    }

    /**
     * isRun sorts through to play chronologically and stores them as an array for
     * specific player, and those that cannot be sorted chronilogically are stored
     * in an array for those not in a run
     * @param checkForRun
     * @param size
     * @param playerNum
     * @return
     */
    public Card[] isRun(Card[] checkForRun, int size, int playerNum){
        Card[] temp = new Card[size];
        Card[] notInRun = new Card[checkForRun.length-size];
        int notInRunLoc = 0;
        int tempLoc = 0;
        for(int i = 0; i<checkForRun.length; i++) {
            temp[0] = checkForRun[i];
            for (int j = i + 1; j < checkForRun.length; j++) {

                if (checkForRun[j].getNumber() == temp[tempLoc].getNumber() + 1) {
                    temp[tempLoc + 1] = checkForRun[j];
                    tempLoc++;
                } else {
                    notInRun[notInRunLoc] = checkForRun[j];
                    notInRunLoc++;
                }

            }
            if(tempLoc >= temp.length -1){
                    if(playerNum == 1) {
                        this.play1Run = temp;
                        if(notInRunLoc == 0){
                            return checkForRun;
                        }
                        return notInRun;
                    }
                    else if(playerNum == 2){
                        this.play2Run = temp;
                        if(notInRunLoc == 0){
                            return checkForRun;
                        }
                        return notInRun;
                    }
                }
        }
        return null;
    }


    /**
     * isColorGroup check runs through the cards and checks to see how many of the
     *        cards are the same card, checkForColor parameter stores the color in an array,
     *        the size indicates how many need to be the same color, and the playerNum
     *        allows us to designate to which Card set for which player needs to assign the remaining
     *        unused cards and the used cards to which color set
     * @param checkForColor the cards being checked for color match
     * @param size the size of the color set being searched for
     * @param playerNum the number of the player that is phasing
     * @return null if unsuccessful, the extra cards if successful and cards are leftover, or the set if no extra cards
     */
    public Card[] isColorGroup(Card[] checkForColor, int size, int playerNum){
        Card[] temp = new Card[size];
        Card[] notInColor = new Card[checkForColor.length-size];
        int notInColorLoc = 0;
        int tempLoc = 0;
        for(int i = 0; i<checkForColor.length; i++) {
            temp[0] = checkForColor[i];
            for (int j = i + 1; j < checkForColor.length; j++) {

                if (checkForColor[j].getColor() == temp[tempLoc].getColor()) {
                    temp[tempLoc + 1] = checkForColor[j];
                    tempLoc++;
                } else {
                    notInColor[notInColorLoc] = checkForColor[j];
                    notInColorLoc++;
                }

            }
            if(tempLoc >= temp.length -1){
                    if(playerNum == 1) {
                        this.play1Color = temp;
                        if(notInColorLoc == 0){
                            return checkForColor;
                        }
                        return notInColor;
                    }
                    else if(playerNum == 2){
                        this.play2Color = temp;
                        if(notInColorLoc == 0){
                            return checkForColor;
                        }
                        return notInColor;
                    }
                }

        }
        return null;
    }

    /**
     * sortCards sorts through the cards of phaseContent so that
     * for the isRun, isSet, isColorGroup methods the cards can be
     * sorted from smallest to largest via card array as opposed to an ArrayList
     *
     * @param attempt the cards attempting to be phased
     * @return the sorted card array
     */
    public Card[] sortCards(ArrayList<Card> attempt){
        Card[] arr = new Card[attempt.size()];
        int x = 0;
        while(x<attempt.size()){
            arr[x] = attempt.get(x);
            x++;
        }
        for (int i = 0; i < arr.length - 1; i++){
            int index = i;
            for (int j = i + 1; j < arr.length; j++){
                if (arr[j].getNumber() < arr[index].getNumber()) {
                    index = j; //searching for lowest index
                }
            }
            Card smallestNumberCard = arr[index];
            arr[index] = arr[i];
            arr[i] = smallestNumberCard;
        }
        return arr;
    }

    /**
     * checkHitValid checks if the selected card is valid
     * @param selectedCard the card that is being hit
     * @param phaseContent phase that it is being hit to
     * @param playerNum the player that is hitting
     * @return true if hit is successful
     */
    protected boolean checkHitValid(Card selectedCard, ArrayList<Card> phaseContent, int playerNum) {
        if (playerNum == 1) {

            //Runs
            if(this.play1Run != null) {
                Card[] tempPlay1Run = new Card[play1Run.length + 1];
                for (int i = 0; i < play1Run.length; i++) {
                    tempPlay1Run[i] = play1Run[i];
                }
                tempPlay1Run[play1Run.length] = selectedCard;
                if (!(isRun(tempPlay1Run, tempPlay1Run.length, playerNum) == null)) return true;
            }
            //Sets
            if(this.play1Set1 != null) {
                Card[] tempPlay1Set1 = new Card[play1Set1.length + 1];
                for (int i = 0; i < play1Set1.length; i++) {
                    tempPlay1Set1[i] = play1Set1[i];
                }
                tempPlay1Set1[play1Set1.length] = selectedCard;
                if(!(isSet(tempPlay1Set1, tempPlay1Set1.length, playerNum,1)==null)) return true;
            }
            if(this.play1Set2 != null) {
                Card[] tempPlay1Set2 = new Card[play1Set2.length + 1];
                for (int i = 0; i < play1Set2.length; i++) {
                    tempPlay1Set2[i] = play1Set2[i];
                }
                tempPlay1Set2[play1Set2.length] = selectedCard;
                if(!(isSet(tempPlay1Set2, tempPlay1Set2.length, playerNum, 2)==null)) return true;
            }


            //Colors
            if(this.play1Color != null) {
                Card[] tempPlay1Color = new Card[play1Color.length + 1];
                for (int i = 0; i < play1Color.length; i++) {
                    tempPlay1Color[i] = play1Color[i];
                }
                tempPlay1Color[play1Color.length] = selectedCard;

                if (!(isColorGroup(tempPlay1Color, tempPlay1Color.length, playerNum) == null))
                    return true;
                else return false;
            }
        } else if (playerNum == 2) {

            //Runs
            if(this.play2Run != null) {
                Card[] tempPlay2Run = new Card[play2Run.length + 1];
                for (int i = 0; i < play2Run.length; i++) {
                    tempPlay2Run[i] = play2Run[i];
                }
                tempPlay2Run[play2Run.length] = selectedCard;
                if (!(isRun(tempPlay2Run, tempPlay2Run.length, playerNum) == null)) return true;
            }

            //Sets
            if(this.play2Set1 != null) {
                Card[] tempPlay2Set1 = new Card[play2Set1.length + 1];
                for (int i = 0; i < play2Set1.length; i++) {
                    tempPlay2Set1[i] = play2Set1[i];
                }
                tempPlay2Set1[play2Set1.length] = selectedCard;
                if(!(isSet(tempPlay2Set1, tempPlay2Set1.length, playerNum,1)==null)) return true;
            }
            if(this.play2Set2 != null) {
                Card[] tempPlay2Set2 = new Card[play2Set2.length + 1];
                for (int i = 0; i < play2Set2.length; i++) {
                    tempPlay2Set2[i] = play2Set2[i];
                }
                tempPlay2Set2[play2Set2.length] = selectedCard;
                if(!(isSet(tempPlay2Set2, tempPlay2Set2.length, playerNum, 2)==null)) return true;
            }

            //Colors
            if(this.play2Color != null) {
                Card[] tempPlay2Color = new Card[play2Color.length + 1];
                for (int i = 0; i < play2Color.length; i++) {
                    tempPlay2Color[i] = play2Color[i];
                }
                tempPlay2Color[play2Color.length] = selectedCard;

                if (!(isColorGroup(tempPlay2Color, tempPlay2Color.length, playerNum) == null))
                    return true;
                else return false;
            }
        }
        return false;
    }//checkHitValid end
}
