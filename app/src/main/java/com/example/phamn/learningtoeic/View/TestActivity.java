package com.example.phamn.learningtoeic.View;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.phamn.learningtoeic.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    ListView listViewTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        listViewTest = (ListView) findViewById(R.id.listviewTest);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new docJson().execute("https://myhost2018.000webhostapp.com/Test1/Part1/json.php");
            }
        });
    }

    class docJson extends AsyncTask<String, Integer, String>{
        @Override
        protected String doInBackground(String... strings) {    // đọc dữ liệu từ link
            return docNoiDung_Tu_URL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {    // s là kết quả doInBackground trả về
            //Toast.makeText(TestActivity.this, s, Toast.LENGTH_SHORT).show();
            try {
                ArrayList<String> arrName = new ArrayList<String>();
                JSONArray array = new JSONArray(s);
                for(int i = 0; i < array.length(); i++){
                    JSONObject jsonObject = array.getJSONObject(i);
                    if(i == 0){
                        arrName.add("Question " + jsonObject.getString("number") + ":");
                    }
                    if( (i > 0) && ((i) % 4 == 0)){
                        arrName.add("Question " + jsonObject.getString("number") + ":");
                    }
                    arrName.add(jsonObject.getString("answer") + ". "+ jsonObject.getString("contentAnswer"));  // cột name
                }
                ArrayAdapter adapter = new ArrayAdapter(TestActivity.this, android.R.layout.simple_list_item_1, arrName);
                listViewTest.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private String docNoiDung_Tu_URL(String theUrl){
        StringBuilder content = new StringBuilder();
        try    {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)    {
            e.printStackTrace();
        }
        return content.toString();
    }

}
