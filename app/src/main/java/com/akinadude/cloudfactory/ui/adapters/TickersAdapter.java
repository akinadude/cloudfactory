package com.akinadude.cloudfactory.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.akinadude.cloudfactory.R;
import com.akinadude.cloudfactory.model.Ticker;
import com.akinadude.cloudfactory.viewholder.BaseVH;
import com.akinadude.cloudfactory.viewholder.TickerVH;

import java.util.ArrayList;
import java.util.List;

public class TickersAdapter extends RecyclerView.Adapter<BaseVH> {

    private static final String TAG = TickersAdapter.class.getSimpleName();

    private List<Ticker> mTickers = new ArrayList<>();

    public void setItems(List<Ticker> items) {
        if (mTickers.isEmpty()) {
            mTickers = items;
            notifyDataSetChanged();
            return;
        }

        List<Integer> positionsToUpdate = new ArrayList<>();
        for (int i = 0; i < mTickers.size(); i++) {
            for (int j = 0; j < items.size(); j++) {
                Ticker outdated = mTickers.get(i);
                Ticker updated = items.get(j);
                if (outdated.getId().equals(updated.getId())
                        && !outdated.getLast().equals(updated.getLast())
                        && !outdated.getHighestBid().equals(updated.getHighestBid())
                        && !outdated.getPercentChange().equals(updated.getPercentChange())) {
                    mTickers.set(i, updated);
                    positionsToUpdate.add(i);
                }
            }
        }
        Log.d(TAG, "positionsToUpdate: " + positionsToUpdate);
        for (Integer p : positionsToUpdate)
            notifyItemChanged(p);
    }

    @Override
    public BaseVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        return new TickerVH(li.inflate(R.layout.item_rv_ticker, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseVH holder, int position) {
        holder.bind(mTickers.get(position));
    }

    @Override
    public int getItemCount() {
        return mTickers.size();
    }
}
