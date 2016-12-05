package com.example.parallel.rockpaperscissors;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //game
    int userScore = 0;
    int computerScore = 0;
    int round =0;
    final int totalRound = 10;
    String uss = "User Score: "+ userScore;
    String css = "Computer Score: "+ computerScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView compScoreTxt = (TextView) findViewById(R.id.compScore);
        compScoreTxt.setText(css);
        TextView uScoreTxt = (TextView) findViewById(R.id.uScore);
        uScoreTxt.setText(uss);

        TextView roundTxt = (TextView) findViewById(R.id.round);
        roundTxt.setText("Round: "+round+"/"+totalRound);
    }

    public void shootRock(View v) {

        ImageView uHand = (ImageView) findViewById(R.id.uHand);
        Context mContext = getApplicationContext();
        Drawable drawable;

        TextView uPlayed = (TextView) findViewById(R.id.uPlayed);
        uPlayed.setText("rock");

        //if they hit rock, show rock
        drawable = ContextCompat.getDrawable(mContext, R.drawable.rock);
        uHand.setImageDrawable(drawable);

        compShoot();
        compare();
        round++;

        TextView roundTxt = (TextView) findViewById(R.id.round);
        roundTxt.setText("Round: "+round+"/"+totalRound);
    }

    public void shootPaper(View v){

        ImageView uHand = (ImageView) findViewById(R.id.uHand);
        Context mContext = getApplicationContext();
        Drawable drawable;

        TextView uPlayed = (TextView) findViewById(R.id.uPlayed);
        uPlayed.setText("paper");

        drawable = ContextCompat.getDrawable(mContext, R.drawable.paper);
        uHand.setImageDrawable(drawable);

        compShoot();
        compare();
        round++;
        TextView roundTxt = (TextView) findViewById(R.id.round);
        roundTxt.setText("Round: "+round+"/"+totalRound);
    }

    public void shootScissors(View v){

        ImageView uHand = (ImageView) findViewById(R.id.uHand);
        Context mContext = getApplicationContext();
        Drawable drawable;

        TextView uPlayed = (TextView) findViewById(R.id.uPlayed);
        uPlayed.setText("scissors");

        drawable = ContextCompat.getDrawable(mContext, R.drawable.scissors);
        uHand.setImageDrawable(drawable);

        compShoot();
        compare();
        round++;
        TextView roundTxt = (TextView) findViewById(R.id.round);
        roundTxt.setText("Round: "+round+"/"+totalRound);

    }

    private void compare(){

        TextView compScoreTxt = (TextView) findViewById(R.id.compScore);
        TextView uScoreTxt = (TextView) findViewById(R.id.uScore);

        TextView uPlayed = (TextView) findViewById(R.id.uPlayed);
        TextView cPlayed = (TextView) findViewById(R.id.cPlayed);

        uss = "User Score: "+ userScore;
        css = "Computer Score: "+ computerScore;

        Button rockBtn = (Button) findViewById(R.id.rock);
        Button paperBtn = (Button) findViewById(R.id.paper);
        Button scisBtn = (Button) findViewById(R.id.scissors);

        ImageView uHand = (ImageView) findViewById(R.id.uHand);
        ImageView cHand = (ImageView) findViewById(R.id.compHand);

        if(round>=9 && userScore>computerScore) {
            rockBtn.setEnabled(false);
            paperBtn.setEnabled(false);
            scisBtn.setEnabled(false);
            uScoreTxt.setText("User wins!!!");
            uHand.setBackgroundColor(Color.parseColor("#81C784"));
        } if (round>=9 && userScore<computerScore) {
            rockBtn.setEnabled(false);
            paperBtn.setEnabled(false);
            scisBtn.setEnabled(false);
            compScoreTxt.setText("Computer wins!!!");
            cHand.setBackgroundColor(Color.parseColor("#81C784"));
        }




        //user picks scissors
        if (uPlayed.getText().toString().contains("scissors") && cPlayed.getText().toString().contains("scissors")) {
        }
        if (uPlayed.getText().toString().contains("scissors") && cPlayed.getText().toString().contains("rock")) {
            computerScore++;
            compScoreTxt.setText("compScore: " + computerScore);

        }
        if (uPlayed.getText().toString().contains("scissors") && cPlayed.getText().toString().contains("paper")) {
            userScore++;
            uScoreTxt.setText("userScore: " + userScore);

        }


        //user pics rock
        if (uPlayed.getText().toString().contains("rock") && cPlayed.getText().toString().contains("rock")) {
        }
        if (uPlayed.getText().toString().contains("rock") && cPlayed.getText().toString().contains("paper")) {
            computerScore++;
            compScoreTxt.setText("compScore: " + computerScore);
        }
        if (uPlayed.getText().toString().contains("rock") && cPlayed.getText().toString().contains("scissors")) {
            userScore++;
            uScoreTxt.setText("userScore: " + userScore);
        }

        //user picks paper
        if (uPlayed.getText().toString().contains("paper") && cPlayed.getText().toString().contains("paper")) {
        }
        if (uPlayed.getText().toString().contains("paper") && cPlayed.getText().toString().contains("rock")) {
            userScore++;
            uScoreTxt.setText("userScore: " + userScore);
        }
        if (uPlayed.getText().toString().contains("paper") && cPlayed.getText().toString().contains("scissors")) {
            computerScore++;
            compScoreTxt.setText("compScore: " + computerScore);
        }
    }

    private void compShoot() {
        Random rand = new Random();
        int num = rand.nextInt(3);
        ImageView compHand = (ImageView) findViewById(R.id.compHand);
        Context mContext = getApplicationContext();
        Drawable drawable;

        if (num == 0) {
            //show rock
            TextView cPlayed = (TextView) findViewById(R.id.cPlayed);
            cPlayed.setText("rock");

             drawable = ContextCompat.getDrawable(mContext, R.drawable.rock);
            compHand.setImageDrawable(drawable);

        }
        if (num == 1) {
            //show paper
            TextView cPlayed = (TextView) findViewById(R.id.cPlayed);
            cPlayed.setText("paper");


            drawable = ContextCompat.getDrawable(mContext, R.drawable.paper);
            compHand.setImageDrawable(drawable);
        }
        if (num == 2) {
            //show scissors

            TextView cPlayed = (TextView) findViewById(R.id.cPlayed);
            cPlayed.setText("scissors");

            drawable = ContextCompat.getDrawable(mContext, R.drawable.scissors);
            compHand.setImageDrawable(drawable);
        }
    }

    public void rematch(View v) {
        userScore =0;
        computerScore=0;
        round = 0;

        uss = "User Score: "+ userScore;
        css = "Computer Score: "+ computerScore;

        TextView compScoreTxt = (TextView) findViewById(R.id.compScore);
        compScoreTxt.setText(css);
        TextView uScoreTxt = (TextView) findViewById(R.id.uScore);
        uScoreTxt.setText(uss);

        Button rockBtn = (Button) findViewById(R.id.rock);
        Button paperBtn = (Button) findViewById(R.id.paper);
        Button scisBtn = (Button) findViewById(R.id.scissors);

        rockBtn.setEnabled(true);
        paperBtn.setEnabled(true);
        scisBtn.setEnabled(true);

        TextView cPlayed = (TextView) findViewById(R.id.cPlayed);
        cPlayed.setText(null);
        TextView uPlayed = (TextView) findViewById(R.id.uPlayed);
        uPlayed.setText(null);

        ImageView cHand = (ImageView) findViewById(R.id.compHand);
        cHand.setImageDrawable(null);


        cHand.setBackgroundColor(Color.parseColor("#FFFFFF"));

        ImageView uHand = (ImageView) findViewById(R.id.uHand);
        uHand.setImageDrawable(null);
        uHand.setBackgroundColor(Color.parseColor("#FFFFFF"));

        TextView roundTxt = (TextView) findViewById(R.id.round);
        roundTxt.setText("Round: " + round +"/"+totalRound);



    }
}
