package io.phoenix.app.splasho.curated;

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

import java.util.List;

import io.phoenix.app.splasho.R;
import io.phoenix.app.splasho.container.Tab;
import io.phoenix.app.splasho.data.photos.PhotosRepository;
import io.phoenix.app.splasho.models.Photo;
import io.phoenix.app.splasho.photos.PhotosGridAdapter;
import io.phoenix.app.splasho.util.HTTPUtils;

import static io.phoenix.app.splasho.Splasho.CURRENT_TAB;
import static io.phoenix.app.splasho.Splasho.NUM_COLUMNS_IN_GRID;
import static io.phoenix.app.splasho.Splasho.VIEW_CACHE_SIZE;
import static io.phoenix.app.splasho.photos.PhotosContract.OrderBy.LATEST;

/**
 * Created by sudharti on 10/22/17.
 */

public class CuratedFragment extends Fragment implements CuratedContract.View {

    private Context mContext;
    private CuratedPresenter mPresenter;
    private PhotosRepository mRepository;

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private TextView mErrorMessage;

    private PhotosGridAdapter mAdapter;

    private Tab mTab;

    public static CuratedFragment newInstance(Tab tab) {
        CuratedFragment fragment = new CuratedFragment();

        Bundle args = new Bundle();
        args.putParcelable(CURRENT_TAB, tab);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mRepository = PhotosRepository.getInstance();
        mPresenter = new CuratedPresenter(this, mRepository);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid, container, false);

        mTab = getArguments().getParcelable(CURRENT_TAB);
        mProgressBar = view.findViewById(R.id.pb_loading_indicator);
        mRecyclerView = view.findViewById(R.id.rv_grid);
        mErrorMessage = view.findViewById(R.id.tv_error_message_display);

        GridLayoutManager mLayoutManager = new GridLayoutManager(mContext, NUM_COLUMNS_IN_GRID);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new PhotosGridAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemViewCacheSize(VIEW_CACHE_SIZE);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (HTTPUtils.isNetworkEnabled(mContext)) {
            showProgressBar();
            mPresenter.loadCuratedPhotos(1, (mTab != null) ? mTab.getKey() : LATEST);
        } else {
            showErrorMessage();
            mErrorMessage.setText(mContext.getResources().getString(R.string.unable_to_connect_error_message));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.cancel();
    }

    @Override
    public void onCuratedPhotosLoaded(List<Photo> photos) {
        showRecyclerView();
        mAdapter.setPhotos(photos);
    }

    @Override
    public void onDataNotAvailable(String errorMessage) {
        showErrorMessage();
        mErrorMessage.setText(mContext.getResources().getString(R.string.something_went_wrong_error_message));
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
