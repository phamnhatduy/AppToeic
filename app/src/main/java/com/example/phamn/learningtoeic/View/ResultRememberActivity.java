package com.example.phamn.learningtoeic.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phamn.learningtoeic.Adapter.WronglistAdapter;
import com.example.phamn.learningtoeic.Model.TopicVocabulary;
import com.example.phamn.learningtoeic.Model.Vocabulary;
import com.example.phamn.learningtoeic.Model.Wronglist;
import com.example.phamn.learningtoeic.R;

import java.util.ArrayList;

public class ResultRememberActivity extends AppCompatActivity {
    Button btnAgain;
    TextView txtNumlist;
    ListView listView;
    ArrayList<String> arrayList1;
    ArrayList<String> arrayList2;
    ArrayList<Wronglist> wronglists;
    WronglistAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Vocabulary Quizzes");
        setContentView(R.layout.activity_result_remember);
        btnAgain = findViewById(R.id.btn_practiceAgain);
        listView=findViewById(R.id.list_wrong);
        txtNumlist=findViewById(R.id.txt_numlist);

        arrayList1 = new ArrayList<>();
        arrayList2=new ArrayList<>();


        arrayList1 = getIntent().getStringArrayListExtra("id");
        arrayList2= getIntent().getStringArrayListExtra("name");

        wronglists=new ArrayList<>();
        for(int i=0;i<arrayList1.size();i++) {
            {
                wronglists.add(new Wronglist(arrayList1.get(i),arrayList2.get(i)));
                //wronglists.add(new Wronglist(arrayList2.get(j)));
            }
        }
        //wronglists.add(new Wronglist(arrayList2.toString()));
        adapter = new WronglistAdapter(this,R.layout.list_wrong,wronglists);
        //arrayList.add("AAAAAAAAAA");
        // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
        // android.R.layout.simple_dropdown_item_1line
        //,arrayList);
        // listView.setAdapter(arrayAdapter);
        listView.setAdapter(adapter);


        txtNumlist.setText("("+wronglists.size()+")");
        if(wronglists.size()==0)
        {
            Toast.makeText(this, "Exellent!You have no wrong answers", Toast.LENGTH_SHORT).show();
        }
        btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent intent=getIntent();
                String top=intent.getStringExtra("topic");
                if(top.equals("Contracts")) {
                    intent = new Intent(ResultRememberActivity.this, TopicVocabulary.class);
                    startActivity(intent);
                }
                else if(top.equals("Marketing"))
                {

                }*/
                Intent intent = new Intent(ResultRememberActivity.this, TopicVocabularyActivity.class);
                startActivity(intent);

            }
        });
    }
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
               this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
        /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }*/
}
