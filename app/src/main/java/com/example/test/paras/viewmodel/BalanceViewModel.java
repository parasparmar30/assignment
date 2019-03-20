package com.example.test.paras.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.test.paras.service.model.BalanceResponse;
import com.example.test.paras.service.repository.HerokuAppRepository;
import com.example.test.paras.util.Constant;
import com.example.test.paras.util.UtilityMethods;

import javax.inject.Inject;

public class BalanceViewModel extends AndroidViewModel {
    private LiveData<BalanceResponse> balanceObservable = new MutableLiveData<>();
    @Inject
    public BalanceViewModel(@NonNull HerokuAppRepository HerokuRepository, @NonNull Application application) {
        super(application);
        // If any transformation is needed, this can be simply done by Transformations class ...
        String token  = UtilityMethods.getStringInPref(application.getApplicationContext(), Constant.PERF_TOKEN_KEY,"");
        balanceObservable = HerokuRepository.getBalance("Bearer "+token);

    }
    public LiveData<BalanceResponse> getBalance() {
        return balanceObservable;
    }
}
