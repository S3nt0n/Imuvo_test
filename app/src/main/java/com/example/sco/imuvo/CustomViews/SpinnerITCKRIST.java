package com.example.sco.imuvo.CustomViews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Spinner;

/**
 * Created by sco on 14.12.2016.
 */

public class SpinnerITCKRIST extends Spinner {
    public SpinnerITCKRIST(Context context) {
        super(context);
    }

    public SpinnerITCKRIST(Context context, int mode) {
        super(context, mode);
    }

    public SpinnerITCKRIST(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SpinnerITCKRIST(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SpinnerITCKRIST(Context context, AttributeSet attrs, int defStyleAttr, int mode) {
        super(context, attrs, defStyleAttr, mode);
    }

    public SpinnerITCKRIST(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, int mode) {
        super(context, attrs, defStyleAttr, defStyleRes, mode);
    }

    public SpinnerITCKRIST(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, int mode, Resources.Theme popupTheme) {
        super(context, attrs, defStyleAttr, defStyleRes, mode, popupTheme);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = Typeface.createFromAsset(context.getAssets(),"fonts/ITCKRIST.TTF");

    }
}
