package com.example.test.paras.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.test.paras.service.model.AddTransactionRequest;
import com.example.test.paras.service.model.BalanceResponse;
import com.example.test.paras.service.model.LoginResponse;
import com.example.test.paras.service.model.TransactionResponse;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class HerokuAppRepository {
    private HerokuAppService herokuService;

    @Inject
    public HerokuAppRepository(HerokuAppService movieDbService) {
        this.herokuService = movieDbService;
    }


    public LiveData<ResponseBody> addTransaction(AddTransactionRequest addTransactionRequest) {
        final MutableLiveData<ResponseBody> data = new MutableLiveData<>();

        herokuService.addTransaction(addTransactionRequest).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                simulateDelay();
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
    public LiveData<LoginResponse> getLogin() {
        final MutableLiveData<LoginResponse> data = new MutableLiveData<>();

        herokuService.loginCall().enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
    public LiveData<List<TransactionResponse>> getTransaction(String token) {
        final MutableLiveData<List<TransactionResponse>> data = new MutableLiveData<>();

        herokuService.getTransaction(token).enqueue(new Callback<List<TransactionResponse>>() {
            @Override
            public void onResponse(Call<List<TransactionResponse>> call, Response<List<TransactionResponse>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<TransactionResponse>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
    public LiveData<BalanceResponse> getBalance(String token) {
        final MutableLiveData<BalanceResponse> data = new MutableLiveData<>();

        herokuService.getBalance(token).enqueue(new Callback<BalanceResponse>() {
            @Override
            public void onResponse(Call<BalanceResponse> call, Response<BalanceResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<BalanceResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
    private void simulateDelay() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
