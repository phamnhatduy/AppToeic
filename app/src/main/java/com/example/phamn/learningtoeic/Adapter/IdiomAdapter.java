package com.example.phamn.learningtoeic.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phamn.learningtoeic.Model.Idiom;
import com.example.phamn.learningtoeic.R;

import java.util.ArrayList;
import java.util.List;

public class IdiomAdapter extends RecyclerView.Adapter<IdiomAdapter.ViewHolder>{
    private List<Idiom> listIdiom = new ArrayList<>();

    public IdiomAdapter(List<Idiom> listIdiom) {
        this.listIdiom = listIdiom;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_idiom, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvPhrase.setText((position + 1) + ". " + listIdiom.get(position).getPhrase() + ": ");
        holder.tvMean.setText(listIdiom.get(position).getMean());
    }

    @Override
    public int getItemCount() {
        return listIdiom.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPhrase, tvMean;
        public ViewHolder(View itemView) {
            super(itemView);
            tvPhrase = (TextView) itemView.findViewById(R.id.tv_phrase);
            tvMean = (TextView) itemView.findViewById(R.id.tv_mean);
        }
    }
}
