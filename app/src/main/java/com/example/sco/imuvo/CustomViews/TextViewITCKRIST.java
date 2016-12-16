package com.example.sco.imuvo.CustomViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by sco on 13.12.2016.
 */

public class TextViewITCKRIST extends TextView {


    public TextViewITCKRIST(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public TextViewITCKRIST(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public TextViewITCKRIST(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = Typeface.createFromAsset(context.getAssets(),"fonts/ITCKRIST.TTF");
        setTypeface(customFont);
    }



}
