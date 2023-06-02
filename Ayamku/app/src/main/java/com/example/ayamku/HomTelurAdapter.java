package com.example.ayamku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class HomTelurAdapter extends ArrayAdapter<HomTelur> {

    public HomTelurAdapter(Context context, List<HomTelur> telurList) {
        super(context, 0, telurList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout, parent, false);
        }

        HomTelur telur = getItem(position);

        TextView titleTextView = convertView.findViewById(R.id.text_view_item);
        titleTextView.setText(telur.getTitle() + ": " + telur.getValue());

        return convertView;
    }
}
