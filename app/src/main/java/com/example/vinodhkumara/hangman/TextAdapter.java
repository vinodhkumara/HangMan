package com.example.vinodhkumara.hangman;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by vinodh.kumara on 12/15/2015.
 */
public class TextAdapter extends BaseAdapter {

    private Context mContext;
    private String[] mText = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    public TextAdapter(Context c) {
        Log.d("TAG", "Inside TextAdapter constructor");
        mContext = c;
    }
    @Override
    public int getCount() {
        return mText.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        //Log.d("TAG", "Inside getView");
        TextView mTextView;
        Button mBtnView;
        ImageButton mBtnImageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            mTextView = new TextView(mContext);
            mTextView.setLayoutParams(new GridView.LayoutParams(85, 85));
        } else {
            mTextView = (TextView) convertView;
        }
        String mStr = mText[position];
        //Log.d("TAG", "mStr = " + mStr);
        mTextView.setText(mText[position]);
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setBackgroundResource(R.drawable.textview_shape);
        return mTextView;
        /*if (convertView == null) {
            mBtnView = new Button(mContext);
            mBtnView.setLayoutParams(new GridView.LayoutParams(85, 85));
        } else {
            mBtnView = (Button) convertView;
        }
        String mStr = mText[position];
        //Log.d("TAG", "mStr = " + mStr);
        mBtnView.setText(mText[position]);
        mBtnView.setGravity(Gravity.CENTER);
        return mBtnView;*/


    }

}