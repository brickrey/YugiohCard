package com.brickrey.yugiohcard.bo;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class CardBanBO implements Comparable, Parcelable {

    private String ban_tcg;
    private String ban_ocg;
    private String ban_goat;

    public CardBanBO(){
        ban_tcg = "";
        ban_ocg = "";
        ban_goat = "";
    }

    public CardBanBO(JSONObject jsonObject) throws JSONException{
        this();
        if(jsonObject != null) {
            if(jsonObject.has(JSON_Ban_Tcg)) ban_tcg = jsonObject.getString(JSON_Ban_Tcg);
            if(jsonObject.has(JSON_Ban_Ocg)) ban_ocg = jsonObject.getString(JSON_Ban_Ocg);
            if(jsonObject.has(JSON_Ban_Goat)) ban_goat = jsonObject.getString(JSON_Ban_Goat);
        }
    }

    protected CardBanBO(Parcel in) {
        ban_tcg = in.readString();
        ban_ocg = in.readString();
        ban_goat = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ban_tcg);
        dest.writeString(ban_ocg);
        dest.writeString(ban_goat);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CardBanBO> CREATOR = new Creator<CardBanBO>() {
        @Override
        public CardBanBO createFromParcel(Parcel in) {
            return new CardBanBO(in);
        }

        @Override
        public CardBanBO[] newArray(int size) {
            return new CardBanBO[size];
        }
    };

    public String getBan_tcg() {
        return ban_tcg;
    }

    public void setBan_tcg(String ban_tcg) {
        this.ban_tcg = ban_tcg;
    }

    public String getBan_ocg() {
        return ban_ocg;
    }

    public void setBan_ocg(String ban_ocg) {
        this.ban_ocg = ban_ocg;
    }

    public String getBan_goat() {
        return ban_goat;
    }

    public void setBan_goat(String ban_goat) {
        this.ban_goat = ban_goat;
    }

    @Override
    public String toString() {
        return "{" +
                "\"ban_tcg\": \"" + ban_tcg + '\"' +
                ", \"ban_ocg\": \"" + ban_ocg + '\"' +
                ", \"ban_goat\": \"" + ban_goat + '\"' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return this == o ? 0 : 1;
    }

    public static String JSON_Ban_Tcg = "ban_tcg";
    public static String JSON_Ban_Ocg = "ban_ocg";
    public static String JSON_Ban_Goat = "ban_goat";
}
