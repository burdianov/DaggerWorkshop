package com.crackncrunch.dagger2tutorial.di.modules;

import android.content.Context;

import com.crackncrunch.dagger2tutorial.di.scopes.GithubApplicationScope;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by Lilian on 10-Feb-17.
 */

@Module(includes = {ContextModule.class, NetworkModule.class})
public class PicassoModule {
    @Provides
    @GithubApplicationScope
    public Picasso picasso(Context context, OkHttp3Downloader okHttp3Downloader) {
        return new Picasso.Builder(context)
                .downloader(okHttp3Downloader)
                .build();
    }

    @Provides
    @GithubApplicationScope
    public OkHttp3Downloader okHttp3Downloader(OkHttpClient okHttpClient) {
        return new OkHttp3Downloader(okHttpClient);
    }
}
