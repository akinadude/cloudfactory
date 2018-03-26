package com.akinadude.cloudfactory.viewholder;

import android.view.View;
import android.widget.TextView;

import com.akinadude.cloudfactory.R;
import com.akinadude.cloudfactory.model.Model;
import com.akinadude.cloudfactory.model.Ticker;
import com.akinadude.cloudfactory.utils.ResourceUtils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class TickerVH extends BaseVH {

    private TextView nameTv;
    private TextView lastTv;
    private TextView highestBidTv;
    private TextView percentChangeTv;

    public TickerVH(View itemView) {
        super(itemView);
        nameTv = itemView.findViewById(R.id.text_ticker_name);
        lastTv = itemView.findViewById(R.id.text_last);
        highestBidTv = itemView.findViewById(R.id.text_highest_bid);
        percentChangeTv = itemView.findViewById(R.id.text_percent_change);
    }

    @Override
    public void bind(Model m) {
        Ticker t = (Ticker) m;
        nameTv.setText(t.getType());

        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(9);
        df.setDecimalSeparatorAlwaysShown(true);
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setGroupingSeparator(',');
        dfs.setDecimalSeparator('.');
        df.setGroupingSize(3);
        df.setGroupingUsed(true);
        df.setDecimalFormatSymbols(dfs);
        lastTv.setText(df.format(Double.parseDouble(t.getLast())));
        highestBidTv.setText(df.format(Double.parseDouble(t.getHighestBid())));

        DecimalFormat percentFormat = new DecimalFormat();
        percentFormat.setMaximumFractionDigits(2);
        percentFormat.setMinimumFractionDigits(2);
        percentFormat.setDecimalSeparatorAlwaysShown(true);
        percentFormat.setDecimalFormatSymbols(dfs);
        Double percentChange = Double.parseDouble(t.getPercentChange()) * 100;
        String pcFormatted = percentFormat.format(percentChange) + "%";
        percentChangeTv.setText(percentChange > 0 ? "+" + pcFormatted : pcFormatted);
        percentChangeTv.setTextColor(ResourceUtils.getColor(itemView.getContext(),
                percentChange > 0 ? R.color.green : R.color.red));
    }
}
