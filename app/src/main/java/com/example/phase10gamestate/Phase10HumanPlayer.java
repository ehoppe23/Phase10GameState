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
import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View.OnClickListener;

/**
 * A GUI for a human to play Pig. This default version displays the GUI but is incomplete
 *
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class Phase10HumanPlayer extends GameHumanPlayer implements OnClickListener {

    /* instance variables */

    // These variables will reference widgets that will be modified during play
    private TextView    playerScoreTextView = null;
    private TextView    oppScoreTextView    = null;
    private TextView    turnTotalTextView   = null;
    private TextView    messageTextView     = null;
    private Button      phaseButton         = null; // do we want this to be an image button?
    private Button      hitButton           = null;
    private Button      discardButton       = null;
    private ImageButton drawFaceUpImageButton =null;
    private ImageButton drawFaceDownImageButton = null;


    // the android activity that we are running
    private GameMainActivity myActivity;

    /**
     * constructor does nothing extra
     */
    public Phase10HumanPlayer(String name) {
        super(name);
    }

    /**
     * Returns the GUI's top view object
     *
     * @return
     * 		the top object in the GUI's view heirarchy
     */
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    /**
     * callback method when we get a message (e.g., from the game)
     *
     * @param info
     * 		the message
     */
    @Override
    public void receiveInfo(GameInfo info) {
        if(!(info instanceof Phase10GameState)) {
            flash(Color.RED, 500);
            return;
        }

        if (((PigGameState) info).getPlayerTurn() == 1){
            playerScoreTextView.setText(String.valueOf(((Phase10GameState) info).getPlayer1Score()));
            oppScoreTextView.setText(String.valueOf(((Phase10GameState) info).getPlayer2Score()));
        }
        else{
            playerScoreTextView.setText(String.valueOf(((Phase10GameState) info).getPlayer2Score()));
            oppScoreTextView.setText(String.valueOf(((Phase10GameState) info).getPlayer1Score()));

        //TO DO should phase counters be here??
    }//receiveInfo


    /**
     * this method gets called when the user clicks the die or hold button. It
     * creates a new PigRollAction or PigHoldAction and sends it to the game.
     *
     * @param button
     * 		the button that was clicked
     */
    public void onClick(View button) {
        if(button.equals(phaseButton)) {
            phaseAction p = new phaseAction(this);
            game.sendAction(p);
        }
        if(button.equals(hitButton)) {
            hitAction p = new hitAction(this);
            game.sendAction(p);
        }
        if(button.equals(discardButton)) {
            discardAction p = new discardAction(this);
            game.sendAction(p);
        }

        // for the drawFaceDown && drawFace up : click a card, in a specific area (i.e. should the
        // onClick area be fixed like drawPile area is??

        if(button.equals(drawFaceDownImageButton)) {
            drawFaceDownAction p = new drawFaceDownAction(this);
            game.sendAction(p);
        }
        if(button.equals(drawFaceUpImageButton)) {
            drawFaceUpAction p = new drawFaceUpAction(this);
            game.sendAction(p);
        }
    }// onClick

    /**
     * callback method--our game has been chosen/rechosen to be the GUI,
     * called from the GUI thread
     *
     * @param activity
     * 		the activity under which we are running
     */
    public void setAsGui(GameMainActivity activity) {

        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.pig_layout);

        //Initialize the widget reference member variables
        this.playerScoreTextView = (TextView)activity.findViewById(R.id.yourScoreValue);
        this.oppScoreTextView    = (TextView)activity.findViewById(R.id.oppScoreValue);
        this.turnTotalTextView   = (TextView)activity.findViewById(R.id.turnTotalValue);
        this.messageTextView     = (TextView)activity.findViewById(R.id.messageTextView);
        this.dieImageButton      = (ImageButton)activity.findViewById(R.id.dieButton);
        this.holdButton          = (Button)activity.findViewById(R.id.holdButton);

        //Listen for button presses
        dieImageButton.setOnClickListener(this);
        holdButton.setOnClickListener(this);

    }//setAsGui

}// class HumanPlayer