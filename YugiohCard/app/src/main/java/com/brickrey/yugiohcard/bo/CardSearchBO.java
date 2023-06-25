package com.brickrey.yugiohcard.bo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardSearchBO implements Comparable{

    private CardInfoBO [] data;
    private CardMetaBO meta;

    public CardSearchBO(){
        meta = new CardMetaBO();
        data = new CardInfoBO[0];
    }

    public CardSearchBO(JSONObject jsonObject) throws JSONException {
        this();
        if (jsonObject != null){
            if(jsonObject.has(JSON_Search_Meta)) meta = new CardMetaBO(jsonObject.getJSONObject(JSON_Search_Meta));
            if(jsonObject.has(JSON_Search_Data)) {
                JSONArray c_array = jsonObject.getJSONArray(JSON_Search_Data);
                List<CardInfoBO> c_list = new ArrayList<>();
                for (int i = 0; i < c_array.length(); i++) {
                    c_list.add(new CardInfoBO(c_array.getJSONObject(i)));
                }
                this.data = new CardInfoBO[c_list.size()];
                if(!c_list.isEmpty()) c_list.toArray(this.data);
            }
        }
    }

    public CardInfoBO[] getData() {
        return data;
    }

    public void setData(CardInfoBO[] data) {
        this.data = data;
    }

    public CardMetaBO getMeta() {
        return meta;
    }

    public void setMeta(CardMetaBO meta) {
        this.meta = meta;
    }

    @Override
    public String toString() {
        return "{" +
                "\"data\": " + Arrays.toString(data) +
                ", \"meta\": " + meta +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return this == o ? 0 : 1;
    }

    public static String JSON_Search_Data = "data";
    public static String JSON_Search_Meta = "meta";
}
