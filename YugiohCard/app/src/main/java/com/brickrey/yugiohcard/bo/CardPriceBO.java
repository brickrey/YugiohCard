package com.brickrey.yugiohcard.bo;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class CardPriceBO implements Comparable, Parcelable {

    private String cardmarket_price;
    private String tcgplayer_price;
    private String ebay_price;
    private String amazon_price;
    private String coolstuffinc_price;

    public CardPriceBO(){
        cardmarket_price = "";
        tcgplayer_price = "";
        ebay_price = "";
        amazon_price = "";
        coolstuffinc_price = "";
    }

    public CardPriceBO(JSONObject jsonObject) throws JSONException {
        this();
        if(jsonObject != null) {
            if(jsonObject.has(JSON_Price_CardMarket)) cardmarket_price = jsonObject.getString(JSON_Price_CardMarket);
            if(jsonObject.has(JSON_Price_TcgPlayer)) tcgplayer_price = jsonObject.getString(JSON_Price_TcgPlayer);
            if(jsonObject.has(JSON_Price_Ebay)) ebay_price = jsonObject.getString(JSON_Price_Ebay);
            if(jsonObject.has(JSON_Price_Amazon)) amazon_price = jsonObject.getString(JSON_Price_Amazon);
            if(jsonObject.has(JSON_Price_CoolStuff)) coolstuffinc_price = jsonObject.getString(JSON_Price_CoolStuff);
        }
    }

    protected CardPriceBO(Parcel in) {
        cardmarket_price = in.readString();
        tcgplayer_price = in.readString();
        ebay_price = in.readString();
        amazon_price = in.readString();
        coolstuffinc_price = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cardmarket_price);
        dest.writeString(tcgplayer_price);
        dest.writeString(ebay_price);
        dest.writeString(amazon_price);
        dest.writeString(coolstuffinc_price);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CardPriceBO> CREATOR = new Creator<CardPriceBO>() {
        @Override
        public CardPriceBO createFromParcel(Parcel in) {
            return new CardPriceBO(in);
        }

        @Override
        public CardPriceBO[] newArray(int size) {
            return new CardPriceBO[size];
        }
    };

    public String getCardmarket_price() {
        return cardmarket_price;
    }

    public void setCardmarket_price(String cardmarket_price) {
        this.cardmarket_price = cardmarket_price;
    }

    public String getTcgplayer_price() {
        return tcgplayer_price;
    }

    public void setTcgplayer_price(String tcgplayer_price) {
        this.tcgplayer_price = tcgplayer_price;
    }

    public String getEbay_price() {
        return ebay_price;
    }

    public void setEbay_price(String ebay_price) {
        this.ebay_price = ebay_price;
    }

    public String getAmazon_price() {
        return amazon_price;
    }

    public void setAmazon_price(String amazon_price) {
        this.amazon_price = amazon_price;
    }

    public String getCoolstuffinc_price() {
        return coolstuffinc_price;
    }

    public void setCoolstuffinc_price(String coolstuffinc_price) {
        this.coolstuffinc_price = coolstuffinc_price;
    }

    @Override
    public String toString() {
        return "{" +
                "\"cardmarket_price\": \"" + cardmarket_price + '\"' +
                ", \"tcgplayer_price\": \"" + tcgplayer_price + '\"' +
                ", \"ebay_price\": \"" + ebay_price + '\"' +
                ", \"amazon_price\": \"" + amazon_price + '\"' +
                ", \"coolstuffinc_price\": \"" + coolstuffinc_price + '\"' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return this == o ? 0 : 1;
    }

    public static String JSON_Price_CardMarket = "cardmarket_price";
    public static String JSON_Price_TcgPlayer = "tcgplayer_price";
    public static String JSON_Price_Ebay = "ebay_price";
    public static String JSON_Price_Amazon = "amazon_price";
    public static String JSON_Price_CoolStuff = "coolstuffinc_price";
}
