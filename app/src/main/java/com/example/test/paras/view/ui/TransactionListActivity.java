package com.example.test.paras.view.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.test.paras.R;
import com.example.test.paras.databinding.ActivityTransactionListBinding;
import com.example.test.paras.service.model.TransactionResponse;
import com.example.test.paras.view.adapter.TransactionListAdapter;
import com.example.test.paras.viewmodel.TransactionListViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class TransactionListActivity extends AppCompatActivity implements HasSupportFragmentInjector {
    private ActivityTransactionListBinding binding;
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transaction_list);
        setSupportActionBar(binding.toolbar);
        binding.setIsLoading(true);
        final TransactionListViewModel viewModel = ViewModelProviders.of(this,
                viewModelFactory).get(TransactionListViewModel.class);
        observeViewModel(viewModel);
    }

    private void observeViewModel(TransactionListViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getTransactionObservable().observe(this, new Observer<List<TransactionResponse>>(){
            @Override
            public void onChanged(@Nullable List<TransactionResponse> transactionResponses) {
                binding.setIsLoading(false);
                if(transactionResponses!=null && !transactionResponses.isEmpty())
                    binding.transactioList.setAdapter(new TransactionListAdapter(transactionResponses));
            }
        });
    }
    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
