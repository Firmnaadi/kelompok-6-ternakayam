package com.example.ayamku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class EggAdapter extends ArrayAdapter<Egg> {

    private Context context;
    private List<Egg> eggList;

    public EggAdapter(Context context, List<Egg> eggList) {
        super(context, 0, eggList);
        this.context = context;
        this.eggList = eggList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false);
        }

        Egg currentEgg = eggList.get(position);

        TextView textViewGrade = itemView.findViewById(R.id.textViewGrade);
        TextView textViewWeight = itemView.findViewById(R.id.textViewWeight);

        textViewGrade.setText(currentEgg.getGrade());
        textViewWeight.setText(currentEgg.getBeratTelur());

        return itemView;
    }
}
