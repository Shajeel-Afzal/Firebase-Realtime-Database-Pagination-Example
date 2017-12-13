package com.shajeelafzal.firebaserealtimedatabasepagination.view_holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.shajeelafzal.firebaserealtimedatabasepagination.R;
import com.shajeelafzal.firebaserealtimedatabasepagination.models.UserModel;

/**
 * Created by shajeelafzal on 05/12/2017.
 */

public class UserRVViewHolder extends RecyclerView.ViewHolder {

    private ImageView mUserIV;
    private TextView mUserNameTV;
    private TextView mEmailTV;
    private TextView mProviderTV;

    public UserRVViewHolder(View itemView) {
        super(itemView);
        findViews(itemView);
    }

    private void findViews(View view) {
        mUserIV = view.findViewById(R.id.user_iv);
        mUserNameTV = view.findViewById(R.id.user_name_iv);
        mEmailTV = view.findViewById(R.id.email);
        mProviderTV = view.findViewById(R.id.provider);
    }

    public void setData(UserModel userModel) {
        mUserNameTV.setText(userModel.getName());
        mEmailTV.setText(userModel.getEmail());
        mProviderTV.setText(userModel.getProvider());

        Glide.with(mUserIV.getContext())
                .load(userModel.getProfileImageLink())
                .thumbnail(0.1f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mUserIV);
    }

}
