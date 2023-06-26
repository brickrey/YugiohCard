package com.brickrey.yugiohcard.bo;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class CardSetBO implements Comparable, Parcelable {

    private String set_name;
    private String set_code;
    private String set_rarity;
    private String set_rarity_code;
    private String set_price;

    public CardSetBO(){
        set_name = "";
        set_code = "";
        set_rarity = "";
        set_rarity_code = "";
        set_price = "";
    }

    public CardSetBO(JSONObject jsonObject) throws JSONException{
        this();
        if (jsonObject != null){
            if(jsonObject.has(JSON_Set_Name)) set_name = jsonObject.getString(JSON_Set_Name);
            if(jsonObject.has(JSON_Set_Code)) set_code = jsonObject.getString(JSON_Set_Code);
            if(jsonObject.has(JSON_Set_Rarity)) set_rarity = jsonObject.getString(JSON_Set_Rarity);
            if(jsonObject.has(JSON_Set_RarityCode)) set_rarity_code = jsonObject.getString(JSON_Set_RarityCode);
            if(jsonObject.has(JSON_Set_Price)) set_price = jsonObject.getString(JSON_Set_Price);
        }
    }

    protected CardSetBO(Parcel in) {
        set_name = in.readString();
        set_code = in.readString();
        set_rarity = in.readString();
        set_rarity_code = in.readString();
        set_price = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(set_name);
        dest.writeString(set_code);
        dest.writeString(set_rarity);
        dest.writeString(set_rarity_code);
        dest.writeString(set_price);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CardSetBO> CREATOR = new Creator<CardSetBO>() {
        @Override
        public CardSetBO createFromParcel(Parcel in) {
            return new CardSetBO(in);
        }

        @Override
        public CardSetBO[] newArray(int size) {
            return new CardSetBO[size];
        }
    };

    public String getSet_name() {
        return set_name;
    }

    public void setSet_name(String set_name) {
        this.set_name = set_name;
    }

    public String getSet_code() {
        return set_code;
    }

    public void setSet_code(String set_code) {
        this.set_code = set_code;
    }

    public String getSet_rarity() {
        return set_rarity;
    }

    public void setSet_rarity(String set_rarity) {
        this.set_rarity = set_rarity;
    }

    public String getSet_rarity_code() {
        return set_rarity_code;
    }

    public void setSet_rarity_code(String set_rarity_code) {
        this.set_rarity_code = set_rarity_code;
    }

    public String getSet_price() {
        return set_price;
    }

    public void setSet_price(String set_price) {
        this.set_price = set_price;
    }

    @Override
    public String toString() {
        return "{" +
                "\"set_name\": \"" + set_name + '\"' +
                ", \"set_code\": \"" + set_code + '\"' +
                ", \"set_rarity\": \"" + set_rarity + '\"' +
                ", \"set_rarity_code\": \"" + set_rarity_code + '\"' +
                ", \"set_price\": \"" + set_price + '\"' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return this == o ? 0 : 1;
    }

    public static String JSON_Set_Name = "set_name";
    public static String JSON_Set_Code = "set_code";
    public static String JSON_Set_Rarity = "set_rarity";
    public static String JSON_Set_RarityCode = "set_rarity_code";
    public static String JSON_Set_Price = "set_price";
}
