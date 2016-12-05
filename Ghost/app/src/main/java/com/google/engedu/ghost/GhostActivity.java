package com.google.engedu.ghost;

//i got more functionality from adding code to this activity


import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.view.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;


public class GhostActivity extends AppCompatActivity {
    private static final String COMPUTER_TURN = "Computer's turn";
    private static final String USER_TURN = "Your turn";
    private GhostDictionary dictionary;
    private boolean userTurn = false;
    private Random random = new Random();
    String wordFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost);


        //initializes dictionary
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("words.txt");
            dictionary = new SimpleDictionary(inputStream);

        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Could not load dictionary", Toast.LENGTH_LONG);
            toast.show();
        }
        onStart(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost, menu);
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


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event){
        char key = (char) event.getUnicodeChar();

        if(key>='a' && key<='z') { //if user entered a letter
            //add to fragment
            wordFragment += key;
            System.out.println("entered a character " + key);
            System.out.println("current word: " + wordFragment);

            ArrayList<String> dictionaryCheck = SimpleDictionary.words;

            //check
            if(dictionaryCheck.contains(wordFragment)) {
                System.out.println("wordFragment is in dictionary");
            } else {
                computerTurn();
            }

            TextView text = (TextView) findViewById(R.id.ghostText);
            text.setText(wordFragment);
            return true;

        } else {
            System.out.println("enter a character");
            return super.onKeyUp(keyCode, event);

        }

    }

    /**
     * Handler for the "Reset" button.
     * Randomly determines whether the game starts with a user turn or a computer turn.
     * @param view
     * @return true
     */
    public boolean onStart(View view) {

        System.out.println("Started");

        userTurn = random.nextBoolean();
        System.out.println("Picking user");

        TextView text = (TextView) findViewById(R.id.ghostText);
        wordFragment="";
        text.setText(wordFragment);
        TextView label = (TextView) findViewById(R.id.gameStatus);


        if (userTurn) {
            label.setText(COMPUTER_TURN);
            playerTurn();
        } else {
            label.setText(USER_TURN);
            computerTurn();


        }
        return true;
    }

    private void computerTurn() {
        TextView label = (TextView) findViewById(R.id.gameStatus);
        // Do computer turn stuff then make it the user's turn again

    /** //have the computer enter a character
        String alphabet = "abcdefghijklmnopqrstuvwxyz"; //possible letters
        char[] output;
        output = alphabet.toCharArray();

        //random
        Random rand = new Random();
        int num = rand.nextInt(25);

        String compAdded = wordFragment += output[num];
        System.out.println("Comp added: " + compAdded); **/

        //have the computer find a word with that contains wordFragment
        //if wordFragment is not at least 4 letters, pick a random word and add a letter from it
        //if wordFragment is at least 4 letters, find words in dict that contain it, add next letter

        TextView text = (TextView) findViewById(R.id.ghostText);
        text.setText(wordFragment);

        //BinaryWordSearch(); //checking comp entry in dictionary

        findNextChar(wordFragment);

        text.setText(wordFragment);

        userTurn = true;
        label.setText(USER_TURN);


    }

    //for computer method
    private void findNextChar(String prefix){

        String word;
        int prefixLength = prefix.length();
        char c;
        ArrayList<String> words = SimpleDictionary.words;

        if(prefix.equals("")) {
            Random rand = new Random();
            int num = rand.nextInt(words.size());
            word = words.get(num);
            System.out.println("word chosen: " + word);
            c = word.charAt(0);
            wordFragment += c;

            TextView label = (TextView) findViewById(R.id.gameStatus);
            label.setText(wordFragment);


        } else {
            //if prefix word is not empty, binary search for prefix in array list
            //after finding the prefix in the dictionary (contains)
            //add next character

            int n = words.size();
            //char c = prefix.charAt(0);

            int start = 0;
            int end = n-1;

            while (start<=end) {
                int mid = (start+end)/2;
                int compare = prefix.compareTo(words.get(mid));

                if(words.get(mid).startsWith(prefix)) {
                    //middle word in array list contains prefix
                    //add next character in word to wordFragment
                    String goodWord = words.get(mid);
                    if (goodWord.length() > prefixLength) {
                        wordFragment += goodWord.charAt(prefixLength);
                        System.out.println("word now: " + wordFragment);
                        break;
                    } else {
                        System.out.println("idk");
                    }

                }
                if(compare<0){
                    end = mid-1;
                }
                if(compare>0) {
                    start = mid+1;
                }
            }

        }

    }



    private void playerTurn() {

        TextView text = (TextView) findViewById(R.id.ghostText);
        text.setText(wordFragment);
        TextView label = (TextView) findViewById(R.id.gameStatus);



        //challenge computer's word if compl has formed a valid word
        //if word isValid
        //player1 wins

        //challenge comp's word if no word can be formed with current fragment
        //waits for comp to add a valid character to complete word
        //if word isValid
        //comp wins

        //if it's p1 turn, add a letter to move the word towards a valid word


        //bluff computer by adding letter that doesn't move the fragment

        //then...
        userTurn = false;
        label.setText(COMPUTER_TURN);

    }

    public boolean challenge(View view){

        System.out.println("i have challenged you");

        ArrayList<String>words = SimpleDictionary.words;
        TextView status = (TextView) findViewById(R.id.gameStatus);

        if(wordFragment.length()>=4 && words.contains(wordFragment)) {
            status.setText("User wins!!!!");
        } else{ //if anything starts with the wordfrag
            status.setText("User loses. Computer wins!");
        }

        //if wordFragment.length()>4 && if the word in the dictionary
        //if it is a word, whoever challenged wins

        //if not a word then whoever challenged loses

        return true;

    }



}
