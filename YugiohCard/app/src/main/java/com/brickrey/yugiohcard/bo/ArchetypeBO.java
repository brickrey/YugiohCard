package com.brickrey.yugiohcard.bo;

import org.json.JSONException;
import org.json.JSONObject;

public class ArchetypeBO implements Comparable {

    private String archetype_name;

    public ArchetypeBO(){
        archetype_name = "";
    }

    public ArchetypeBO(JSONObject jsonObject) throws JSONException{
        this();
        if(jsonObject != null){
            if(jsonObject.has(JSON_Archetype_Name)) setArchetype_name(jsonObject.getString(JSON_Archetype_Name));
        }
    }

    public String getArchetype_name() {
        return archetype_name;
    }

    public void setArchetype_name(String archetype_name) {
        this.archetype_name = archetype_name;
    }

    @Override
    public String toString() {
        return "{" +
                "\"archetype_name\": \"" + archetype_name + '\"' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return this == o ? 0 : 1;
    }

    public static String JSON_Archetype_Name = "archetype_name";
}
