package com.brickrey.yugiohcard.bo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardInfoBO implements Comparable{

    private int id;
    private String name;
    private String type;
    private String frameType;
    private String desc;
    private int atk;
    private int def;
    private int level;
    private String race;
    private String attribute;
    private CardSetBO [] card_sets;
    private CardImageBO [] card_images;
    private CardPriceBO [] card_prices;
    private CardBanBO card_banList;

    public CardInfoBO(){
        id = 0;
        name = "";
        type = "";
        frameType = "";
        desc = "";
        atk = 0;
        def = 0;
        level = 0;
        race = "";
        attribute = "";
        card_sets = new CardSetBO[0];
        card_images = new CardImageBO[0];
        card_prices = new CardPriceBO[0];
        card_banList = new CardBanBO();
    }

    public CardInfoBO(JSONObject jsonObject) throws JSONException{
        this();
        if(jsonObject != null) {
            if(jsonObject.has(JSON_Card_ID))id = jsonObject.getInt(JSON_Card_ID);
            if(jsonObject.has(JSON_Card_Name))name = jsonObject.getString(JSON_Card_Name);
            if(jsonObject.has(JSON_Card_Type))type = jsonObject.getString(JSON_Card_Type);
            if(jsonObject.has(JSON_Card_FrameType))frameType = jsonObject.getString(JSON_Card_FrameType);
            if(jsonObject.has(JSON_Card_Desc))desc = jsonObject.getString(JSON_Card_Desc);
            if(jsonObject.has(JSON_Card_Atk))atk = jsonObject.getInt(JSON_Card_Atk);
            if(jsonObject.has(JSON_Card_Def))def = jsonObject.getInt(JSON_Card_Def);
            if(jsonObject.has(JSON_Card_Level))level = jsonObject.getInt(JSON_Card_Level);
            if(jsonObject.has(JSON_Card_Race))race = jsonObject.getString(JSON_Card_Race);
            if(jsonObject.has(JSON_Card_Attribute))attribute = jsonObject.getString(JSON_Card_Attribute);
            if(jsonObject.has(JSON_Card_Set)) setCard_sets(jsonObject.getJSONArray(JSON_Card_Set));
            if(jsonObject.has(JSON_Card_Prices)) setCard_prices(jsonObject.getJSONArray(JSON_Card_Prices));
            if(jsonObject.has(JSON_Card_Images)) setCard_images(jsonObject.getJSONArray(JSON_Card_Images));
            if(jsonObject.has(JSON_Card_BanList)) setCard_banList(new CardBanBO(jsonObject.getJSONObject(JSON_Card_BanList)));
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrameType() {
        return frameType;
    }

    public void setFrameType(String frameType) {
        this.frameType = frameType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public CardSetBO[] getCard_sets() {
        return card_sets;
    }

    public void setCard_sets(CardSetBO[] card_sets) {
        this.card_sets = card_sets;
    }

    public void setCard_sets(JSONArray jsonArray) throws JSONException {
        List<CardSetBO> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(new CardSetBO(jsonArray.getJSONObject(i)));
        }
        this.card_sets = new CardSetBO[list.size()];
        if(!list.isEmpty()) list.toArray(this.card_sets);
    }

    public CardImageBO[] getCard_images() {
        return card_images;
    }

    public void setCard_images(CardImageBO[] card_images) {
        this.card_images = card_images;
    }

    public void setCard_images(JSONArray jsonArray) throws JSONException {
        List<CardImageBO> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(new CardImageBO(jsonArray.getJSONObject(i)));
        }
        this.card_images = new CardImageBO[list.size()];
        if(!list.isEmpty()) list.toArray(this.card_images);
    }

    public CardPriceBO[] getCard_prices() {
        return card_prices;
    }

    public void setCard_prices(CardPriceBO[] card_prices) {
        this.card_prices = card_prices;
    }

    public void setCard_prices(JSONArray jsonArray) throws JSONException {
        List<CardPriceBO> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(new CardPriceBO(jsonArray.getJSONObject(i)));
        }
        this.card_prices = new CardPriceBO[list.size()];
        if(!list.isEmpty()) list.toArray(this.card_prices);
    }

    public CardBanBO getCard_banList() {
        return card_banList;
    }

    public void setCard_banList(CardBanBO card_banList) {
        this.card_banList = card_banList;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\": " + id +
                ", \"name\": \"" + name + '\"' +
                ", \"type\": \"" + type + '\"' +
                ", \"frameType\": \"" + frameType + '\"' +
                ", \"desc\": \"" + desc + '\"' +
                ", \"atk=\": " + atk +
                ", \"def=\": " + def +
                ", \"level\": " + level +
                ", \"race\": \"" + race + '\"' +
                ", \"attribute\": \"" + attribute + '\"' +
                ", \"card_sets\": " + Arrays.toString(card_sets) +
                ", \"card_images\": " + Arrays.toString(card_images) +
                ", \"card_prices\": " + Arrays.toString(card_prices) +
                ", \"banlist_info\": " + card_banList +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return this == o ? 0 : 1;
    }

    public static String JSON_Card_ID = "id";
    public static String JSON_Card_Name = "name";
    public static String JSON_Card_Type = "type";
    public static String JSON_Card_FrameType = "frameType";
    public static String JSON_Card_Desc = "desc";
    public static String JSON_Card_Atk = "atk";
    public static String JSON_Card_Def = "def";
    public static String JSON_Card_Level = "level";
    public static String JSON_Card_Race = "race";
    public static String JSON_Card_Attribute = "attribute";
    public static String JSON_Card_Set = "card_sets";
    public static String JSON_Card_Images = "card_images";
    public static String JSON_Card_Prices = "card_prices";
    public static String JSON_Card_BanList = "banlist_info";
}
