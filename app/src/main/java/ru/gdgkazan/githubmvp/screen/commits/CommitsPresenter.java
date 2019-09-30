package ru.gdgkazan.githubmvp.screen.commits;

import android.support.annotation.NonNull;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.gdgkazan.githubmvp.R;
import ru.gdgkazan.githubmvp.content.Repository;
import ru.gdgkazan.githubmvp.repository.RepositoryProvider;
import ru.gdgkazan.githubmvp.screen.repositories.RepositoriesView;

/**
 * @author Artur Vasilov
 */
public class CommitsPresenter {

    private final LifecycleHandler mLifecycleHandler;
    private final CommitsView mView;

    public CommitsPresenter(@NonNull LifecycleHandler lifecycleHandler,
                            @NonNull CommitsView view) {
        mLifecycleHandler = lifecycleHandler;
        mView = view;
    }

    public void init(String repoName) {
        RepositoryProvider.provideGithubRepository()
                .commits("steel4132", repoName)
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .compose(mLifecycleHandler.load(R.id.repositories_request))
                .subscribe(mView::showCommits, throwable -> mView.showError());
    }

    public void refresh(String repoName) {
        RepositoryProvider.provideGithubRepository()
                .commits("steel4132", repoName)
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .compose(mLifecycleHandler.reload(R.id.repositories_request))
                .subscribe(mView::showCommits, throwable -> mView.showError());
    }
}
