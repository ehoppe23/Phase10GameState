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
            + "They drew a " + drawnCard.toString() + ".\n\n"); //print info about method call

        drawnCard = firstInstance.getDiscardPile().lastElement();
        firstInstance.drawFaceDown(firstInstance.getTurnId());//call method for drawing from discard
        editText.append("Player " + firstInstance.getTurnId() + " has drawn from the discard pile.\n"
                + "They drew a " + drawnCard.toString() + ".\n\n");// print method call info

        if(firstInstance.getTurnId() == 0){ //find discarded card
            drawnCard = firstInstance.getPlayer1Hand().get(1);
        }
        else if(firstInstance.getTurnId() == 1){
            drawnCard = firstInstance.getPlayer2Hand().get(1);
        }
        firstInstance.discard(firstInstance.getTurnId(), 1); //call method to discard
        editText.append("Player "  + firstInstance.getTurnId() + " discarded a "
                + drawnCard.toString() + ".\n\n"); //print info about discard method call

        //firstInstance.playPhase(firstInstance.getTurnId());// call method to play a phase
        //firstInstance.hit(); //call method to hit another player

        Phase10GameState thirdInstance = new Phase10GameState(); //create third instance
        Phase10GameState fourthInstance = new Phase10GameState(thirdInstance); //copy third instance

        //check second and fourth instance equality
        if(secondInstance.toString().equals(fourthInstance.toString())){
            editText.append("The second and fourth instances are identical!\n\n");

            editText.append("Note, for the following two strings each deck of cards was shuffled," +
                    " so the size, rather than content, of card arrays were compared.\n");
            editText.append("Second Instance: \n" + secondInstance.toString()+ "\n\n");
            editText.append("Fourth Instance: \n" + fourthInstance.toString() + "\n");
        }
        else{
            editText.append("Something went wrong! The second and fourth instances are different!\n");
        }

    }



}