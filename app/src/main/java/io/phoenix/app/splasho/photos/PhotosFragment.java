package io.phoenix.app.splasho.photos;

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
import io.phoenix.app.splasho.data.models.Photo;
import io.phoenix.app.splasho.data.repositories.photos.PhotosRepository;
import io.phoenix.app.splasho.util.EndlessRecyclerViewScrollListener;
import io.phoenix.app.splasho.util.HTTPUtils;

import static io.phoenix.app.splasho.Splasho.CURRENT_TAB;
import static io.phoenix.app.splasho.Splasho.NUM_COLUMNS_IN_GRID;
import static io.phoenix.app.splasho.Splasho.VIEW_CACHE_SIZE;
import static io.phoenix.app.splasho.photos.PhotosContract.OrderBy.LATEST;

/**
 * Created by sudharti on 10/21/17.
 */

public class PhotosFragment extends Fragment implements PhotosContract.View {

    private Context mContext;
    private PhotosRepository mRepository;
    private PhotosPresenter mPresenter;

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private TextView mErrorMessage;

    private RecyclerView.LayoutManager mLayoutManager;
    private EndlessRecyclerViewScrollListener mScrollListener;
    private PhotosGridAdapter mAdapter;
    private Tab mTab;

    private List<Photo> mPhotos;
    private int mStartPage = 1;
    private String mOrderBy;


    // TODO (5): Retain Tab Key in saveInstance
    // TODO (6): Fix issue when Network goes back and comes again; Endless Scrolling Fix;
    // TODO (7): Custom ProgressBar
    public static PhotosFragment newInstance(Tab tab) {
        PhotosFragment fragment = new PhotosFragment();

        Bundle args = new Bundle();
        args.putParcelable(CURRENT_TAB, tab);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO (14): Remove this to avoid memory leaks. Used savedInstance to store.
        setRetainInstance(true);
        mContext = getContext();
        mRepository = PhotosRepository.getInstance();
        mPresenter = new PhotosPresenter(this, mRepository);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid, container, false);

        mTab = getArguments().getParcelable(CURRENT_TAB);
        mOrderBy = (mTab != null) ? mTab.getKey() : LATEST;

        mProgressBar = view.findViewById(R.id.pb_loading_indicator);
        mRecyclerView = view.findViewById(R.id.rv_grid);
        mErrorMessage = view.findViewById(R.id.tv_error_message_display);

        mPhotos = new ArrayList<>();

        mLayoutManager = new GridLayoutManager(mContext, NUM_COLUMNS_IN_GRID);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemViewCacheSize(VIEW_CACHE_SIZE);
        mRecyclerView.setHasFixedSize(true);
        mScrollListener = new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadPhotos(page, mOrderBy);
            }
        };
        mRecyclerView.addOnScrollListener(mScrollListener);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new PhotosGridAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadPhotos(mStartPage, mOrderBy);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.cancel();
    }

    @Override
    public void onPhotosLoaded(List<Photo> photos) {
        showRecyclerView();
        mPhotos.addAll(photos);
        mAdapter.setPhotos(mPhotos);
    }

    @Override
    public void onDataNotAvailable(String errorMessage) {
        String displayMessage = mContext.getResources().getString(R.string.something_went_wrong_error_message);
        if (mPhotos == null || mPhotos.isEmpty()) {
            showErrorMessage();
            mErrorMessage.setText(displayMessage);
        } else {
            Toast.makeText(mContext, displayMessage, Toast.LENGTH_LONG).show();
        }
    }

    private void loadPhotos(int page, String mOrderBy) {
        if (HTTPUtils.isNetworkEnabled(mContext)) {
            if (mPhotos == null || mPhotos.isEmpty()) {
                showProgressBar();
            }
            mPresenter.loadPhotos(page, mOrderBy);
        } else {
            String displayMessage = mContext.getResources().getString(R.string.unable_to_connect_error_message);
            if (mPhotos == null || mPhotos.isEmpty()) {
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
