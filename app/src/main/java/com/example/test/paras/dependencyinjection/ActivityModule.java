package com.example.test.paras.dependencyinjection;

import com.example.test.paras.view.ui.AddTransactionActivity;
import com.example.test.paras.view.ui.HomeActivity;
import com.example.test.paras.view.ui.TransactionListActivity;
import com.example.test.paras.view.ui.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector()
    abstract TransactionListActivity contributeTransactionListActivity();
    @ContributesAndroidInjector()
    abstract AddTransactionActivity contributeAddTransactionActivity();
    @ContributesAndroidInjector()
    abstract HomeActivity contributeHomeActivity();
    @ContributesAndroidInjector()
    abstract SplashActivity contributeSplashActivity();
}
