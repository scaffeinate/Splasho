package io.phoenix.app.splasho.photos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.phoenix.app.splasho.R;
import io.phoenix.app.splasho.models.Photo;

/**
 * Created by sudharti on 10/22/17.
 */

public class PhotosGridAdapter extends RecyclerView.Adapter<PhotosGridAdapter.ViewHolder> {

    private List<Photo> mPhotos;
    private Random mRandom = new Random();

    public PhotosGridAdapter() {
        this.mPhotos = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, null);
        view.setMinimumHeight(parent.getMeasuredHeight() / 2);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    public void setPhotos(List<Photo> photos) {
        this.mPhotos = photos;
        notifyDataSetChanged();
    }

    protected int getRandomIntInRange(int max, int min) {
        return mRandom.nextInt((max - min) + min) + min;
    }

    final class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView mPhotoImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mPhotoImageView = itemView.findViewById(R.id.iv_photo);
        }

        private void bind(int position) {
            Photo photo = mPhotos.get(position);
            String imageUrl = photo.getUrls().getSmall();
            if (imageUrl != null) {
                Picasso.with(mPhotoImageView.getContext()).load(imageUrl)
                        .fit().centerCrop()
                        .into(mPhotoImageView);
            }
        }
    }
}
