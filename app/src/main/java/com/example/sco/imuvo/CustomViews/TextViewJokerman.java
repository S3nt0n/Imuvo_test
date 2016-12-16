package com.example.sco.imuvo.CustomViews;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.sco.imuvo.R;

/**
 * Created by sco on 13.12.2016.
 */

public class TextViewJokerman extends TextView {
    public TextViewJokerman(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public TextViewJokerman(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public TextViewJokerman(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }
    private void applyCustomFont(Context context) {
        Typeface customFont = Typeface.createFromAsset(context.getAssets(),"fonts/Jokerman-Regular.ttf");
        setTypeface(customFont);
        setTextSize(20);
        //setTextAppearance();
        //setTextColor(50);
    }



}
