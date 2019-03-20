package com.example.test.paras.view.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.test.paras.R;
import com.example.test.paras.databinding.ActivityHomeBinding;
import com.example.test.paras.service.model.BalanceResponse;
import com.example.test.paras.view.callback.ClickHomeCallBack;
import com.example.test.paras.viewmodel.BalanceViewModel;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class HomeActivity extends AppCompatActivity implements HasSupportFragmentInjector, ClickHomeCallBack {
    private ActivityHomeBinding binding;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        setSupportActionBar(binding.toolbar);
        binding.setEventListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        binding.setIsLoading(true);
        final BalanceViewModel viewModel = ViewModelProviders.of(this,
                viewModelFactory).get(BalanceViewModel.class);
        observeViewModel(viewModel);
    }

    private void observeViewModel(BalanceViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getBalance().observe(this, new Observer<BalanceResponse>() {
            @Override
            public void onChanged(@Nullable BalanceResponse login) {
                binding.setIsLoading(false);
                if (login != null) {
                    binding.txtAmount.setText("Your Balance :-"+login.getBalance()+" "+login.getCurrency());
                }
            }
        });
    }
    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public void onAddTransactionClick(View v) {
        startActivity(new Intent(this,AddTransactionActivity.class));

    }

    @Override
    public void onViewTransactionClick(View v) {
        startActivity(new Intent(this,TransactionListActivity.class));

    }
}
