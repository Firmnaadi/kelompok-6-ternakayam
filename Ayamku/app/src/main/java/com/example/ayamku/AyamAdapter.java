package com.example.ayamku;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class AyamAdapter extends ArrayAdapter<Ayam> {

    private Context context;
    private List<Ayam> ayamList;

    public AyamAdapter(Context context, List<Ayam> ayamList) {
        super(context, 0, ayamList);
        this.context = context;
        this.ayamList = ayamList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.list_item_ayam, parent, false);
        }

        Ayam currentAyam = ayamList.get(position);

        TextView tvIdAyam = listItemView.findViewById(R.id.tvIdAyam);
        tvIdAyam.setText(currentAyam.getIdAyam());

        TextView tvJumlahAyam = listItemView.findViewById(R.id.tvJumlahAyam);
        tvJumlahAyam.setText(currentAyam.getJumlahAyam());

        return listItemView;
    }
}
