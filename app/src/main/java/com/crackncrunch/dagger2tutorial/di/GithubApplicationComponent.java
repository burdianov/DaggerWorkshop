package com.crackncrunch.dagger2tutorial.di;

import com.crackncrunch.dagger2tutorial.network.GithubService;
import com.squareup.picasso.Picasso;

import dagger.Component;

/**
 * Created by Lilian on 10-Feb-17.
 */

@Component
public interface GithubApplicationComponent {
    Picasso getPicasso();
    GithubService getGithubService();
}
