package com.akinadude.cloudfactory.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ticker implements Model {

    @SerializedName("id")
    @Expose
    protected Integer id;
    @SerializedName("last")
    @Expose
    protected String last;
    @SerializedName("lowestAsk")
    @Expose
    protected String lowestAsk;
    @SerializedName("highestBid")
    @Expose
    protected String highestBid;
    @SerializedName("percentChange")
    @Expose
    protected String percentChange;
    @SerializedName("baseVolume")
    @Expose
    protected String baseVolume;
    @SerializedName("quoteVolume")
    @Expose
    protected String quoteVolume;
    @SerializedName("isFrozen")
    @Expose
    protected String isFrozen;
    @SerializedName("high24hr")
    @Expose
    protected String high24hr;
    @SerializedName("low24hr")
    @Expose
    protected String low24hr;

    @Expose(serialize = false, deserialize = false)
    protected String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getLowestAsk() {
        return lowestAsk;
    }

    public void setLowestAsk(String lowestAsk) {
        this.lowestAsk = lowestAsk;
    }

    public String getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(String highestBid) {
        this.highestBid = highestBid;
    }

    public String getPercentChange() {
        return percentChange;
    }

    public void setPercentChange(String percentChange) {
        this.percentChange = percentChange;
    }

    public String getBaseVolume() {
        return baseVolume;
    }

    public void setBaseVolume(String baseVolume) {
        this.baseVolume = baseVolume;
    }

    public String getQuoteVolume() {
        return quoteVolume;
    }

    public void setQuoteVolume(String quoteVolume) {
        this.quoteVolume = quoteVolume;
    }

    public String getIsFrozen() {
        return isFrozen;
    }

    public void setIsFrozen(String isFrozen) {
        this.isFrozen = isFrozen;
    }

    public String getHigh24hr() {
        return high24hr;
    }

    public void setHigh24hr(String high24hr) {
        this.high24hr = high24hr;
    }

    public String getLow24hr() {
        return low24hr;
    }

    public void setLow24hr(String low24hr) {
        this.low24hr = low24hr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("Ticker type: %s, id: %s", type, id);
    }
}
