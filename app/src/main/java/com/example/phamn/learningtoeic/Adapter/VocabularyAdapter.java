package com.example.phamn.learningtoeic.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phamn.learningtoeic.Model.Vocabulary;
import com.example.phamn.learningtoeic.R;

import java.util.ArrayList;
import java.util.List;

public class VocabularyAdapter extends RecyclerView.Adapter<VocabularyAdapter.ViewHolder>{
    private List<Vocabulary> listVocab = new ArrayList<>();

    public VocabularyAdapter(List<Vocabulary> listVocab) {
        this.listVocab = listVocab;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_word_vocabulary, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvWord.setText((position + 1) + ". " + listVocab.get(position).getWord());
        holder.tvSpelling.setText(listVocab.get(position).getSpelling());
        holder.tvMean.setText(listVocab.get(position).getMean());
        holder.tvExample.setText("Example: " + listVocab.get(position).getExample());
    }

    @Override
    public int getItemCount() {
        return listVocab.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvWord, tvMean, tvSpelling, tvExample;
        public ViewHolder(View itemView) {
            super(itemView);
            tvWord = (TextView) itemView.findViewById(R.id.tv_word_vocab);
            tvSpelling = (TextView) itemView.findViewById(R.id.tv_spelling_vocab);
            tvMean = (TextView) itemView.findViewById(R.id.tv_mean_vocab);
            tvExample = (TextView) itemView.findViewById(R.id.tv_example_vocab);
        }
    }
}