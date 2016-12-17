package com.example.sco.imuvo.HelperClasses;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sco.imuvo.Activities.CreateUserActivity;
import com.example.sco.imuvo.Activities.VocabList;
import com.example.sco.imuvo.R;

/**
 * Created by sco on 17.12.2016.
 */

public class LectionCursorAdapter extends CursorAdapter {
    public LectionCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    public LectionCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_view_vocabs,parent,false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        TextView german = (TextView) view.findViewById(R.id.german);
        final TextView foreign = (TextView) view.findViewById(R.id.foreign);
        TextView lection = (TextView) view.findViewById(R.id.lektion);

        final int lectionNo = cursor.getInt(1);

        german.setText("Lektion");
        foreign.setText(Integer.toString(lectionNo));
        lection.setText(cursor.getString(2));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent menuIntent = new Intent(context,VocabList.class);
                Bundle bundle = new Bundle();
                bundle.putInt("lectionNumber",lectionNo);
                menuIntent.putExtras(bundle);
                context.startActivity(menuIntent);
            }
        });

    }
}
