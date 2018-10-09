package com.example.phamn.learningtoeic.JSON;

import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import com.example.phamn.learningtoeic.Model.Question_Part1;
import com.example.phamn.learningtoeic.View.TestActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class JSON extends AsyncTask<String, Integer, String>{

    public MutableLiveData<List<Question_Part1>> listQuestion = new MutableLiveData<>();
    public List<Question_Part1> list = new ArrayList<>();

    @Override
    protected String doInBackground(String... strings) {
        return docNoiDung_Tu_URL(strings[0]);
    }
    @Override
    protected void onPostExecute(String s) {    // s là kết quả doInBackground trả về
        //Toast.makeText(TestActivity.this, s, Toast.LENGTH_SHORT).show();
        try {
            Question_Part1 q = new Question_Part1();
            JSONArray array = new JSONArray(s);
            for(int i = 0; i < array.length(); i++){
                JSONObject jsonObject = array.getJSONObject(i);
                q.setQuestionNumber(jsonObject.getInt("number"));
                q.setImage(jsonObject.getInt("image"));
                q.setAnswerA(jsonObject.getString("contentAnswerA"));
                q.setAnswerB(jsonObject.getString("contentAnswerB"));
                q.setAnswerC(jsonObject.getString("contentAnswerC"));
                q.setAnswerD(jsonObject.getString("contentAnswerD"));
                q.setCorrectAnswer((Character) jsonObject.get("correctAnswer"));
                list.add(q);
            }
            listQuestion.setValue(list);

        } catch (JSONException e) {
            e.printStackTrace();
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

    public MutableLiveData<List<Question_Part1>> getListQuestion() {
        return listQuestion;
    }

    public List<Question_Part1> getList() {
        return list;
    }
}
