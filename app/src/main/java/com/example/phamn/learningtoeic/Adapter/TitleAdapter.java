package com.example.phamn.learningtoeic.Adapter;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.app.NativeActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phamn.learningtoeic.Model.History;
import com.example.phamn.learningtoeic.Model.TitleOnPhone;
import com.example.phamn.learningtoeic.R;
import com.example.phamn.learningtoeic.Repository.HistoryRepository;
import com.example.phamn.learningtoeic.View.NavigationActivity;
import com.example.phamn.learningtoeic.View.Part1Activity;
import com.example.phamn.learningtoeic.View.Part2Activity;
import com.example.phamn.learningtoeic.View.Part3Activity;
import com.example.phamn.learningtoeic.View.Part4Activity;
import com.example.phamn.learningtoeic.ViewModel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class TitleAdapter extends ArrayAdapter<TitleOnPhone> {
    private Context context;
    private int resource;
    private List<TitleOnPhone> listTitle;
    private Context mContext;

    public TitleAdapter(@NonNull Context context, int resource, @NonNull List<TitleOnPhone> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listTitle = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_title_listview, parent, false);
            viewHolder.tvTitleName = (TextView) convertView.findViewById(R.id.tv_TitleName);

            viewHolder.btnPart1 = (Button) convertView.findViewById(R.id.button_part1);
            viewHolder.btnPart2 = (Button) convertView.findViewById(R.id.button_part2);
            viewHolder.btnPart3 = (Button) convertView.findViewById(R.id.button_part3);
            viewHolder.btnPart4 = (Button) convertView.findViewById(R.id.button_part4);

            viewHolder.tvScore1 = (TextView) convertView.findViewById(R.id.tv_score1);
            viewHolder.tvScore2 = (TextView) convertView.findViewById(R.id.tv_score2);
            viewHolder.tvScore3 = (TextView) convertView.findViewById(R.id.tv_score3);
            viewHolder.tvScore4 = (TextView) convertView.findViewById(R.id.tv_score4);

            viewHolder.tvDate1 = (TextView) convertView.findViewById(R.id.tv_date_time1);
            viewHolder.tvDate2 = (TextView) convertView.findViewById(R.id.tv_date_time2);
            viewHolder.tvDate3 = (TextView) convertView.findViewById(R.id.tv_date_time3);
            viewHolder.tvDate4 = (TextView) convertView.findViewById(R.id.tv_date_time4);
            if(listTitle.get(position).getPart1Audio() == null || listTitle.get(position).getPart1ID() == 0){
                viewHolder.btnPart1.setVisibility(View.INVISIBLE);
                viewHolder.tvScore1.setVisibility(View.INVISIBLE);
                viewHolder.tvDate1.setVisibility(View.INVISIBLE);
            }
            if(listTitle.get(position).getPart2Audio() == null || listTitle.get(position).getPart2ID() == 0){
                viewHolder.btnPart2.setVisibility(View.INVISIBLE);
                viewHolder.tvScore2.setVisibility(View.INVISIBLE);
                viewHolder.tvDate2.setVisibility(View.INVISIBLE);
            }
            if(listTitle.get(position).getPart3Audio() == null || listTitle.get(position).getPart3ID() == 0){
                viewHolder.btnPart3.setVisibility(View.INVISIBLE);
                viewHolder.tvScore3.setVisibility(View.INVISIBLE);
                viewHolder.tvDate3.setVisibility(View.INVISIBLE);
            }
            if(listTitle.get(position).getPart4Audio() == null || listTitle.get(position).getPart4ID() == 0){
                viewHolder.btnPart4.setVisibility(View.INVISIBLE);
                viewHolder.tvScore4.setVisibility(View.INVISIBLE);
                viewHolder.tvDate4.setVisibility(View.INVISIBLE);
            }
            viewHolder.btnPart1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), Part1Activity.class);
                    intent.putExtra("serialName", listTitle.get(position).getSerialName());
                    intent.putExtra("titleName", listTitle.get(position).getTitleName());
                    intent.putExtra("time", "" + listTitle.get(position).getTime1());
                    intent.putExtra("numberOfQuestion", listTitle.get(position).getNumberOfQuestions1());
                    intent.putExtra("partID", listTitle.get(position).getPart1ID());
                    intent.putExtra("audio", listTitle.get(position).getPart1Audio());
