package com.brickrey.yugiohcard.bo;

import org.json.JSONException;
import org.json.JSONObject;

public class CardImageBO implements Comparable{

    private int id;
    private String image_url;
    private String image_url_small;
    private String image_url_cropped;

    public CardImageBO(){
        id = 0;
        image_url = "";
        image_url_small = "";
        image_url_cropped = "";
    }

    public CardImageBO(JSONObject jsonObject) throws JSONException {
        this();
        if(jsonObject != null) {
            if(jsonObject.has(JSON_Image_ID)) id = jsonObject.getInt(JSON_Image_ID);
            if(jsonObject.has(JSON_Image_Url)) image_url = jsonObject.getString(JSON_Image_Url);
            if(jsonObject.has(JSON_Image_Url_Small)) image_url_small = jsonObject.getString(JSON_Image_Url_Small);
            if(jsonObject.has(JSON_Image_Url_Crop)) image_url_cropped = jsonObject.getString(JSON_Image_Url_Crop);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_url_small() {
        return image_url_small;
    }

    public void setImage_url_small(String image_url_small) {
        this.image_url_small = image_url_small;
    }

    public String getImage_url_cropped() {
        return image_url_cropped;
    }

    public void setImage_url_cropped(String image_url_cropped) {
        this.image_url_cropped = image_url_cropped;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"image_url\": \"" + image_url + '\"' +
                ", \"image_url_small\": \"" + image_url_small + '\"' +
                ", \"image_url_cropped\": \"" + image_url_cropped + '\"' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return this == o ? 0 : 1;
    }

    public static String JSON_Image_ID = "id";
    public static String JSON_Image_Url = "image_url";
    public static String JSON_Image_Url_Small = "image_url_small";
    public static String JSON_Image_Url_Crop = "image_url_cropped";

}
