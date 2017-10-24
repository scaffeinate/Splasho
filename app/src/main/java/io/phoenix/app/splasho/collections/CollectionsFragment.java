package io.phoenix.app.splasho.collections;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.phoenix.app.splasho.R;
import io.phoenix.app.splasho.container.Tab;
import io.phoenix.app.splasho.data.models.Collection;
import io.phoenix.app.splasho.data.repositories.collections.CollectionsRepository;
import io.phoenix.app.splasho.util.EndlessRecyclerViewScrollListener;
import io.phoenix.app.splasho.util.HTTPUtils;

import static io.phoenix.app.splasho.Splasho.CURRENT_TAB;
import static io.phoenix.app.splasho.Splasho.NUM_COLUMNS_STREAM;
import static io.phoenix.app.splasho.Splasho.VIEW_CACHE_SIZE;
import static io.phoenix.app.splasho.collections.CollectionsContract.Type.ALL;

/**
 * Created by sudharti on 10/21/17.
 */

public class CollectionsFragment extends Fragment implements CollectionsContract.View {

    private Context mContext;
    private CollectionsPresenter mPresenter;
    private CollectionsRepository mRepository;

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private TextView mErrorMessage;

    private RecyclerView.LayoutManager mLayoutManager;
    private EndlessRecyclerViewScrollListener mScrollListener;
    private CollectionsGridAdapter mAdapter;
    private Tab mTab;

    private List<Collection> mCollections;
    private int mStartPage = 1;
    private String mOrderBy;

    public static CollectionsFragment newInstance(Tab tab) {
        CollectionsFragment fragment = new CollectionsFragment();

        Bundle args = new Bundle();
        args.putParcelable(CURRENT_TAB, tab);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mRepository = CollectionsRepository.getInstance();
        mPresenter = new CollectionsPresenter(this, mRepository);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid, container, false);

        setRetainInstance(true);

        mTab = getArguments().getParcelable(CURRENT_TAB);
        mOrderBy = (mTab != null) ? mTab.getKey() : ALL;

        mProgressBar = view.findViewById(R.id.pb_loading_indicator);
        mRecyclerView = view.findViewById(R.id.rv_grid);
        mErrorMessage = view.findViewById(R.id.tv_error_message_display);

        mCollections = new ArrayList<>();

        mLayoutManager = new GridLayoutManager(mContext, NUM_COLUMNS_STREAM);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mScrollListener = new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadCollections(page, mOrderBy);
            }
        };
        mRecyclerView.addOnScrollListener(mScrollListener);

        mAdapter = new CollectionsGridAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemViewCacheSize(VIEW_CACHE_SIZE);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadCollections(mStartPage, mOrderBy);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.cancel();
    }

    @Override
    public void onCollectionsLoaded(List<Collection> collections) {
        showRecyclerView();
        mCollections.addAll(collections);
        mAdapter.setCollections(mCollections);
    }

    @Override
    public void onDataNotAvailable(String errorMessage) {
        String displayMessage = mContext.getResources().getString(R.string.something_went_wrong_error_message);
        if (mCollections == null || mCollections.isEmpty()) {
            showErrorMessage();
            mErrorMessage.setText(displayMessage);
        } else {
            Toast.makeText(mContext, displayMessage, Toast.LENGTH_LONG).show();
        }
    }

    private void loadCollections(int page, String mOrderBy) {
        if (HTTPUtils.isNetworkEnabled(mContext)) {
            if (mCollections == null || mCollections.isEmpty()) {
                showProgressBar();
            }
            mPresenter.loadCollections(page, mOrderBy);
        } else {
            String displayMessage = mContext.getResources().getString(R.string.unable_to_connect_error_message);
            if (mCollections == null || mCollections.isEmpty()) {
                showErrorMessage();
                mErrorMessage.setText(displayMessage);
            } else {
                Toast.makeText(mContext, displayMessage, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.INVISIBLE);
    }

    private void showRecyclerView() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.INVISIBLE);
    }

    private void showErrorMessage() {
        mErrorMessage.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
    }
}
