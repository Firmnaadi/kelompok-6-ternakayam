package com.example.ayamku;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class KebutuhanAdapter extends BaseAdapter {
    private Context context;
    private List<Kebutuhan> kebutuhanList;

    public KebutuhanAdapter(Context context, List<Kebutuhan> kebutuhanList) {
        this.context = context;
        this.kebutuhanList = kebutuhanList;
    }

    @Override
    public int getCount() {
        return kebutuhanList.size();
    }

    @Override
    public Object getItem(int position) {
        return kebutuhanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item_kebutuhan, parent, false);
        }

        TextView textViewIdKebutuhan = convertView.findViewById(R.id.textViewIdKebutuhan);
        TextView textViewNamaKebutuhan = convertView.findViewById(R.id.textViewNamaKebutuhan);
        TextView textViewJumlahKebutuhan = convertView.findViewById(R.id.textViewJumlahKebutuhan);

        Kebutuhan kebutuhan = kebutuhanList.get(position);

        textViewIdKebutuhan.setText(kebutuhan.getIdKebutuhan());
        textViewNamaKebutuhan.setText(kebutuhan.getNamaKebutuhan());
        textViewJumlahKebutuhan.setText(kebutuhan.getJumlahKebutuhan());

        return convertView;
    }
}
