package com.example.phamn.learningtoeic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phamn.learningtoeic.Model.Vocabulary;
import com.example.phamn.learningtoeic.R;

import java.util.ArrayList;
import java.util.List;

public class VocabularyAdapter extends RecyclerView.Adapter<VocabularyAdapter.ViewHolder>  {
    private List<Vocabulary> listVocab = new ArrayList<>();
    int row_index = -1;
    private Context mContext;
    public VocabularyAdapter(Context mContext,List<Vocabulary> listVocab) {
        this.listVocab = listVocab;
        this.mContext=mContext;
    }
    public VocabularyAdapter(List<Vocabulary> listVocab) {
        this.listVocab = listVocab;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_word_vocabulary, parent, false);
        // effect
        Animation animation = AnimationUtils.loadAnimation(mContext,R.anim.scale_vocabulary);
        view.startAnimation(animation);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvWord.setText((position + 1) + ". " + listVocab.get(position).getWord());
        holder.tvSpelling.setText(listVocab.get(position).getSpelling());
        holder.tvMean.setText(listVocab.get(position).getMean());
        holder.tvExample.setText("Example: " + listVocab.get(position).getExample());
        //event touch layout
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
                if (onItemClickedListener != null ) {
                   onItemClickedListener.onItemClick(listVocab.get(position).getWord(),position);
                }
            }
        });

        if(row_index==position){
            holder.layout.setBackgroundColor(Color.parseColor("#03A9F4"));
            holder.tvWord.setTextColor(Color.parseColor("#ffffff"));
        }
        else
        {
            holder.layout.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.tvWord.setTextColor(Color.parseColor("#000000"));
        }

    }
    public interface OnItemClickedListener {
        void onItemClick(String pos,int i);

    }

    private OnItemClickedListener onItemClickedListener;

    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }

    @Override
    public int getItemCount() {
        return listVocab.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvWord, tvMean, tvSpelling, tvExample;
        RecyclerView recyclerView;
        LinearLayout layout;
        public ViewHolder(View itemView) {
            super(itemView);
            tvWord = (TextView) itemView.findViewById(R.id.tv_word_vocab);
            tvSpelling = (TextView) itemView.findViewById(R.id.tv_spelling_vocab);
            tvMean = (TextView) itemView.findViewById(R.id.tv_mean_vocab);
            tvExample = (TextView) itemView.findViewById(R.id.tv_example_vocab);
            //init item_world_vocabulary
            layout = itemView.findViewById(R.id.lin_vocabulary);
            recyclerView=itemView.findViewById(R.id.recycle_view_vocabulary);
        }
    }


}