package ru.gdgkazan.githubmvp.screen.commits;

import android.support.annotation.NonNull;
import android.util.Log;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.gdgkazan.githubmvp.R;
import ru.gdgkazan.githubmvp.content.Repository;
import ru.gdgkazan.githubmvp.repository.RepositoryProvider;
import ru.gdgkazan.githubmvp.screen.repositories.RepositoriesView;
import ru.gdgkazan.githubmvp.utils.PreferenceUtils;
import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;

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

    Observable.Transformer<Observable<String>, String> transformIntegerToString() {
        return observable -> observable.map(String::valueOf);
    }

    public void init(String repoName) {
        final String[] user = new String[1];
        PreferenceUtils.getUserName()
                .subscribe(value -> user[0] = value);
        //  Log.i("USER", user);
        RepositoryProvider.provideGithubRepository()
                .commits(user[0], repoName)
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .compose(mLifecycleHandler.load(R.id.repositories_request))
                .subscribe(mView::showCommits, throwable -> mView.showError());
    }

    private String returnUser(String value) {
        return value;
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
