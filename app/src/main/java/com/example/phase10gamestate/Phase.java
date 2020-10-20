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
    private String Phase1 = "2 sets of 3 ";
    private String Phase2 = "1 set of 3 and 1 run of 4";
    private String Phase3 = "1 set of 4 and 1 run of 4";
    private String Phase4 = "1 run of 7";
    private String Phase5 = "1 run of 8";
    private String Phase6 = "1 run of 9";
    private String Phase7 = "2 sets of 4";
    private String Phase8 = "7 cards of one color";
    private String Phase9 = "1 set of 5 and 1 set of 2";
    private String Phase10 = "1 set of 5 and 1 set of 3";

    //Player 1 phase values
    ArrayList<Card> play1Run;
    ArrayList<Card> play1Set;
    ArrayList<Card> play1Color;

    //Player 2 phase values
    ArrayList<Card> play2Run;
    ArrayList<Card> play2Set;
    ArrayList<Card> play2Color;

    //needs constructor

    /** checks if the play can play a phase, first by seeing what phase
     * the player is on, then my referencing two different methods that checks
     * each card to make sure the play can hit */
    public boolean checkPhase(int playerPhase, ArrayList<Card> phaseContent, int playerNum) {
        switch (playerPhase) {
            case 1:
                if(checkIfPhaseOne(phaseContent)) {
                    checkRunSetColor(playerNum, phaseContent);
                    return true;
                }
            case 2:
                if(checkIfPhaseTwo(phaseContent)) {
                    checkRunSetColor(playerNum, phaseContent);
                    return true;
                }
            case 3:
                if(checkIfPhaseThree(phaseContent)) {
                    checkRunSetColor(playerNum, phaseContent);
                    return true;
                }
            case 4:
                if(sevenInARow(phaseContent)) {
                    checkRunSetColor(playerNum, phaseContent);
                    return true;
                }
            case 5:
                if(eightInARow(phaseContent)) {
                    checkRunSetColor(playerNum, phaseContent);
                    return true;
                }
            case 6:
                if(nineInARow(phaseContent)) {
                    checkRunSetColor(playerNum, phaseContent);
                    return true;
                }
            case 7:
                if(checkIfPhaseSeven(phaseContent)) {
                    checkRunSetColor(playerNum, phaseContent);
                    return true;
                }
            case 8:
                if(checkIfPhaseEight(phaseContent)) {
                    checkRunSetColor(playerNum, phaseContent);
                    return true;
                }
            case 9:
                if(checkIfPhaseNine(phaseContent)) {
                    checkRunSetColor(playerNum, phaseContent);
                    return true;
                }
            case 10:
                if(checkIfPhaseTen(phaseContent)) {
                    checkRunSetColor(playerNum, phaseContent);
                    return true;
                }
            default:
                return false;
        }
    }

    /**
     * checks for if two cards are twins (equal numbers)
     * @param list holds the cards to check
     * @return if they're twins
     */
    private boolean isTwin(ArrayList<Card> list) {
        if (list.size() == 2) {
            return checkForEqualNumbers(list);
        }
        return false;
    }

    /**
     * checks if three cards have equal numbers
     * @param list holds cards to check
     * @return if they're triplets
     */
    private boolean isTriplet(ArrayList<Card> list) {
        if (list.size() == 3) {
            return checkForEqualNumbers(list);
        }
        return false;
    }

    /**
     * checks if four cards have equal numbers
     * @param list holds cards to check
     * @return if they're equal
     */
    private boolean isQuad(ArrayList<Card> list) {
        if (list.size() == 4) {
            return checkForEqualNumbers(list);
        }
        return false;
    }

    /**
     * checks if 5 cards have equal numbers
     * @param list holds cards to check
     * @return if they're equal
     */
    private boolean isQuintuple(ArrayList<Card> list) {
        if (list.size() == 5) {
            return checkForEqualNumbers(list);
        }
        return false;
    }

    /**
     * checks if player has a run of 4
     * @param list of cards to check
     * @return if run of 4 is valid
     */
    private boolean fourInARow(ArrayList<Card> list) {

        if (list.size() == 4) {
            return checkIfInARow(list);
        }
        return false;
    }

    /**
     * checks if player has a run of 7
     * @param list of cards to check
     * @return if it's seven in a row
     */
    private boolean sevenInARow(ArrayList<Card> list) {
        if (list.size() == 7) {
            return checkIfInARow(list);
        }
        return false;
    }

    /**
     * checks if player has a run of 8
     * @param list of cards to check
     * @return if run of 8 is valid
     */
    private boolean eightInARow(ArrayList<Card> list) {
        if (list.size() == 8) {
            return checkIfInARow(list);
        }
        return false;
    }

    /**
     * checks if player has a run of 9
     * @param list of cards to check
     * @return if run of 9 is valid
     */
    private boolean nineInARow(ArrayList<Card> list) {
        if (list.size() == 9) {
            return checkIfInARow(list);
        }
        return false;
    }

    private boolean checkIfPhaseOne(ArrayList<Card> list) {
        return isTriplet(list);
    }

    private boolean checkIfPhaseTwo(ArrayList<Card> list) {
        return (isTriplet(list) || fourInARow(list));
    }

    private boolean checkIfPhaseThree(ArrayList<Card> list) {
        return (isQuad(list)|| fourInARow(list));
    }

    private boolean checkIfPhaseSeven(ArrayList<Card> list) {
        return isQuad(list);
    }

    /**
     * check if player has phase 8
     * @param list of cards to check
     * @return if cards contain phase 8
     */
    private boolean checkIfPhaseEight(ArrayList<Card> list) {
        if (list.size() == 7) {
            return checkSameColors((ArrayList<Card>) list);
        }
        return false;
    }

    /**
     * check if cards are the same color
     * @param list of cards to check
     * @return if cards are the same color
     */
    public boolean checkSameColors(ArrayList<Card> list) {
        int col = -10;
        for (Card c : list) {
            if (col == -10) {
                col = c.getColor();
            } else return c.getColor() == col;
            return true;
        }
        return false;
    }

    private boolean checkIfPhaseNine(ArrayList<Card> list) {
        return ((isQuintuple(list)) || ( isTwin(list)));
    }

    private boolean checkIfPhaseTen(ArrayList<Card> list) {
        return ((isQuintuple(list) ) || ( isTriplet(list)));
    }

    /**
     * checks if cards have equal numbers
     * @param list of cards to check
     * @return if cards are equal
     */
    public boolean checkForEqualNumbers(ArrayList<Card> list) {
        int number = 0;
        for (Card c : list) {
            if (number == 0) {
                number = c.getNumber();
            } else if (c.getNumber() != number) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * checks if cards are a run
     * @param list of cards to check
     * @return if they are in a row
     */
    public boolean checkIfInARow(ArrayList<Card> list) {
        int number = 0;
        boolean firstNumber = false;

        for (Card c : list) {
            if (!firstNumber) {
                if (number < (c.getNumber())){
                    number = (c.getNumber());
                    firstNumber = true;
                }
            } else {
                if ((number + 1) == (c.getNumber())){
                    number = (c.getNumber());
                } else {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**Check hit validity method
    * based on phase (switch) what are the phase components
    * which player is it, which phase is it
    * -> okay, can the card can be added to run (if theres a run)
    *if not -> can card be added to set (if thers a set)
    * if not -> can card be added to color group (if exists)
    * if not -> return false*/

    public boolean checkRunSetColor(int playerNum, ArrayList<Card> phaseContents) {
        if( playerNum ==1) { //for player 1
           for(Card c: phaseContents) {
               if (checkForEqualNumbers(phaseContents) == true) {
                   play1Run.add(c);
               } else if (checkIfInARow(phaseContents) == true) {
                   play1Set.add(c);
               } else if (checkSameColors(phaseContents) == true) {
                   play1Color.add(c);
               } else {
                   return false;
               }
           }
            return true;
        } else if (playerNum ==2) { //for player2
          for(Card c: phaseContents) {
              if (checkForEqualNumbers(phaseContents) == true) {
                  play2Run.add(c);
              } else if (checkIfInARow(phaseContents) == true) {
                  play2Set.add(c);
              } else if (checkSameColors(phaseContents) == true) {
                  play2Color.add(c);
              } else {
                  return false;
              }
          }
          return true;
        } //player 2 check
        return false;
    } // checkRunSetColor

    /**
     *
     * @param selectedCard
     * @param phaseContent
     * @param playerNum
     * @return
     */
    protected boolean checkHitValid(Card selectedCard, ArrayList<Card> phaseContent, int playerNum) {
        if (playerNum == 1) {
                ArrayList<Card> tempPlay1Run = play1Run;
                tempPlay1Run.add(selectedCard);
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

} //Phase Class End
