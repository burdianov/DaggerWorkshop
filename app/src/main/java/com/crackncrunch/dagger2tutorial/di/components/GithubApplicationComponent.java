package com.crackncrunch.dagger2tutorial.di.components;

import com.crackncrunch.dagger2tutorial.di.modules.GithubServiceModule;
import com.crackncrunch.dagger2tutorial.di.modules.PicassoModule;
import com.crackncrunch.dagger2tutorial.di.scopes.GithubApplicationScope;
import com.crackncrunch.dagger2tutorial.network.GithubService;
import com.squareup.picasso.Picasso;

import dagger.Component;

/**
 * Created by Lilian on 10-Feb-17.
 */

@GithubApplicationScope
@Component(modules = {GithubServiceModule.class, PicassoModule.class})
public interface GithubApplicationComponent {
    Picasso getPicasso();
    GithubService getGithubService();
}
