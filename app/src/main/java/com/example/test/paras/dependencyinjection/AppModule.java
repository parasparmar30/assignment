package com.example.test.paras.dependencyinjection;

import android.arch.lifecycle.ViewModelProvider;

import com.example.test.paras.service.repository.HerokuAppService;
import com.example.test.paras.viewmodel.MyViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(subcomponents = ViewModelSubComponent.class)
class AppModule {
    @Singleton @Provides
    HerokuAppService provideMovieDbService() {
        return new Retrofit.Builder()
                .baseUrl(HerokuAppService.HTTPS_API_MOVIEDB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HerokuAppService.class);
    }

    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(
            ViewModelSubComponent.Builder viewModelSubComponent) {

        return new MyViewModelFactory(viewModelSubComponent.build());
    }
}
