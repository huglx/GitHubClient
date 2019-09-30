package ru.gdgkazan.githubmvp.screen.commits;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gdgkazan.githubmvp.R;
import ru.gdgkazan.githubmvp.content.Commit;
import ru.gdgkazan.githubmvp.content.Repository;

/**
 * @author Artur Vasilov
 */
public class CommitsHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.commitAuthor)
    TextView mAuthor;

    @BindView(R.id.commitMessage)
    TextView mMessage;

    public CommitsHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull Commit commit) {
        mAuthor.setText(commit.getCommit().getAuthor().getAuthorName());
        mMessage.setText(commit.getCommit().getmCommitMessage());
    }
}
