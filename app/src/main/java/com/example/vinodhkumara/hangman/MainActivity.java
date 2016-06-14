package com.example.vinodhkumara.hangman;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    public Context mContext = null;
    public GridView mGridView = null;
    public Utils mUtils = null;
    TextView mTextWord = null;
    TextView mTextGuessed = null;
    TextView mTextLife = null;
    private String mWordText = null;
    private String mWord = null;
    private String mGuessed = "Guessed Latters :";
    private String mLife = "Life :";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        mUtils = new Utils(mContext);
        mWord = mUtils.getRandomWord();
        Log.d("TAG", "getRandomWord  mWord=" + mWord);
        initUI();
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Log.d("TAG", "setOnItemClickListener Possition = " + position);
                boolean mLetterExist = false;
                mUtils.playGame(position);
                updateUI();
            }
        });
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

    private void initUI() {
        mTextWord = (TextView) findViewById(R.id.wordtext);
        mTextGuessed = (TextView) findViewById(R.id.guessed_word);
        mTextLife = (TextView) findViewById(R.id.life);
        //mTextWord.setText(mWord);
        mGridView = (GridView) findViewById(R.id.gridview);
        mGridView.setAdapter(new TextAdapter(this));
        //mTextGuessed.setText(mGuessed);
        //mTextLife.setText(mLife);
        initiliseTextUI();
    }


    private void initiliseTextUI() {
        Log.d("TAG", "Inside initiliseTextUI");
        mWordText = mUtils.hideWord(mWord);
        String mGuessLetter = mGuessed + mUtils.getGessedLetters();
        String mLifeLeft =  mLife + mUtils.getRemaingLife();
        Log.d("TAG", "initiliseTextUI mWordText =" + mWordText);
        //mTextWord.setText(mWordText);
        updateWordUI(mWordText);
        mTextGuessed.setText(mGuessLetter);
        mTextLife.setText(mLifeLeft);
    }
    private void updateUI() {
        Log.d("TAG", "Inside updateUI");
        mWordText = mUtils.getUpdatedWord();
        String mGuessLetter = mGuessed + mUtils.getGessedLetters();
        String mLifeLeft =  mLife + mUtils.getRemaingLife();
        //mTextWord.setText(mWordText);
        updateWordUI(mWordText);
        mTextGuessed.setText(mGuessLetter);
        mTextLife.setText(mLifeLeft);
        checkWinningCondition();
    }

    private void checkWinningCondition() {
        Log.d("TAG", "Inside checkWinningCondition");
        boolean mGameWon = false;
        if (mWordText.equals(mWord)) {
            mGameWon = true;
            showPopUP(mWord,mGameWon);
        } else if (mUtils.getRemaingLife() == 0) {
            mGameWon = false;
            showPopUP(mWord,mGameWon);
        }
    }

    private void showPopUP(String mStr, boolean mWon) {
        if (mWon) {
            Toast.makeText(mContext, "Great !! GAME OVER!! The Word is" + mStr,
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(mContext, "Better luck next Time !! GAME OVER!! The Word is" + mStr,
                    Toast.LENGTH_LONG).show();
        }
        finish();
    }

    /*private String updateWordUI (String mStr) {
        String mText = mStr;
        String mUpdatedWord = null;
        for(int i = 0; i < mText.length(); i++) {
            mUpdatedWord =  mUpdatedWord + mText.charAt(i) + "";
        }
        Log.d("TAG", "Inside checkWinningCondition" + mUpdatedWord);
        return mUpdatedWord;
    }*/

    private void updateWordUI(String str) {
        String mText = str;
        Log.d("TAG", "updateWordUI mText Before = " + mText );
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length() - 1; i++) {
            sb.insert(++i, ' ');
        }
        mText = sb.toString();
        Log.d("TAG", "updateWordUI mText After = " + mText );
        mTextWord.setText(sb.toString());
    }
}
