package com.example.phamn.learningtoeic.Service;

import com.example.phamn.learningtoeic.Model.QuestionPart1;
import com.example.phamn.learningtoeic.Model.QuestionPart2;
import com.example.phamn.learningtoeic.Model.QuestionPart3;
import com.example.phamn.learningtoeic.Model.QuestionPart4;
import com.example.phamn.learningtoeic.Model.Serial;
import com.example.phamn.learningtoeic.Model.Title;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("SerialJSON.php")
    Call<List<Serial>> getAllSerial();

    @GET("JSON.php")
    Call<List<Title>> getAllTitle();

    @GET("JSON.php")
    Call<List<QuestionPart1>> getAllQuestionPart1();

    @GET("JSON.php")
    Call<List<QuestionPart2>> getAllQuestionPart2();

    @GET("JSON.php")
    Call<List<QuestionPart3>> getAllQuestionPart3();

    @GET("JSON.php")
    Call<List<QuestionPart4>> getAllQuestionPart4();
}
