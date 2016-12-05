package com.example.parallel.week02scarnesdice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;

import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    Random rand = new Random();

    int userScore = 0;
    int userTurn = 0;
    int userTotal;

    int compScore = 0;
    int compTurn = 0;
    //int compTotal = compScore + compTurn;

    //when you hold, turn is reset to 0

    int value;
    final int winnerScore = 100;

    String diceName;
    String scoreStatementjava = "Your score: " + userScore + ". Computer score: " + compScore + ". Your turn score is: " + userTurn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Button resources
        Button rollBtn = (Button) findViewById(R.id.rollBtn);
        Button holdBtn = (Button) findViewById(R.id.holdBtn);
        Button resetBtn = (Button) findViewById(R.id.resetBtn);


        //shows up on the screen
        TextView scores = (TextView) findViewById(R.id.scoreStatement);
        scores.setText(scoreStatementjava);



        //roll
        rollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gameRules();

                }
            }
        );

        holdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //userScore = userTurn+userScore; //total score = turn score + userscore
                userTurn = 0;

                //compScore = compTurn+compScore;
                compTurn=0;



                TextView scores = (TextView) findViewById(R.id.scoreStatement);
                scoreStatementjava = "User holds. Computer's turn. Your score: " + userScore + ". Computer score: " + compScore + ". Your turn score is: " + userTurn;
                //scoreStatementjava = "User holds. Computer's turn.";
                scores.setText(scoreStatementjava);

                computerTurn();
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userScore =0;
                userTurn =0;


                compScore=0;
                compTurn=0;

                TextView scores = (TextView) findViewById(R.id.scoreStatement);
                scoreStatementjava = "Your score: " + userScore + ". Computer score: " + compScore + ". Your turn score is: " + userTurn;


                //scoreStatementjava= "User hit reset.";
                scores.setText(scoreStatementjava);


            }
        });



    }

    public void computerTurn(){

        Button rollBtn = (Button) findViewById(R.id.rollBtn);
        Button holdBtn = (Button) findViewById(R.id.holdBtn);
        ImageView diceView = (ImageView) findViewById(R.id.diceView);
        final TextView scores = (TextView) findViewById(R.id.scoreStatement);

        Random rand2 = new Random();
        int compvalue = 0;

        diceName="dice"+value;
        int diceFace = getResources().getIdentifier(diceName,"drawable",getPackageName());
        diceView.setImageResource(diceFace);

        while(compvalue != 1) {

            compvalue = rand2.nextInt(6)+1;

            if (compvalue != 1) {

                compTurn++;
                compScore = compScore+compvalue+1;
                //compTotal = compScore;


                //scoreStatementjava ="Computer is playing";
                scoreStatementjava = "Computer's turn. Your score: " + userScore + ". Computer score: " + compScore +
                        ". Comp Turn Score: " + compTurn + ". Your turn score is: " + userTurn;
                scores.setText(scoreStatementjava);

                if (compScore>=winnerScore) { scores.setText("Computer wins!! Play again?");

                    //rollBtn.setOnClickListener(null);
                    //holdBtn.setOnClickListener(null);

                }


            } else if (compvalue == 1) {
                compTurn = 0;

                //scoreStatementjava="computer rolled a 1";
                scoreStatementjava = "Computer rolled a 1. User turn. \n" +
                        "Your score: " + userScore + ". Computer score: " + compScore + ". Your turn score is: " + userTurn;
                scores.setText(scoreStatementjava);

                gameRules();
            }


        }
    }

    //ROLL GAME
    public void gameRules(){

        //drawable
        ImageView diceView = (ImageView) findViewById(R.id.diceView);
        TextView scores = (TextView) findViewById(R.id.scoreStatement);
        value = rand.nextInt(6)+1;
        diceName="dice"+value;
        int diceFace = getResources().getIdentifier(diceName,"drawable",getPackageName());
        diceView.setImageResource(diceFace);

        //if the random number is not 1
        if(value != 1) {
            userTurn++; //increase turn score
            userScore = userScore+value; //score = random no + prev value of score
            //userTotal = userScore;

            //scoreStatementjava="user's turn. hit roll.";
            scoreStatementjava = "User's turn. Your score: " + userScore + ". Computer score: " + compScore + ". Your turn score is: " + userTurn;
            scores.setText(scoreStatementjava);

                if (userScore>=winnerScore) { scores.setText("User wins!! Play again?");

                    //rollBtn.setOnClickListener(null);
                    //holdBtn.setOnClickListener(null);


                }

        } else if(value == 1) {
            userTurn =0;
            scoreStatementjava = "User rolled a 1. Computer's turn. Your score: " + userScore + ". Computer score: " + compScore + ". Your turn score is: "+ userTurn;
            scores.setText(scoreStatementjava);
            computerTurn();

        }




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
