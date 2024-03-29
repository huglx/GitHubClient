package ru.gdgkazan.githubmvp.screen.commits;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;
import ru.gdgkazan.githubmvp.R;
import ru.gdgkazan.githubmvp.content.Commit;
import ru.gdgkazan.githubmvp.content.Repository;
import ru.gdgkazan.githubmvp.screen.general.LoadingDialog;
import ru.gdgkazan.githubmvp.screen.general.LoadingView;
import ru.gdgkazan.githubmvp.screen.repositories.RepositoriesAdapter;
import ru.gdgkazan.githubmvp.screen.repositories.RepositoriesPresenter;
import ru.gdgkazan.githubmvp.widget.DividerItemDecoration;
import ru.gdgkazan.githubmvp.widget.EmptyRecyclerView;

/**
 * @author Artur Vasilov
 */
public class CommitsActivity extends AppCompatActivity implements CommitsView, SwipeRefreshLayout.OnRefreshListener{

    private static final String REPO_NAME_KEY = "repo_name_key";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recyclerView)
    EmptyRecyclerView mRecyclerView;

    @BindView(R.id.empty)
    View mEmptyView;

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private LoadingView mLoadingView;

    private CommitsPresenter mPresenter;

    private CommitsAdapter mAdapter;

    private String repositoryName;

    public static void start(@NonNull Activity activity, @NonNull Repository repository) {
        Intent intent = new Intent(activity, CommitsActivity.class);
        intent.putExtra(REPO_NAME_KEY, repository.getName());
       // intent.putExtra(REPO_NAME_KEY, user);

        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commits);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mLoadingView = LoadingDialog.view(getSupportFragmentManager());

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(
                Color.BLACK, Color.GREEN, Color.BLUE, Color.CYAN);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));
        mRecyclerView.setEmptyView(mEmptyView);

        mAdapter = new CommitsAdapter(new ArrayList<>());
        mAdapter.attachToRecyclerView(mRecyclerView);

        repositoryName = getIntent().getStringExtra(REPO_NAME_KEY);
        //Snackbar.make(mRecyclerView, "Not implemented for " + repositoryName + " yet", Snackbar.LENGTH_LONG).show();
        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        mPresenter = new CommitsPresenter(lifecycleHandler, this);
        mPresenter.init(repositoryName);

    }

    @Override
    public void showCommits(@NonNull List<Commit> commits) {
        Log.i("showCommits", commits.size()+"");

        mAdapter.changeDataSet(commits);
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {
        mLoadingView.showLoading();
    }

    @Override
    public void hideLoading() {
        mLoadingView.hideLoading();

    }

    @Override
    public void onRefresh() {
        mPresenter.refresh(repositoryName);
    }
}
