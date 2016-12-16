package com.example.sco.imuvo.CustomViews;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.Button;

import com.example.sco.imuvo.R;

/**
 * Created by sco on 14.12.2016.
 */

public class ButtonJokerman extends Button {
    public ButtonJokerman(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public ButtonJokerman(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public ButtonJokerman(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = Typeface.createFromAsset(context.getAssets(),"fonts/Jokerman-Regular.ttf");
        setTypeface(customFont);

    }
}
