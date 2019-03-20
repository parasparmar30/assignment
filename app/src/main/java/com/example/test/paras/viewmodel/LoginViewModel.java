package com.example.test.paras.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.test.paras.service.model.LoginResponse;
import com.example.test.paras.service.repository.HerokuAppRepository;

import javax.inject.Inject;

public class LoginViewModel extends AndroidViewModel {

    private LiveData<LoginResponse> loginObservable = new MutableLiveData<>();
    @Inject
    public LoginViewModel(@NonNull HerokuAppRepository movieRepository, @NonNull Application application) {
        super(application);
        // If any transformation is needed, this can be simply done by Transformations class ...
        loginObservable = movieRepository.getLogin();

    }
    public LiveData<LoginResponse> getLoginResponse() {
        return loginObservable;
    }
}
