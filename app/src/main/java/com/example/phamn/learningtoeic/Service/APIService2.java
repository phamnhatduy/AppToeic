package com.example.phamn.learningtoeic.Service;

import com.example.phamn.learningtoeic.Model.QuestionPart1;
import com.example.phamn.learningtoeic.Model.QuestionPart2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService2 {
    @GET("JSON.php")

    Call<List<QuestionPart2>> getAllQuestionPart2();
}
