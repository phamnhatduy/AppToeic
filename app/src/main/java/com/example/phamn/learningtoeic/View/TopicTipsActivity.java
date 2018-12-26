package com.example.phamn.learningtoeic.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.phamn.learningtoeic.Adapter.TopicTipsAdapter;
import com.example.phamn.learningtoeic.Model.Tips;
import com.example.phamn.learningtoeic.R;

import java.util.ArrayList;
import java.util.List;

public class TopicTipsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TopicTipsAdapter tipsAdapter;
    List<Tips> listTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_tips);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tips");

        recyclerView = (RecyclerView) findViewById(R.id.recycle_view_tips);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerHorizontal = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerHorizontal);

        listTips = new ArrayList<>();
        listTips.add(new Tips(R.drawable.tips, "Bí quyết chung"));
        listTips.add(new Tips(R.drawable.tips, "Bí quyết riêng cho phần 1"));
        listTips.add(new Tips(R.drawable.tips, "Bí quyết riêng cho phần 2"));
        listTips.add(new Tips(R.drawable.tips, "Bí quyết riêng cho phần 3"));
        listTips.add(new Tips(R.drawable.tips, "Bí quyết riêng cho phần 4"));

        tipsAdapter = new TopicTipsAdapter(this, listTips);
        recyclerView.setAdapter(tipsAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
