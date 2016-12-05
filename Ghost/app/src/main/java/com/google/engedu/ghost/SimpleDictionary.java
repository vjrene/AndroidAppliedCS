package com.google.engedu.ghost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class SimpleDictionary implements GhostDictionary {
    public static ArrayList<String> words; //dictionary
    public int MIN_WORD_LENGTH = 4;

    public SimpleDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        words = new ArrayList<>();
        String line = null;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() >= MIN_WORD_LENGTH)
              words.add(line.trim());
        }
    }

    @Override
    public boolean isWord(String word) {
        return words.contains(word);
    }


    @Override
    public String getAnyWordStartingWith(String prefix) {



        //checks wordFragment against words in the dictionary
        //if there is a word in the dictionary that starts with wordFragment
        //have the comp add in the next letter


        return null;
    }



    @Override
    public String getGoodWordStartingWith(String prefix) {

        String selected = null;

        return selected;
    }
}
