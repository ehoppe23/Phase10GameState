package com.example.phase10gamestate;

public class Phase10GameState {

    //Resources
    private int turnId; //1 or 2
    private Card [] player1Hand;
    private Card [] player2Hand;
    private Card [] Player1PhaseContent;
    private Card [] Player2PhaseContent;
    private boolean player1HasPhased;
    private boolean player2HasPhased;
    private int player1Score;
    private int player2Score;
    private int player1Phase;
    private int player2Phase;
    private int hasGoneOut; //set to zero until a player goes out, then set to player Id


}
