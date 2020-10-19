package com.example.phase10gamestate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button runTest = findViewById(R.id.testButton);
        runTest.setOnClickListener(this::onClick);

    }

    //end game
    @Override
    protected void Quit(Bundle savedInstanceState, View Button){
        Log.i("button", "Exit and Save Game");
        super.onCreate(savedInstanceState);
        finish();
    }

    public void onClick(View v) {
        Phase10GameState firstInstance = new Phase10GameState(); //create first instance
        Phase10GameState secondInstance = new Phase10GameState(firstInstance); //copy first instance

        EditText editText = (EditText) findViewById(R.id.actionText); //reference editText

        //Call all methods:
        //drawFaceDown(int playerID)
        //drawFaceUp(int playerID)
        //discard(int playerID, int cardLoc)
        //playPhase(???)


        //hit(???)

        Card drawnCard = firstInstance.getDrawPile().get(0); //store card to use in string

        firstInstance.drawFaceDown(firstInstance.getTurnId()); // call method for drawing from draw pile
        editText.append("Player " + firstInstance.getTurnId() + " has drawn from the draw pile.\n"
            + "They drew a " + drawnCard.toString() + ".\n"); //print info about method call

        drawnCard = firstInstance.getDiscardPile().lastElement();
        firstInstance.drawFaceDown(firstInstance.getTurnId());//call method for drawing from discard
        editText.append("Player " + firstInstance.getTurnId() + " has drawn from the discard pile.\n"
                + "They drew a " + drawnCard.toString() + ".\n");// print method call info

        if(firstInstance.getTurnId() == 1){ //find discarded card
            drawnCard = firstInstance.getPlayer1Hand().get(1);
        }
        else if(firstInstance.getTurnId() == 2){
            drawnCard = firstInstance.getPlayer2Hand().get(1);
        }
        firstInstance.discard(firstInstance.getTurnId(), 1); //call method to discard
        editText.append("Player "  + firstInstance.getTurnId() + " discraded a "
                + drawnCard.toString() + ".\n"); //print info about discard method call

        //firstInstance.playPhase(); //call method to play a phase
        //firstInstance.hit(); //call method to hit another player

        Phase10GameState thirdInstance = new Phase10GameState(); //create third instance
        Phase10GameState fourthInstance = new Phase10GameState(thirdInstance); //copy third instance

        //check second and fourth instance equality
        if(secondInstance.toString().equals(fourthInstance.toString())){
            editText.append("The second and fourth instances are identical!\n");
        }
        else{
            editText.append("Something went wrong! The second and fourth instances are different!\n");
        }
    }



}