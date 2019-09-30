package ru.gdgkazan.githubmvp.content;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * @author Artur Vasilov
 */
public class Commit extends RealmObject {

    @SerializedName("commit")
    private CommitWhole mCommit;

    private String mRepoName;

    @SerializedName("author")
    private Author mAuthor;

    public Commit() {
    }

    public Commit(CommitWhole mCommit, String mRepoName, Author mAuthor) {
        this.mCommit = mCommit;
        this.mRepoName = mRepoName;
        this.mAuthor = mAuthor;
    }

    @NonNull
    public CommitWhole getCommit() {
        return mCommit;
    }

    public void setCommit(@NonNull CommitWhole message) {
        mCommit = message;
    }

    @NonNull
    public String getRepoName() {
        return mRepoName;
    }

    public void setRepoName(@NonNull String repoName) {
        mRepoName = repoName;
    }

    @NonNull
    public Author getAuthor() {
        return mAuthor;
    }

    public void setAuthor(@NonNull Author author) {
        mAuthor = author;
    }

}
