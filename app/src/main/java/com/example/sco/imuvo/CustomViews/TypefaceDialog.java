package com.example.sco.imuvo.CustomViews;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListAdapter;

/**
 * Created by sco on 02.01.2017.
 */

public class TypefaceDialog extends DialogFragment {
    private static final CharSequence[] items = {
            "A", "B", "C", "D", "E", "F", "G"
    };
    private static final boolean[] checked = {
            true, false, false, true, true, false, false
    };

    public static TypefaceDialog newInstance(int title) {
        TypefaceDialog frag = new TypefaceDialog();
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Typeface fontTypeface = Typeface.createFromAsset(getActivity().getAssets(),"fonts/ITCKRIST.TTF");
        ListAdapter adapter = new ArrayAdapter<CharSequence>(
                getActivity(),
                android.R.layout.select_dialog_multichoice,
                android.R.id.text1,
                items) {

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                CheckedTextView textView = (CheckedTextView)view.findViewById(android.R.id.text1);

                textView.setChecked(checked[position]);
                textView.setTypeface(fontTypeface);
                textView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        CheckedTextView view = (CheckedTextView)v;
                        view.setChecked(!view.isChecked());
                        checked[position] = view.isChecked();
                    }
                });

                return view;
            }
        };

        return new AlertDialog.Builder(getActivity())
                .setAdapter(adapter, null)
                .setPositiveButton("OK", null)
                .create();
    }

}
