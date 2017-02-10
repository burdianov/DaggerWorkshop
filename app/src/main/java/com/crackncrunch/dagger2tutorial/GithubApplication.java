package com.crackncrunch.dagger2tutorial;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.crackncrunch.dagger2tutorial.managers.ConstantsManager;
import com.crackncrunch.dagger2tutorial.network.DateTimeConverter;
import com.crackncrunch.dagger2tutorial.network.GithubService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class GithubApplication extends Application {

    public static GithubApplication get(Activity activity) {
        return (GithubApplication) activity.getApplication();
    }

    private GithubService mGithubService;
    private Picasso mPicasso;

    // Activity:
        // GithubService
        // Retrofit
            // Gson
        // Picasso
            // OkHttp3Downloader
            // OkHttp
                // logger
                    // timber
                // cache
                    // file

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        //region ==================== Context ===================

        Context context = this;

        //endregion

        //region ==================== Network ===================

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor
                (new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Timber.i(message);
                    }
                });

        File cacheFile = new File(context.getCacheDir(), "okhtt_cache");
        cacheFile.mkdirs();

        Cache cache = new Cache(cacheFile, 10 * 1000 * 1000); // 10MB Cache

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build();

        //endregion

        //region ==================== Picasso ===================

        mPicasso = new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(okHttpClient))
                .build();

        //endregion

        //region ==================== Client ===================

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeConverter());
        Gson gson = gsonBuilder.create();

        Retrofit gitHubRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl(ConstantsManager.BASE_URL)
                .build();

        mGithubService = gitHubRetrofit.create(GithubService.class);

        //endregion
    }

    public GithubService getGithubService() {
        return mGithubService;
    }

    public Picasso getPicasso() {
        return mPicasso;
    }
}