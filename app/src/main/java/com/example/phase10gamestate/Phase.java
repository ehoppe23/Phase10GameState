package com.example.phase10gamestate;

import java.util.ArrayList;
import java.util.List;

public class Phase {

    //needs variables 1-10 phase strings
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


    //Player 1
    ArrayList<Card> play1Run;
    ArrayList<Card> play1Set;
    ArrayList<Card> play1Color;

    //Player 2
    ArrayList<Card> play2Run;
    ArrayList<Card> play2Set;
    ArrayList<Card> play2Color;
    //Are placed in these variables when phasing happens

    //needs constructor

    /* checks if the play can play a phase, first by seeing what phase
     * the player is on, then my referencing two different methods that checks
     * each card to make sure the play can hit */
    public boolean checkPhase(int playerPhase, ArrayList<Card> phaseContent) {
        switch (playerPhase) {
            case 1:
                return checkIfPhaseOne(phaseContent);
            case 2:
                return checkIfPhaseTwo(phaseContent);
            case 3:
                return checkIfPhaseThree(phaseContent);
            case 4:
                return sevenInARow(phaseContent);
            case 5:
                return eightInARow(phaseContent);
            case 6:
                return nineInARow(phaseContent);
            case 7:
                return checkIfPhaseSeven(phaseContent);
            case 8:
                return checkIfPhaseEight(phaseContent);
            case 9:
                return checkIfPhaseNine(phaseContent);
            case 10:
                return checkIfPhaseTen(phaseContent);
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

    private boolean checkIfPhaseOne(ArrayList<Card> list) {
        if (isTriplet(list) ) {
            return true;
        }
        return false;
    }

    private boolean checkIfPhaseTwo(ArrayList<Card> list) {
        return (isTriplet(list) || fourInARow(list));
    }

    private boolean checkIfPhaseThree(ArrayList<Card> list) {
        return (isQuad(list)|| fourInARow(list));
    }

    private boolean checkIfPhaseSeven(ArrayList<Card> list) {
        if (isQuad(list) ) {
            return true;
        }
        return false;
    }

    private boolean checkIfPhaseEight(List<Card> list) {
        if (list.size() == 7) {
            return checkSameColors((ArrayList<Card>) list);
        }
        return false;
    }

    public boolean checkSameColors(ArrayList<Card> list) {
        int col = -10;
        for (Card c : list) {
            if (col == -10) {
                col = c.getColor();
            } else if (c.getColor() != col) {
                return false;
            }
            return true;
        }
        return false;
    }

    // allows player to hit
    private boolean checkIfPhaseNine(ArrayList<Card> list) {
        return ((isQuintuple(list)) || ( isTwin(list)));
    }

    private boolean checkIfPhaseTen(ArrayList<Card> list) {
        return ((isQuintuple(list) ) || ( isTriplet(list)));
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
        return false;
    }

    public boolean checkIfInARow(List<Card> list) {
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

    //Check hit validity method
    // based on phase (switch) what are the phase components
    //which player is it, which phase is it
    // -> okay, can the card be added to run (if theres a run)
    // if not -> can card be added to set (if thers a set)
    // if not -> can card be added to color group (if exists)
    //if not -> return false
}
