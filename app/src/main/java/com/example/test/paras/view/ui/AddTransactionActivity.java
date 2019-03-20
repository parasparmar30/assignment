package com.example.test.paras.view.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.test.paras.R;
import com.example.test.paras.databinding.ActivityTransactionAddBinding;
import com.example.test.paras.service.model.AddTransactionRequest;
import com.example.test.paras.service.model.TransactionResponse;
import com.example.test.paras.view.adapter.TransactionListAdapter;
import com.example.test.paras.view.callback.ClickCallBack;
import com.example.test.paras.viewmodel.AddTransactionViewModel;
import com.example.test.paras.viewmodel.TransactionListViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import okhttp3.ResponseBody;

public class AddTransactionActivity extends AppCompatActivity implements HasSupportFragmentInjector, ClickCallBack {
    private ActivityTransactionAddBinding binding;
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transaction_add);
        binding.setEventListener(this);
        setSupportActionBar(binding.toolbar);

    }



    private void observeViewModel(AddTransactionViewModel viewModel) {
        viewModel.getObservableProject().observe(this, new Observer<ResponseBody>(){
            @Override
            public void onChanged(@Nullable ResponseBody responseBody) {
                binding.setIsLoading(false);
                finish();
            }
        });
    }
    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    private String getTime(){
        Date date = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String dateFormatted = formatter.format(date);
        return dateFormatted;
    }
    private void apiClick(){
        final AddTransactionViewModel viewModel = ViewModelProviders.of(this,
                viewModelFactory).get(AddTransactionViewModel.class);
        binding.setIsLoading(true);
        AddTransactionRequest addTransactionRequest = new AddTransactionRequest();
        addTransactionRequest.setDate(getTime());
        addTransactionRequest.setAmount(binding.editTxtAddNumber.getText().toString().trim());
        addTransactionRequest.setDescription(binding.editTxtAddNumber.getText().toString().trim());
        addTransactionRequest.setCurrency("GBP");
        viewModel.setAddTransactionRequest(addTransactionRequest);
        observeViewModel(viewModel);
    }
    @Override
    public void onBtnClick(View v) {
        if(validate()){
            apiClick();
        }
    }
    private boolean validate(){
        boolean isValid =true;
        if(TextUtils.isEmpty(binding.editTxtAddNumber.getText().toString().trim())){
            Toast.makeText(this,getString(R.string.add_amount_error),Toast.LENGTH_SHORT).show();
            isValid =false;
        }else if(TextUtils.isEmpty(binding.editTxtComment.getText().toString().trim())){
            Toast.makeText(this,getString(R.string.add_comment_error),Toast.LENGTH_SHORT).show();
            isValid =false;
        }
        return isValid;

    }
}
