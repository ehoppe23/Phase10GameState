/**
 * @author Kirsten Foster, Alexis Molina, Emily Hoppe, Grace Penunuri
 * Holds onCreate to set up the layout
 * Holds onClick to check each action method
 */

package com.example.phase10gamestate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * onCreate sets up original interface
     * @param savedInstanceState holds the instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button runTest = findViewById(R.id.testButton);
        runTest.setOnClickListener(this::onClick);

    }

    //end game


    /**
     * Tests each action method and prints out results
     * @param v holds view to use
     */
    public void onClick(View v) {
        Phase10GameState firstInstance = new Phase10GameState(); //create first instance
        Phase10GameState secondInstance = new Phase10GameState(firstInstance); //copy first instance

        firstInstance.setTurnId(1); //creates mock game where player1 goes first
        firstInstance.setGoesFirst(1);

        ArrayList<Card> givenHand = new ArrayList<Card>(); //dummy hand to make phase successful
        givenHand.add(new Card(1,1));
        givenHand.add(new Card(1,2));
        givenHand.add(new Card(1,3));
        givenHand.add(new Card(1,4));
        givenHand.add(new Card(2,4));
        givenHand.add(new Card(2,1));
        givenHand.add(new Card(2,2));
        givenHand.add(new Card(2,3));
        ArrayList<Card> possiblePhase = givenHand;
        possiblePhase.remove(4);
        possiblePhase.remove(3);

        firstInstance.setPlayer1Hand(givenHand);
        firstInstance.setPlayer2Hand(givenHand);
        firstInstance.setPlayer1Phase(1);
        firstInstance.setPlayer2Phase(1);

        EditText editText = (EditText) findViewById(R.id.actionText); //reference editText

        Card drawnCard = firstInstance.getDrawPile().get(0); //store card to use in string

        firstInstance.drawFaceDown(firstInstance.getTurnId()); // call method for drawing from draw pile
        editText.append("Player " + firstInstance.getTurnId() + " has drawn from the draw pile.\n"
            + "They drew a " + drawnCard.toString() + ".\n\n"); //print info about method call

        drawnCard = firstInstance.getDiscardPile().lastElement();
        firstInstance.drawFaceDown(firstInstance.getTurnId());//call method for drawing from discard
        editText.append("Player " + firstInstance.getTurnId() + " has drawn from the discard pile.\n"
                + "They drew a " + drawnCard.toString() + ".\n\n");// print method call info

        if(firstInstance.getTurnId() == 1){ //find discarded card
            drawnCard = firstInstance.getPlayer1Hand().get(1);
        }
        else if(firstInstance.getTurnId() == 2){
            drawnCard = firstInstance.getPlayer2Hand().get(1);
        }
        firstInstance.discard(firstInstance.getTurnId(), 1); //call method to discard
        editText.append("Player "  + firstInstance.getTurnId() + " discarded a "
                + drawnCard.toString() + ".\n\n"); //print info about discard method call


        if (firstInstance.playPhase(firstInstance.getTurnId(), possiblePhase)) {// call method to play a phase
            editText.append("Player " + firstInstance.getTurnId() + " played a phase!\n\n");
        }

        if(firstInstance.getTurnId() == 1){ //find card to use for hit
            drawnCard = firstInstance.getPlayer1Hand().get(3);
        }
        else if(firstInstance.getTurnId() == 2){
            drawnCard = firstInstance.getPlayer2Hand().get(3);
        }
        if(firstInstance.hitPlayer(firstInstance.getTurnId(), drawnCard, firstInstance.getTurnId())) { //call method to hit another player
            editText.append("Player " + firstInstance.getTurnId() + " has a hit player " + firstInstance.getTurnId() + "!\n\n");
        }

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