package com.example.vinodhkumara.hangman;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by vinodh.kumara on 12/15/2015.
 */
public class Utils {
    public ArrayList<String> mText = new ArrayList<String>(Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"));
    public String mWord = null;
    public String mHiddenWord = null;
    public String mGuessedLetters = "aeiou";
    public Integer mLife = 7;
    public ArrayList<String> mWordsList = new ArrayList<String>();
    static BufferedReader mBufferFileReader = null;
    public Context mContext = null;

    public Utils(Context mCxt) {
        mContext = mCxt;
        loadDataFromAsset(mContext);
        readTheWordsFromList();
    }

    public String getRandomWord() {
        Log.d("TAG", "Inside getRandomword");
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(mWordsList.size());
        Log.d("TAG", "Inside getRandomword Index =" + index);
        mWord  = mWordsList.get(index);
        Log.d("TAG", "Inside getRandomword mWord =" + mWord);
        return mWord;
    }

    public String hideWord(String mStr) {
        Log.d("TAG", "hideWord  mStr=" + mStr);
        for(int i = 0; i < mStr.length(); i++) {
            Log.d("TAG", "hideWord  index=" + i);
            if (mStr.charAt(i) == 'a' || mStr.charAt(i) == 'e' || mStr.charAt(i) == 'i' || mStr.charAt(i) == 'o' || mStr.charAt(i) == 'u') {
                Log.d("TAG", "hideWord  continue =" + i);
                continue;
            } else {
                Log.d("TAG", "hideWord  else continue =" + i);
                mStr = mStr.replace(mStr.charAt(i), '_');
                //mStr = changeCharInPosition(i, '_', mStr);
                Log.d("TAG", "hideWord After change =" + mStr);
            }
        }
        mHiddenWord = mStr;
        Log.d("TAG", "hideWord  mHiddenWord =" + mHiddenWord);
        return mHiddenWord;
    }

    public String changeCharInPosition(int position, char ch, String str){
        char[] charArray = str.toCharArray();
        charArray[position] = ch;
        return new String(charArray);
    }

    public boolean checkWordContainsLetter(int position, String mWord) {
        boolean mResult = false;
        String mLetter = mText.get(position);

        if (mWord.contains(mLetter)) {
            mResult = true;
        }
        return mResult;
    }

    public void playGame(int position) {
        String mLetter = mText.get(position);
        mLetter = mLetter.toLowerCase();

        Log.d("TAG", "playGame mLetter =" + mLetter);
        if (mWord.contains(mLetter)) {
            Log.d("TAG", "playGame call fillLetter =" + mLetter);
            fillLetter(mLetter);

        } else {
            mGuessedLetters = mGuessedLetters + mLetter;
            mLife = mLife - 1;
            // Do something
        }
    }

    public void fillLetter(String mLetter) {
        Log.d("TAG", "Inside fillLetter mLetter =" + mLetter);
        for(int i = 0; i < mWord.length(); i++) {
            Log.d("TAG", "fillLetter i =" + i + "Letter = " + mLetter.charAt(0));
            if (mLetter.charAt(0) == mWord.charAt(i)) {
                Log.d("TAG", "fillLetter Before change =" + mHiddenWord);
                mHiddenWord = changeCharInPosition(i, mLetter.charAt(0), mHiddenWord);
                Log.d("TAG", "fillLetter After change =" + mHiddenWord);
            }
        }
        if (!mGuessedLetters.contains(mLetter)) {
            mGuessedLetters = mGuessedLetters + mLetter;
        } else {
            mLife = mLife - 1;
        }
    }

    public String getUpdatedWord() {
        Log.d("TAG", "getUpdatedWord =" + mHiddenWord);
        return mHiddenWord;
    }

    public String getGessedLetters() {
        Log.d("TAG", "getGessedLetters =" + mGuessedLetters);
        return mGuessedLetters;
    }

    public Integer getRemaingLife() {
        Log.d("TAG", "getRemaingLife =" + mLife);
        return mLife;
    }

    public void loadDataFromAsset(Context mContext) {
        Log.d("TAG", "Inside loadDataFromAsset");
        BufferedReader reader = null;
        AssetManager assetManager = mContext.getAssets();
        try {
            /*reader = new BufferedReader(
                    new InputStreamReader(assetManager.open("handwords.txt")));*/
            reader = new BufferedReader(
                    new InputStreamReader(mContext.getResources().openRawResource(R.raw.hangwords)));
            //InputStream inputStream = assetManager.open("handwords.txt");
            //InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            //reader = new BufferedReader(inputStreamReader);
            if (reader == null) {
                Log.d("TAG", "loadDataFromAsset reader is null");
            } else {
                Log.d("TAG", "loadDataFromAsset reader is not null");
            }
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                Log.d("TAG", "loadDataFromAsset" + mLine);
                mWordsList.add(mLine);
            }
        } catch (IOException e) {
            //log the exception
            Log.d("TAG", "loadDataFromAsset Exception");
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    Log.d("TAG", "loadDataFromAsset Close");
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                    Log.d("TAG", "loadDataFromAsset Close Catch");
                }
            }
        }
        /*try {
            Log.d("TAG", "loadDataFromAsset file open");
            InputStream inputStream = assetManager.open("handwords.txt");
        } catch (IOException e) {
            Log.d("TAG", "loadDataFromAsset Exception");
            e.printStackTrace();
        }*/
    }

    public void readTheWordsFromList() {
        Log.d("TAG", "Inside readTheWordsFromList");
        int i = 0;
        for (String mWord : mWordsList) {
            Log.d("TAG", "Inside readTheWordsFromList");
            Log.d("TAG", "readTheWordsFromList mWord" + i + ":" + mWord);
            i++;
        }
    }
}