//                    getContext().startActivity(intent);
                    ((Activity)parent.getContext()).startActivityForResult(intent, 99);
                    //((Activity)context).startActivityForResult();

                    //Toast.makeText(context, "" + listTitle.get(position).getPart1Audio(), Toast.LENGTH_SHORT).show();
                }
            });
            viewHolder.btnPart2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), Part2Activity.class);
                    intent.putExtra("serialName", listTitle.get(position).getSerialName());
                    intent.putExtra("titleName", listTitle.get(position).getTitleName());
                    intent.putExtra("time", "" + listTitle.get(position).getTime2());
                    intent.putExtra("numberOfQuestion", listTitle.get(position).getNumberOfQuestions2());
                    intent.putExtra("serialID", listTitle.get(position).getSerialID());
                    intent.putExtra("partID", listTitle.get(position).getPart2ID());
                    intent.putExtra("audio", listTitle.get(position).getPart2Audio());
                    getContext().startActivity(intent);
//                    Toast.makeText(context, "" + listTitle.get(position).getPart2Audio(), Toast.LENGTH_SHORT).show();
                }
            });
            viewHolder.btnPart3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), Part3Activity.class);
                    intent.putExtra("serialName", listTitle.get(position).getSerialName());
                    intent.putExtra("titleName", listTitle.get(position).getTitleName());
                    intent.putExtra("time", "" + listTitle.get(position).getTime3());
                    intent.putExtra("numberOfQuestion", listTitle.get(position).getNumberOfQuestions3());
                    intent.putExtra("serialID", "" + listTitle.get(position).getSerialID());
                    intent.putExtra("partID", listTitle.get(position).getPart3ID());
                    intent.putExtra("audio", listTitle.get(position).getPart3Audio());
                    getContext().startActivity(intent);
                }
            });
            viewHolder.btnPart4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), Part4Activity.class);
                    intent.putExtra("serialName", listTitle.get(position).getSerialName());
                    intent.putExtra("titleName", listTitle.get(position).getTitleName());
                    intent.putExtra("time", "" + listTitle.get(position).getTime4());
                    intent.putExtra("numberOfQuestion", listTitle.get(position).getNumberOfQuestions4());
                    intent.putExtra("serialID", listTitle.get(position).getSerialID());
                    intent.putExtra("partID", listTitle.get(position).getPart4ID());
                    intent.putExtra("audio", listTitle.get(position).getPart4Audio());
                    getContext().startActivity(intent);
                }
            });

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        TitleOnPhone title = listTitle.get(position);

        viewHolder.tvTitleName.setText(title.getTitleName());

        viewHolder.tvScore1.setText(title.getHistory1().getScore());
        viewHolder.tvDate1.setText(title.getHistory1().getDate());

        viewHolder.tvScore2.setText(title.getHistory2().getScore());
        viewHolder.tvDate2.setText(title.getHistory2().getDate());

        viewHolder.tvScore3.setText(title.getHistory3().getScore());
        viewHolder.tvDate3.setText(title.getHistory3().getDate());

        viewHolder.tvScore4.setText(title.getHistory4().getScore());
        viewHolder.tvDate4.setText(title.getHistory4().getDate());

//        viewHolder.tvTime1.setText(title.getTime1());
//        viewHolder.tvTime2.setText(title.getTime2());
//        viewHolder.tvTime3.setText(title.getTime3());
//        viewHolder.tvTime4.setText(title.getTime4());
//        viewHolder.tvNumberOfQuestions1.setText("" + title.getNumberOfQuestions1());
//        viewHolder.tvNumberOfQuestions2.setText("" + title.getNumberOfQuestions2());
//        viewHolder.tvNumberOfQuestions3.setText("" + title.getNumberOfQuestions3());
//        viewHolder.tvNumberOfQuestions4.setText("" + title.getNumberOfQuestions4());
        //viewHolder.tvScore1.setText(title.getScore1());
        return convertView;
    }

    public class ViewHolder{
        TextView tvTitleName;
        TextView tvScore1, tvScore2, tvScore3, tvScore4;
        TextView tvDate1, tvDate2, tvDate3, tvDate4;
        Button btnPart1, btnPart2, btnPart3, btnPart4;
    }
}
