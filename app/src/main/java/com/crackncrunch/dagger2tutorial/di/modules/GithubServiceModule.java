package com.crackncrunch.dagger2tutorial.di.modules;

import com.crackncrunch.dagger2tutorial.di.scopes.GithubApplicationScope;
import com.crackncrunch.dagger2tutorial.managers.ConstantsManager;
import com.crackncrunch.dagger2tutorial.network.DateTimeConverter;
import com.crackncrunch.dagger2tutorial.network.GithubService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.joda.time.DateTime;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lilian on 10-Feb-17.
 */

@Module(includes = NetworkModule.class)
public class GithubServiceModule {
    @Provides
    @GithubApplicationScope
    public GithubService githubService(Retrofit gitHubRetrofit) {
        return gitHubRetrofit.create(GithubService.class);
    }

    @Provides
    @GithubApplicationScope
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeConverter());
        return gsonBuilder.create();
    }

    @Provides
    @GithubApplicationScope
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl(ConstantsManager.BASE_URL)
                .build();
    }
}
