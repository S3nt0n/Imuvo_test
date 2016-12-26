package com.example.sco.imuvo.HelperClasses;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Path;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
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

    public static Spannable colorsString(Context context, String s, int color1, int color2){
        SpannableStringBuilder builder = new SpannableStringBuilder();
        String[] splited = s.split("\\s+");
        Spannable s1 = new SpannableString(splited[0]);
        s1.setSpan(new ForegroundColorSpan(color1), 0, s1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        Spannable s2 = new SpannableString(splited[1]);
        s2.setSpan(new ForegroundColorSpan(color2), 0, s2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        Spannable result = builder.append(s1).append(" ").append(s2);
        return result;
    }

    public static Spannable colorsString(String s, int color1, int color2, int color3){
        SpannableStringBuilder builder = new SpannableStringBuilder();
        String[] splited = s.split("\\s+");
        Spannable s1 = new SpannableString(splited[0]);
        s1.setSpan(new ForegroundColorSpan(color1), 0, s1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        Spannable s2 = new SpannableString(splited[1]);
        s2.setSpan(new ForegroundColorSpan(color2), 0, s2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        Spannable s3 = new SpannableString(splited[2]);
        s3.setSpan(new ForegroundColorSpan(color3), 0, s3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        Spannable result = builder.append(s1).append(" ").append(s2).append(" ").append(s3);
        return result;
    }

}
