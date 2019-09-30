package ru.gdgkazan.githubmvp.content;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class CommitWhole extends RealmObject {
    @SerializedName("message")
    private String mCommitMessage;

    @SerializedName("author")
    private Author mAuthor;

    @NonNull
    public Author getAuthor() {
        return mAuthor;
    }

    public void setAuthor(@NonNull Author author) {
        mAuthor = author;
    }


    public String getmCommitMessage() {
        return mCommitMessage;
    }

    public void setmCommitMessage(String mCommitMessage) {
        this.mCommitMessage = mCommitMessage;
    }
}
