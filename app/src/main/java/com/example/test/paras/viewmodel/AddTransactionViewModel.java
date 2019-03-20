package com.example.test.paras.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.test.paras.service.model.AddTransactionRequest;
import com.example.test.paras.service.repository.HerokuAppRepository;

import javax.inject.Inject;

import okhttp3.ResponseBody;

public class AddTransactionViewModel extends AndroidViewModel {
    private static final String TAG = AddTransactionViewModel.class.getName();
    private static final MutableLiveData ABSENT = new MutableLiveData();
    {
        ABSENT.setValue(null);
    }

    private final LiveData<ResponseBody> responseObservable;
    private final MutableLiveData<AddTransactionRequest> addTransactionMutableLiveData;


    @Inject
    public AddTransactionViewModel(@NonNull HerokuAppRepository movieRepository, @NonNull Application application) {
        super(application);

        this.addTransactionMutableLiveData = new MutableLiveData<>();

        responseObservable = Transformations.switchMap(addTransactionMutableLiveData, input -> {
            if (input==null) {
                Log.i(TAG, "AddTransactionViewModel addTransactionMutableLiveData is absent!!!");
                return ABSENT;
            }

            Log.i(TAG,"AddTransactionViewModel addTransactionMutableLiveData is " + addTransactionMutableLiveData.getValue());

            return movieRepository.addTransaction( addTransactionMutableLiveData.getValue());
        });
    }

    public LiveData<ResponseBody> getObservableProject() {
        return responseObservable;
    }


    public void setAddTransactionRequest(AddTransactionRequest addTransactionRequest) {
        this.addTransactionMutableLiveData.setValue(addTransactionRequest);
    }
}
