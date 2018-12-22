package com.example.phamn.learningtoeic.Adapter;

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


    public TitleAdapter(@NonNull Context context, int resource, @NonNull List<TitleOnPhone> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listTitle = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_title_listview, parent, false);
            viewHolder.tvTitleName = (TextView) convertView.findViewById(R.id.tv_TitleName);
//            viewHolder.tvTime1 = (TextView) convertView.findViewById(R.id.tv_time_1);
//            viewHolder.tvTime2 = (TextView) convertView.findViewById(R.id.tv_time_2);
//            viewHolder.tvTime3 = (TextView) convertView.findViewById(R.id.tv_time_3);
//            viewHolder.tvTime4 = (TextView) convertView.findViewById(R.id.tv_time_4);
//            viewHolder.tvNumberOfQuestions1 = (TextView) convertView.findViewById(R.id.tv_number_1);
//            viewHolder.tvNumberOfQuestions2 = (TextView) convertView.findViewById(R.id.tv_number_2);
//            viewHolder.tvNumberOfQuestions3 = (TextView) convertView.findViewById(R.id.tv_number_3);
//            viewHolder.tvNumberOfQuestions4 = (TextView) convertView.findViewById(R.id.tv_number_4);
            viewHolder.btnPart1 = (Button) convertView.findViewById(R.id.button_part1);
            viewHolder.btnPart2 = (Button) convertView.findViewById(R.id.button_part2);
            viewHolder.btnPart3 = (Button) convertView.findViewById(R.id.button_part3);
            viewHolder.btnPart4 = (Button) convertView.findViewById(R.id.button_part4);

            viewHolder.tvScore1 = (TextView) convertView.findViewById(R.id.tv_score1);

            viewHolder.btnPart1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), Part1Activity.class);
                    intent.putExtra("titleName", "Test" + (position + 1));
                    intent.putExtra("time", "" + listTitle.get(position).getTime1());
                    intent.putExtra("numberOfQuestion", listTitle.get(position).getNumberOfQuestions1());
                    getContext().startActivity(intent);
                }
            });
            viewHolder.btnPart2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), Part2Activity.class);
                    intent.putExtra("titleName", "Test" + (position + 1));
                    intent.putExtra("time", "" + listTitle.get(position).getTime2());
                    intent.putExtra("numberOfQuestion", listTitle.get(position).getNumberOfQuestions2());
                    getContext().startActivity(intent);
                }
            });
            viewHolder.btnPart3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), Part3Activity.class);
                    intent.putExtra("titleName", "Test" + (position + 1));
                    intent.putExtra("time", "" + listTitle.get(position).getTime3());
                    intent.putExtra("numberOfQuestion", listTitle.get(position).getNumberOfQuestions3());
                    getContext().startActivity(intent);
                }
            });
            viewHolder.btnPart4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), Part4Activity.class);
                    intent.putExtra("titleName", "Test" + (position + 1));
                    intent.putExtra("time", "" + listTitle.get(position).getTime4());
                    intent.putExtra("numberOfQuestion", listTitle.get(position).getNumberOfQuestions4());
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
//        viewHolder.tvTime1.setText(title.getTime1());
//        viewHolder.tvTime2.setText(title.getTime2());
//        viewHolder.tvTime3.setText(title.getTime3());
//        viewHolder.tvTime4.setText(title.getTime4());
//        viewHolder.tvNumberOfQuestions1.setText("" + title.getNumberOfQuestions1());
//        viewHolder.tvNumberOfQuestions2.setText("" + title.getNumberOfQuestions2());
//        viewHolder.tvNumberOfQuestions3.setText("" + title.getNumberOfQuestions3());
//        viewHolder.tvNumberOfQuestions4.setText("" + title.getNumberOfQuestions4());

        return convertView;
    }

    public class ViewHolder{
        TextView tvTitleName;
        TextView tvScore1, tvScore2, tvScore3, tvScore4;
        TextView tvDate1, tvDate2, tvDate3, tvDate4;
        TextView tvNumberOfQuestions1, tvNumberOfQuestions2, tvNumberOfQuestions3, tvNumberOfQuestions4;
        Button btnPart1, btnPart2, btnPart3, btnPart4;
    }
}
