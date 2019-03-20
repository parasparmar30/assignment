package com.example.test.paras.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.test.paras.service.model.TransactionResponse;
import com.example.test.paras.service.repository.HerokuAppRepository;
import com.example.test.paras.util.Constant;
import com.example.test.paras.util.UtilityMethods;

import java.util.List;

import javax.inject.Inject;

public class TransactionListViewModel extends AndroidViewModel {
    private LiveData<List<TransactionResponse>> transactionObservable = new MutableLiveData<>();
    @Inject
    public TransactionListViewModel(@NonNull HerokuAppRepository movieRepository, @NonNull Application application) {
        super(application);
        String token  = UtilityMethods.getStringInPref(application.getApplicationContext(), Constant.PERF_TOKEN_KEY,"");
        transactionObservable = movieRepository.getTransaction("Bearer "+token);

    }
    public LiveData<List<TransactionResponse>> getTransactionObservable() {
        return transactionObservable;
    }
}
