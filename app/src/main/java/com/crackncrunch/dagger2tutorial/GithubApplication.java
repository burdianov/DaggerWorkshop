package com.crackncrunch.dagger2tutorial;

import android.app.Activity;
import android.app.Application;

import com.crackncrunch.dagger2tutorial.di.components.DaggerGithubApplicationComponent;
import com.crackncrunch.dagger2tutorial.di.components.GithubApplicationComponent;
import com.crackncrunch.dagger2tutorial.di.modules.ContextModule;
import com.crackncrunch.dagger2tutorial.network.GithubService;
import com.squareup.picasso.Picasso;

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
        // PicassoModule
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

        GithubApplicationComponent component =
                DaggerGithubApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

        mGithubService = component.getGithubService();
        mPicasso = component.getPicasso();

    }

    public GithubService getGithubService() {
        return mGithubService;
    }

    public Picasso getPicasso() {
        return mPicasso;
    }
}