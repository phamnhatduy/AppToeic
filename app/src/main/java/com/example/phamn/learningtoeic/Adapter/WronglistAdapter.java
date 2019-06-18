package com.example.phamn.learningtoeic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.phamn.learningtoeic.Model.Wronglist;
import com.example.phamn.learningtoeic.R;

import java.util.List;

public class WronglistAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Wronglist> wronglists;

    public WronglistAdapter(Context context, int layout, List<Wronglist> wronglists) {
        this.context = context;
        this.layout = layout;
        this.wronglists = wronglists;
    }

    @Override
    public int getCount() {
        return wronglists.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView=inflater.inflate(layout,null);

        TextView txtEng = convertView.findViewById(R.id.txt_eng);
        TextView txtVn = convertView.findViewById(R.id.txt_viet);

        Wronglist wronglist = wronglists.get(position);
        txtEng.setText(wronglist.getEng());
        txtVn.setText(wronglist.getVn());


        return convertView;
    }
}
