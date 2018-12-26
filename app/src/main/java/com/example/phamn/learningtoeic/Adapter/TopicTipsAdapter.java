package com.example.phamn.learningtoeic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phamn.learningtoeic.Model.Tips;
import com.example.phamn.learningtoeic.R;
import com.example.phamn.learningtoeic.View.TipsActivity;

import java.util.ArrayList;
import java.util.List;

public class TopicTipsAdapter extends RecyclerView.Adapter<TopicTipsAdapter.ViewHolder>{
    private List<Tips> listTips = new ArrayList<>();
    private Context context;

    public TopicTipsAdapter(Context context, List<Tips> listTips) {
        this.listTips = listTips;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_tips, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvTopicTips.setText(listTips.get(position).getTitle());
        holder.ivTips.setImageResource(listTips.get(position).getImageID());
        holder.tvTopicTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TipsActivity.class);
                intent.putExtra("number", position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTips.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTopicTips;
        ImageView ivTips;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTopicTips = (TextView) itemView.findViewById(R.id.tv_topic_tips);
            ivTips = (ImageView) itemView.findViewById(R.id.iv_tips);
        }
    }
}
