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
import android.widget.Toast;

import com.example.test.paras.R;
import com.example.test.paras.databinding.ActivitySplashBinding;
import com.example.test.paras.service.model.LoginResponse;
import com.example.test.paras.util.Constant;
import com.example.test.paras.util.UtilityMethods;
import com.example.test.paras.viewmodel.LoginViewModel;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class SplashActivity extends AppCompatActivity implements HasSupportFragmentInjector {
    private ActivitySplashBinding binding;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        final LoginViewModel viewModel = ViewModelProviders.of(this,
                viewModelFactory).get(LoginViewModel.class);
        observeViewModel(viewModel);
    }

    private void observeViewModel(LoginViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getLoginResponse().observe(this, new Observer<LoginResponse>() {
            @Override
            public void onChanged(@Nullable LoginResponse login) {
                if (login != null) {
                    UtilityMethods.saveStringInPref(SplashActivity.this, Constant.PERF_TOKEN_KEY,login.getToken());
                    startActivity(new Intent(SplashActivity.this,HomeActivity.class));
                    finish();
                }
            }
        });
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
