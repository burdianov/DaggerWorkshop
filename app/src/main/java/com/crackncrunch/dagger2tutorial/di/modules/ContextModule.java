package com.crackncrunch.dagger2tutorial.di.modules;

import android.content.Context;

import com.crackncrunch.dagger2tutorial.di.scopes.GithubApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lilian on 10-Feb-17.
 */

@Module
public class ContextModule {

    private final Context mContext;

    public ContextModule(Context context) {
        mContext = context;
    }

    @Provides
    @GithubApplicationScope
    public Context context() {
        return mContext;
    }
}
