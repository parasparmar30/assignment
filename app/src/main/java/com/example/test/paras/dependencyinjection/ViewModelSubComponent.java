package com.example.test.paras.dependencyinjection;

import com.example.test.paras.viewmodel.AddTransactionViewModel;
import com.example.test.paras.viewmodel.TransactionListViewModel;
import com.example.test.paras.viewmodel.LoginViewModel;
import com.example.test.paras.viewmodel.BalanceViewModel;
import com.example.test.paras.viewmodel.MyViewModelFactory;

import dagger.Subcomponent;

@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    BalanceViewModel balanceViewModel();
    AddTransactionViewModel addViewModel();
    TransactionListViewModel transactionViewModel();
    LoginViewModel loginViewModel();
}
