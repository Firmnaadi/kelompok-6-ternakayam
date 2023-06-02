package com.example.ayamku;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class JamAdapter extends ArrayAdapter<JamModel> {

    public JamAdapter(Context context, List<JamModel> jamList) {
        super(context, 0, jamList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout, parent, false);
        }

        JamModel jam = getItem(position);

        TextView jamTextView = convertView.findViewById(R.id.text_view_jam);
        TextView tanggalTextView = convertView.findViewById(R.id.text_view_tanggal);

        jamTextView.setText(jam.getJam());

        if (!jam.getTanggal().isEmpty()) {
            tanggalTextView.setText("Tanggal: " + jam.getTanggal());
            tanggalTextView.setVisibility(View.VISIBLE);
        } else {
            tanggalTextView.setVisibility(View.GONE);
        }

        return convertView;
    }
}

