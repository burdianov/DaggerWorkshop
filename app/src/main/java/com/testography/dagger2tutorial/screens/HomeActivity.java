package com.testography.dagger2tutorial.screens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.testography.dagger2tutorial.GithubApplication;
import com.testography.dagger2tutorial.R;
import com.testography.dagger2tutorial.models.GithubRepo;
import com.testography.dagger2tutorial.network.GithubService;
import com.testography.dagger2tutorial.screens.home.AdapterRepos;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.repo_home_list)
    ListView listView;

    GithubService mGithubService;
    Picasso mPicasso;
    Call<List<GithubRepo>> mReposCall;
    AdapterRepos mAdapterRepos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mGithubService = GithubApplication.get(this).getGithubService();
        mPicasso = GithubApplication.get(this).getPicasso();

        mAdapterRepos = new AdapterRepos(this, mPicasso);
        listView.setAdapter(mAdapterRepos);

        mReposCall = mGithubService.getAllRepos();
        mReposCall.enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
                mAdapterRepos.swapData(response.body());
            }

            @Override
            public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Error getting repos " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReposCall != null) {
            mReposCall.cancel();
        }
    }
}
