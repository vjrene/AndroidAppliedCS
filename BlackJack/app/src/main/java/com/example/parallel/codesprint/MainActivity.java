package com.example.parallel.codesprint;

import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.StringTokenizer;
import java.util.*;



public class MainActivity extends AppCompatActivity {

    public int user_overall_score;
    public int user_turn_score;
    public int cpu_overall_score;
    public int cpu_turn_score;
    public int card_value;

    public String uScoreText = "User Score: " + user_overall_score;
    public String cScoreText = "Computer Score: " + cpu_overall_score;

    public String deb = "Debugging";


    public int[] diceRes = {
            R.drawable.ccard1,
            R.drawable.ccard2,
            R.drawable.ccard3,
            R.drawable.ccard4,
            R.drawable.ccard5,
            R.drawable.ccard6,
            R.drawable.ccard7,
            R.drawable.ccard8,
            R.drawable.ccard9,
            R.drawable.ccard10,
            R.drawable.ccard11,
            R.drawable.ccard12,
            R.drawable.ccard13
    };

    Stack cardDeck = new Stack();


//    TextView user_score_textView = (TextView) findViewById(R.id.uScore);
//    user_score_textView.setText(uScoreText);
//
//    TextView cpu_score_textView = (TextView) findViewById(R.id.cScore);
//    cpu_score_textView.setText(cScoreText);

    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //shuffle the cards
        cardDeck = new Stack();
        int cards_pushed = 0;
        int diceRes_length = diceRes.length;

        while ( cards_pushed < diceRes_length-1)
        {
            int card_number = new Random().nextInt(12);
            if(!cardDeck.contains(card_number))
            {
                cardDeck.push(card_number);
                cards_pushed++;
            }
        }
    }

    protected int radomizer()
    {
        Random rand = new Random();
        int  n = rand.nextInt(12);
        return n;
    }

    public void draw (View v){

        int card_to_display = (int)cardDeck.pop();
        Log.d(deb, "draw: " + card_to_display);

        ImageView img = (ImageView)findViewById(R.id.uDeck);
        img.setImageResource(diceRes[card_to_display]);


        //calculate user score for that draw
        card_value = card_to_display +1;
        user_overall_score += card_value;
        uScoreText = "User Score: " + user_overall_score;
        TextView user_score_textView = (TextView) findViewById(R.id.uScore);
        user_score_textView.setText(uScoreText);

        if (user_overall_score>21){
            System.out.println("bust!!!");

            Button drawBtn = (Button) findViewById(R.id.draw);
            drawBtn.setEnabled(false);

            Button challengeBtn = (Button) findViewById(R.id.challenge);
            challengeBtn.setEnabled(false);

            user_score_textView.setText("USER: BUST!!!!");
        }

    }


    public void reset (View v) {
        user_overall_score =0;
        user_turn_score=0;

        cpu_overall_score=0;
        cpu_turn_score=0;

        uScoreText = "User Score: " + user_overall_score;
        TextView user_score_textView = (TextView) findViewById(R.id.uScore);
        user_score_textView.setText(uScoreText);

        Button drawBtn = (Button) findViewById(R.id.draw);
        drawBtn.setEnabled(true);

        Button challengeBtn = (Button) findViewById(R.id.challenge);
        challengeBtn.setEnabled(true);

        ImageView uDeck = (ImageView) findViewById(R.id.uDeck);
        uDeck.setImageDrawable(null);
    }


}
