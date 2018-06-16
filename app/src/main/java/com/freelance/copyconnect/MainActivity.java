package com.freelance.copyconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;

    int gameState[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    boolean isActive = true;


    public void dropIn(View view) {
        System.out.println("jagga lit");

        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] != 2 || !isActive) {
            return;
        }
        gameState[tappedCounter] = activePlayer;
        counter.setImageResource((activePlayer == 0) ? R.drawable.yellow : R.drawable.red);
        activePlayer = 1 - activePlayer;

        for (int[] winningPosition : winningPositions) {
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                    && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                    && gameState[winningPosition[0]] != 2) {
                handleGameCompletion(winningPosition[0] == 0 ? "Player 1 won" : "Player 2 won");
                break;
            } else {
                int i;
                for (i = 0; i < gameState.length; ++i) {
                    if (gameState[i] == 2) {
                        break;
                    }
                }
                if (i == gameState.length) {
                    handleGameCompletion("Its a draw");
                    break;
                }
            }
        }


    }

    public void playAgain(View view) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
        linearLayout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        for (int i = 0; i < gameState.length; ++i) {
            gameState[i] = 2;
        }
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); ++i) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
        isActive = true;
    }

    private void handleGameCompletion(String message) {
        TextView textView = (TextView) findViewById(R.id.winnerMessage);
        textView.setText(message);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
        linearLayout.setVisibility(View.VISIBLE);
        isActive = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

