package com.example.juanpablo.turitown;

/**
 * Created by Juan Pablo on 04/10/2015.
 */
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class DBCursorAdapter extends CursorAdapter {


    public DBCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor){
        TextView textView=(TextView) view.findViewById(R.id.textView);
        textView.setText(cursor.getString(cursor.getColumnIndex(String.valueOf(0))));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        final LayoutInflater inflater=LayoutInflater.from(context);
        final View view=inflater.inflate(android.R.layout.simple_list_item_1,parent,false);
        return view;
    }


}
