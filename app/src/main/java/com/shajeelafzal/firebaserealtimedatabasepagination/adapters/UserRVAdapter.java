package com.shajeelafzal.firebaserealtimedatabasepagination.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.shajeelafzal.firebaserealtimedatabasepagination.R;
import com.shajeelafzal.firebaserealtimedatabasepagination.models.UserModel;
import com.shajeelafzal.firebaserealtimedatabasepagination.view_holders.UserRVViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shajeelafzal on 05/12/2017.
 */

public class UserRVAdapter extends RecyclerView.Adapter<UserRVViewHolder> {

    private List<UserModel> userModels;

    public UserRVAdapter() {
        this.userModels = new ArrayList<>();
    }

    @Override
    public UserRVViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserRVViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(UserRVViewHolder holder, int position) {
        holder.setData(userModels.get(position));
    }

    @Override
    public int getItemCount() {
        return userModels.size();
    }

    public void addAll(List<UserModel> newUsers) {
        int initialSize = userModels.size();
        userModels.addAll(newUsers);
        notifyItemRangeInserted(initialSize, newUsers.size());
    }

    public String getLastItemId() {
        return userModels.get(userModels.size() - 1).getUid();
    }
}
