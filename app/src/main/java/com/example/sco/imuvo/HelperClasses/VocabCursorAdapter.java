package com.example.sco.imuvo.HelperClasses;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sco.imuvo.R;

import org.w3c.dom.Text;

/**
 * Created by sco on 17.12.2016.
 */

public class VocabCursorAdapter extends CursorAdapter {
    public VocabCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    public VocabCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_view_vocabs,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView german = (TextView) view.findViewById(R.id.german);
        TextView foreign = (TextView) view.findViewById(R.id.foreign);
        TextView lection = (TextView) view.findViewById(R.id.lektion);

        german.setText(cursor.getString(2));
        foreign.setText(cursor.getString(1));
        lection.setText(Integer.toString(cursor.getInt(3)));

    }
}
