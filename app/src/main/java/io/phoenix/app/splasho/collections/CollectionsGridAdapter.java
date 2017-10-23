package io.phoenix.app.splasho.collections;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.phoenix.app.splasho.R;
import io.phoenix.app.splasho.data.models.Collection;
import io.phoenix.app.splasho.data.models.CoverPhoto;
import io.phoenix.app.splasho.util.DisplayUtils;

/**
 * Created by sudharti on 10/22/17.
 */

public class CollectionsGridAdapter extends RecyclerView.Adapter<CollectionsGridAdapter.ViewHolder> {

    private List<Collection> mCollections;
    private Activity mActivity;

    public CollectionsGridAdapter(Activity activity) {
        this.mActivity = activity;
        this.mCollections = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collection, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mCollections.size();
    }

    public void setCollections(List<Collection> collections) {
        this.mCollections = collections;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView mCollectionImageView;
        final TextView mCollectionTextView;
        final TextView mCollectionCountTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mCollectionImageView = itemView.findViewById(R.id.iv_collection);
            mCollectionTextView = itemView.findViewById(R.id.tv_collection);
            mCollectionCountTextView = itemView.findViewById(R.id.tv_collection_count);
        }

        private void bind(int position) {
            Collection collection = mCollections.get(position);
            if (collection != null) {
                int screenHeight = DisplayUtils.getInstance(mActivity).getScreenHeight();
                int height = screenHeight / 3;

                itemView.setMinimumHeight(height);

                if (collection.getTitle() != null) {
                    mCollectionTextView.setText(collection.getTitle());
                }

                mCollectionCountTextView.setText(collection.getTotalPhotos() + " photos");

                CoverPhoto coverPhoto = collection.getCoverPhoto();
                if (coverPhoto != null && coverPhoto.getUrls() != null && coverPhoto.getUrls().getSmall() != null) {
                    Picasso.with(itemView.getContext())
                            .load(collection.getCoverPhoto().getUrls().getSmall())
                            .centerCrop().fit()
                            .into(mCollectionImageView);
                }
            }
        }
    }
}
