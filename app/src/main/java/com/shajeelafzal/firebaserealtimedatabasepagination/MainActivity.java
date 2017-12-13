package com.shajeelafzal.firebaserealtimedatabasepagination;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.shajeelafzal.firebaserealtimedatabasepagination.adapters.UserRVAdapter;
import com.shajeelafzal.firebaserealtimedatabasepagination.models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private RecyclerView mRV;
    private UserRVAdapter mAdapter;
    private int mTotalItemCount = 0;
    private int mLastVisibleItemPosition;
    private boolean mIsLoading = false;
    private int mPostsPerPage = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRV = findViewById(R.id.rv);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRV.setLayoutManager(mLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRV.getContext(),
                mLayoutManager.getOrientation());
        mRV.addItemDecoration(dividerItemDecoration);

        mAdapter = new UserRVAdapter();
        mRV.setAdapter(mAdapter);

        getUsers(null);

        mRV.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                mTotalItemCount = mLayoutManager.getItemCount();
                mLastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();

                if (!mIsLoading && mTotalItemCount <= (mLastVisibleItemPosition + mPostsPerPage)) {
                    getUsers(mAdapter.getLastItemId());
                    mIsLoading = true;
                }
            }
        });
    }

    private void getUsers(String nodeId) {
        Query query;

        if (nodeId == null)
            query = FirebaseDatabase.getInstance().getReference()
                    .child(Consts.FIREBASE_DATABASE_LOCATION_USERS)
                    .orderByKey()
                    .limitToFirst(mPostsPerPage);
        else
            query = FirebaseDatabase.getInstance().getReference()
                    .child(Consts.FIREBASE_DATABASE_LOCATION_USERS)
                    .orderByKey()
                    .startAt(nodeId)
                    .limitToFirst(mPostsPerPage);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserModel user;
                List<UserModel> userModels = new ArrayList<>();
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    userModels.add(userSnapshot.getValue(UserModel.class));
                }

                mAdapter.addAll(userModels);
                mIsLoading = false;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mIsLoading = false;
            }
        });
    }
}

