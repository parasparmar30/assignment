package com.example.test.paras.service.repository;

import com.example.test.paras.service.model.AddTransactionRequest;
import com.example.test.paras.service.model.BalanceResponse;
import com.example.test.paras.service.model.LoginResponse;
import com.example.test.paras.service.model.TransactionResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface HerokuAppService {
    String HTTPS_API_MOVIEDB_URL = "https://interviewer-api.herokuapp.com";

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("spend")
    Call<ResponseBody> addTransaction(@Body AddTransactionRequest addTransactionRequest);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("balance" )
    Call<BalanceResponse> getBalance(@Header("Authorization") String authHeader);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("transactions" )
    Call<List<TransactionResponse>> getTransaction(@Header("Authorization") String authHeader);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("login" )
    Call<LoginResponse> loginCall();
}
