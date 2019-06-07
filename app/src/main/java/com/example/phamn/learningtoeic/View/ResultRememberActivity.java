package com.example.phamn.learningtoeic.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.phamn.learningtoeic.R;

import java.util.ArrayList;

public class ResultRememberActivity extends AppCompatActivity {
    Button btnAgain;
    TextView txtShow;
    GridView gridView;
    ArrayList<String> arrayList =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Vocabulary Quizzes");
        setContentView(R.layout.activity_result_remember);
        btnAgain = findViewById(R.id.btn_practiceAgain);
        gridView=findViewById(R.id.grid_wronglist);
        //Intent intent = this.getIntent();
        //arrayList = getIntent().getStringArrayListExtra("wrongWord");
        //arrayList.add(intent.getStringExtra("wrongWord"));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,arrayList);
        gridView.setAdapter(arrayAdapter);


        btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultRememberActivity.this,RememberActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
               this.finish();
                //return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
