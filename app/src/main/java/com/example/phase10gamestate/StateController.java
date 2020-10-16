package com.example.phase10gamestate;

import android.view.View;

public class StateController implements View.OnClickListener {


    @Override
    public void onClick(View view) {
        Phase10GameState thirdInstance = new Phase10GameState();
        Phase10GameState fourthInstance = new Phase10GameState(thirdInstance);
    }


}
