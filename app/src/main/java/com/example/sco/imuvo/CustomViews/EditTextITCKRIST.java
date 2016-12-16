package com.example.sco.imuvo.CustomViews;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by sco on 14.12.2016.
 */

public class EditTextITCKRIST extends EditText {
    public EditTextITCKRIST(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public EditTextITCKRIST(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public EditTextITCKRIST(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    public EditTextITCKRIST(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = Typeface.createFromAsset(context.getAssets(),"fonts/ITCKRIST.TTF");
        setTypeface(customFont);

    }
}
