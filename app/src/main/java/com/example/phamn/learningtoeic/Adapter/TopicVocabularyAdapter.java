package com.example.phamn.learningtoeic.Adapter;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phamn.learningtoeic.Model.TopicVocabulary;
import com.example.phamn.learningtoeic.Model.Vocabulary;
import com.example.phamn.learningtoeic.R;
import com.example.phamn.learningtoeic.View.VocabularyActivity;

import java.util.ArrayList;
import java.util.List;

public class TopicVocabularyAdapter extends RecyclerView.Adapter<TopicVocabularyAdapter.ViewHolder>{
    private List<TopicVocabulary> listTopic = new ArrayList<>();
    Context context;
    public TopicVocabularyAdapter(Context context, List<TopicVocabulary> listTopic) {
        this.listTopic = listTopic;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_topic_vocabulary, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvTopic.setText(listTopic.get(position).getTopic());
        holder.image.setImageResource(listTopic.get(position).getImageID());
        holder.listTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VocabularyActivity.class);
                intent.putExtra("topic", "" + listTopic.get(position).getTopic());
                context.startActivity(intent);
            }
        });
        /*
        holder.tvTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VocabularyActivity.class);
                intent.putExtra("topic", "" + listTopic.get(position).getTopic());
                context.startActivity(intent);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return listTopic.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTopic;
        ImageView image;
        LinearLayout listTop;
        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.iv_topic_vocab);
            tvTopic = (TextView) itemView.findViewById(R.id.tv_topic_vocab);
            listTop=itemView.findViewById(R.id.list_top);

        }
    }
}
