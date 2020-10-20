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

    /* checks if the play can play a phase, first by seeing what phase
     * the player is on, then my referencing two different methods that checks
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

    public Card[] isSet(Card[] checkForSet, int size, int playerNum, int setNum){
        Card[] temp = new Card[size];
        Card[] notInSet = new Card[checkForSet.length-size];
        int notInSetLoc = 0;
        int tempLoc = 1;
        for(int i = 0; i<checkForSet.length; i++) {
            temp[0] = checkForSet[i];
            for (int j = i + 1; j < checkForSet.length; j++) {
                if(tempLoc < temp.length){
                    if (checkForSet[j].getNumber() == temp[tempLoc].getNumber() ) {
                        temp[tempLoc + 1] = checkForSet[j];
                        tempLoc++;
                    }
                    else {
                        notInSet[notInSetLoc] = checkForSet[j];
                        notInSetLoc++;
                    }

                }
                else{
                    if(playerNum == 1) {
                        if(setNum == 1){
                            this.play1Set1 = temp;
                            return notInSet;
                        }
                        else if(setNum == 2){
                            this.play1Set2 = temp;
                            return notInSet;
                        }

                    }
                    else if(playerNum == 2){
                        if(setNum == 1){
                            this.play2Set1 = temp;
                            return notInSet;
                        }
                        else if(setNum == 2){
                            this.play2Set2 = temp;
                            return notInSet;
                        }
                    }
                }
            }
        }
        return null;
    }

    public Card[] isRun(Card[] checkForRun, int size, int playerNum){
        Card[] temp = new Card[size];
        Card[] notInRun = new Card[checkForRun.length-size];
        int notInRunLoc = 0;
        int tempLoc = 1;
        for(int i = 0; i<checkForRun.length; i++) {
            temp[0] = checkForRun[i];
            for (int j = i + 1; j < checkForRun.length; j++) {
                if(tempLoc < temp.length){
                    if (checkForRun[j].getNumber() == temp[tempLoc].getNumber() + 1 ) {
                        temp[tempLoc + 1] = checkForRun[j];
                        tempLoc++;
                    }
                    else {
                        notInRun[notInRunLoc] = checkForRun[j];
                        notInRunLoc++;
                    }

                }
                else{
                    if(playerNum == 1) {
                        this.play1Run = temp;
                        return notInRun;
                    }
                    else if(playerNum == 2){
                        this.play2Run = temp;
                        return notInRun;
                    }
                }
            }
        }
        return null;
    }

    public Card[] isColorGroup(Card[] checkForColor, int size, int playerNum){
        Card[] temp = new Card[size];
        Card[] notInColor = new Card[checkForColor.length-size];
        int notInColorLoc = 0;
        int tempLoc = 1;
        for(int i = 0; i<checkForColor.length; i++) {
            temp[0] = checkForColor[i];
            for (int j = i + 1; j < checkForColor.length; j++) {
                if(tempLoc < temp.length){
                    if (checkForColor[j].getColor() == temp[tempLoc].getColor()) {
                        temp[tempLoc + 1] = checkForColor[j];
                        tempLoc++;
                    }
                    else {
                        notInColor[notInColorLoc] = checkForColor[j];
                        notInColorLoc++;
                    }

                }
                else{
                    if(playerNum == 1) {
                        this.play1Color = temp;
                        return notInColor;
                    }
                    else if(playerNum == 2){
                        this.play2Color = temp;
                        return notInColor;
                    }
                }
            }
        }
        return null;
    }
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

    protected boolean checkHitValid(Card selectedCard, ArrayList<Card> phaseContent, int playerNum) {
        if (playerNum == 1) {
            Card[] tempPlay1Run = new Card[play1Run.length+1];
            for(int i = 0; i<play1Run.length; i++){
               tempPlay1Run[i] = play1Run[i];
            }
            tempPlay1Run[play1Run.length] = selectedCard;

            Card[] tempPlay1Set = new Card[play1Set.length+1];
            ArrayList<Card> tempPlay1Set = play1Set;
            tempPlay1Set.add(selectedCard);
            ArrayList<Card> tempPlay1Color = play1Color;
            tempPlay1Color.add(selectedCard);
            if (checkIfInARow(tempPlay1Run)) {
                play1Run = tempPlay1Run;
                return true;
            } else if (checkForEqualNumbers(tempPlay1Set)) {
                play1Set = tempPlay1Set;
                return true;
            } else if (checkSameColors(tempPlay1Color)) {
                play1Color = tempPlay1Color;
                return true;
            }
            return true;

        } else if (playerNum == 2) {
            ArrayList<Card> tempPlay2Run = play2Run;
            tempPlay2Run.add(selectedCard);
            ArrayList<Card> tempPlay2Set = play2Set;
            tempPlay2Set.add(selectedCard);
            ArrayList<Card> tempPlay2Color = play2Color;
            tempPlay2Color.add(selectedCard);
            if (checkIfInARow(tempPlay2Run)) {
                play2Run = tempPlay2Run;
                return true;
            } else if (checkForEqualNumbers(tempPlay2Set)) {
                play1Set = tempPlay2Set;
                return true;
            } else if (checkSameColors(tempPlay2Color)) {
                play2Color = tempPlay2Color;
                return true;
            }
            return true;
        }
        return false;
    }
}
