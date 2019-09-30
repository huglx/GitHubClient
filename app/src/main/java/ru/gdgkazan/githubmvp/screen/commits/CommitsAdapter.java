package ru.gdgkazan.githubmvp.screen.commits;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ru.gdgkazan.githubmvp.R;
import ru.gdgkazan.githubmvp.content.Commit;
import ru.gdgkazan.githubmvp.content.Repository;
import ru.gdgkazan.githubmvp.screen.repositories.RepositoryHolder;
import ru.gdgkazan.githubmvp.widget.BaseAdapter;

/**
 * @author Artur Vasilov
 */
public class CommitsAdapter extends BaseAdapter<CommitsHolder, Commit> {

    public CommitsAdapter(@NonNull List<Commit> items) {
        super(items);
    }

    @Override
    public CommitsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommitsHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_commit, parent, false));
    }

    @Override
    public void onBindViewHolder(CommitsHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Commit commit = getItem(position);
        holder.bind(commit);
    }

}
