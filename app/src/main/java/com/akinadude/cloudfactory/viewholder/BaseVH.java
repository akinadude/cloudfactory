package com.akinadude.cloudfactory.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.akinadude.cloudfactory.model.Model;

public abstract class BaseVH extends RecyclerView.ViewHolder {

    public BaseVH(View itemView) {
        super(itemView);
    }

    public abstract void bind(Model m);
}
