package io.phoenix.app.splasho.collections;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.phoenix.app.splasho.R;
import io.phoenix.app.splasho.models.Collection;

/**
 * Created by sudharti on 10/22/17.
 */

public class CollectionsGridAdapter extends RecyclerView.Adapter<CollectionsGridAdapter.ViewHolder> {

    List<Collection> mCollections;

    public CollectionsGridAdapter() {
        mCollections = new ArrayList<>();
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

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }

        private void bind(int position) {

        }
    }
}
