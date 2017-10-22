package io.phoenix.app.splasho.photos;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import io.phoenix.app.splasho.util.HTTPUtils;

/**
 * Created by sudharti on 10/21/17.
 */

public class PhotosFragment extends Fragment implements PhotosContract.View {

    private static final String TAB = "tab";

    private Context mContext;
    private PhotosRepository mRepository;
    private PhotosPresenter mPresenter;

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private TextView mErrorMessage;

    private Tab mTab;

    public static PhotosFragment newInstance(Tab tab) {
        PhotosFragment fragment = new PhotosFragment();

        Bundle args = new Bundle();
        args.putParcelable(TAB, tab);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mRepository = PhotosRepository.getInstance();
        mPresenter = new PhotosPresenter(this, mRepository);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid, container, false);

        mTab = getArguments().getParcelable(TAB);

        mProgressBar = view.findViewById(R.id.pb_loading_indicator);
        mRecyclerView = view.findViewById(R.id.rv_grid);
        mErrorMessage = view.findViewById(R.id.tv_error_message_display);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (HTTPUtils.isNetworkEnabled(mContext)) {
            showProgressBar();
            mPresenter.loadPhotos(1, (mTab != null) ? mTab.getKey() : PhotosContract.OrderBy.LATEST);
        } else {
            showErrorMessage();
            mErrorMessage.setText(mContext.getResources().getString(R.string.unable_to_connect_error_message));
        }
    }

    @Override
    public void onPhotosLoaded(List<Photo> photos) {
        showRecyclerView();
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
