package com.example.sco.imuvo.HelperClasses;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Path;
import android.widget.Toast;

import com.example.sco.imuvo.R;

/**
 * Created by sco on 28.11.2016.
 */

public class Helper {
    public static void makeLongToast(Context context, CharSequence value){
        Toast.makeText(context,value,Toast.LENGTH_LONG).show();

    }
    public static void makeShortToast(Context context, CharSequence value){
        Toast.makeText(context,value,Toast.LENGTH_SHORT).show();

    }

}
