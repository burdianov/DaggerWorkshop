package com.crackncrunch.dagger2tutorial.screens.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.crackncrunch.dagger2tutorial.models.GithubRepo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdapterRepos extends BaseAdapter {

  private final List<GithubRepo> repoList = new ArrayList<>(0);
  private final Context mContext;
  private final Picasso mPicasso;

  public AdapterRepos(Context context, Picasso picasso) {
    mContext = context;
    mPicasso = picasso;
  }

  @Override
  public int getCount() {
    return repoList.size();
  }

  @Override
  public GithubRepo getItem(int position) {
    return repoList.get(position);
  }

  @Override
  public boolean hasStableIds() {
    return true;
  }

  @Override
  public long getItemId(int position) {
    return repoList.get(position).id;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    RepoListItem repoListItem;
    if(convertView == null) {
      repoListItem = new RepoListItem(mContext, mPicasso);
    } else {
      repoListItem = RepoListItem.class.cast(convertView);
    }

    repoListItem.setRepo(repoList.get(position));

    return repoListItem;
  }

  public void swapData(Collection<GithubRepo> githubRepos) {
    repoList.clear();
    if(githubRepos != null) {
      repoList.addAll(githubRepos);
    }
    notifyDataSetChanged();
  }

}
