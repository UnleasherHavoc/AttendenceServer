package com.example.application.attendenceserver;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by BAIG1995 on 2/14/2017.
 */

public class custom_curso_adaptor extends CursorAdapter {

    public custom_curso_adaptor(Context context, Cursor c, int flags) {
        super(context, c , flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View custom_cursor=layoutInflater.inflate(R.layout.custom_cursor_adaptor,parent,false);
        return custom_cursor;


    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {



        TextView name=(TextView)view.findViewById(R.id.name);

        name.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));






    }
}
