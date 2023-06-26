package com.brickrey.yugiohcard.service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.brickrey.yugiohcard.bo.ArchetypeBO;
import com.brickrey.yugiohcard.bo.CardSearchBO;
import com.brickrey.yugiohcard.helper.LogHelper;
import com.brickrey.yugiohcard.network.NetworkConstants;
import com.brickrey.yugiohcard.network.VolleyManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class CardInfoApiService {

    private static final String TAG = CardInfoApiService.class.getName();

    public static boolean getAllArchetypesRequest(final Context context, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener){
        String url = NetworkConstants.WS_END_POINT_URL + NetworkConstants.OP_ARCHETYPES;

        // Header Parameters
        final HashMap<String, String> headerParameters = new HashMap<>();
        // Json Request
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, listener, errorListener) {
            @Override
            public HashMap<String, String> getHeaders() {
                return headerParameters;
            }
        };

        try {
            // Put Request Queue
            VolleyManager.getInstance(context).addToRequestQueue(jsonArrayRequest);
            return true;
        } catch (Exception ex) {
            LogHelper.logException(TAG, ex, context);
        }
        return false;
    }

    public static ArrayList<ArchetypeBO> getAllArchetypesResponse(JSONArray jsonArray, final Context context) throws JSONException {
        ArrayList<ArchetypeBO> list = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i ++){
                list.add(new ArchetypeBO((JSONObject) jsonArray.get(i)));
            }
        }catch (Exception ex){
            LogHelper.logException(TAG, ex, context);
        }
        return list;
    }

    public static boolean searchCard(String searchText, String filter, int rows, int offset, final Context context, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){
        String url = NetworkConstants.WS_END_POINT_URL + NetworkConstants.OP_CARD + "?";
        if(rows > 0) url += "&num=" + rows + "&offset=" + offset;
        if(searchText != null && searchText.length() > 0) url += "&fname=" + searchText;
        if(filter != null && !filter.isEmpty()) url += "&archetype=" + filter;
        // Header Parameters
        final HashMap<String, String> headerParameters = new HashMap<>();
        // Json Request
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null, listener, errorListener) {
            @Override
            public HashMap<String, String> getHeaders() {
                return headerParameters;
            }
        };

        try {
            // Put Request Queue
            VolleyManager.getInstance(context).addToRequestQueue(jsonArrayRequest);
            return true;
        } catch (Exception ex) {
            LogHelper.logException(TAG, ex, context);
        }
        return false;
    }

    public static CardSearchBO searchCardResponse(JSONObject jsonObject, final Context context) {
        try {
            if(jsonObject != null){
                return new CardSearchBO(jsonObject);
            }
        }catch (Exception ex){
            LogHelper.logException(TAG, ex, context);
        }
        return null;
    }
}
