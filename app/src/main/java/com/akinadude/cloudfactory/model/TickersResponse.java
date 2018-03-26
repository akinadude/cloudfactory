
package com.akinadude.cloudfactory.model;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.Expose;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TickersResponse implements Model {

    @Expose(serialize = false, deserialize = false)
    private List<Ticker> tickers;

    public List<Ticker> getTickers() {
        return tickers;
    }

    public void setTickers(List<Ticker> list) {
        tickers = list;
    }

    public static class Deserializer implements JsonDeserializer<TickersResponse> {

        @Override
        public TickersResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            List<Ticker> tickers = new ArrayList<>();
            TickersResponse tickersResponse = new Gson().fromJson(json, TickersResponse.class);
            JsonObject jsonObject = json.getAsJsonObject();

            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                JsonObject tickerJsonObject = entry.getValue().getAsJsonObject();
                Ticker ticker = new Gson().fromJson(tickerJsonObject, Ticker.class);
                ticker.setType(entry.getKey());
                tickers.add(ticker);
            }
            tickersResponse.setTickers(tickers);
            return tickersResponse;
        }
    }
}
