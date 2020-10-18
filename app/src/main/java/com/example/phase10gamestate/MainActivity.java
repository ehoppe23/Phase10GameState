package com.example.phase10gamestate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    public void onClick(View v) {
        Phase10GameState firstInstance = new Phase10GameState();
        Phase10GameState secondInstance = new Phase10GameState(firstInstance);

        EditText editText = (EditText) findViewById(R.id.actionText);

        //Call all methods:
        //drawFaceDown(int playerID)
        //drawFaceUp(int playerID)
        //discard(int playerID, int cardLoc)
        //playPhase(???)
        //hit(???)

        Card drawnCard = firstInstance.getDrawPile().get(firstInstance.getDiscardPile().size()-1);

        firstInstance.drawFaceDown(firstInstance.getTurnId());
        editText.append("Player " + firstInstance.getTurnId() + " has drawn from the draw pile.\n"
            + "They drew a " + drawnCard.toString() + ".");

        drawnCard = firstInstance.getDiscardPile().lastElement();
        firstInstance.drawFaceDown(firstInstance.getTurnId());
        editText.append("Player " + firstInstance.getTurnId() + " has drawn from the discard pile.\n"
                + "They drew a " + drawnCard.toString() + ".");

        firstInstance.discard(firstInstance.getTurnId(), 1);


        //firstInstance.playPhase();
        //firstInstance.hit();

        Phase10GameState thirdInstance = new Phase10GameState();
        Phase10GameState fourthInstance = new Phase10GameState(thirdInstance);
    }

}