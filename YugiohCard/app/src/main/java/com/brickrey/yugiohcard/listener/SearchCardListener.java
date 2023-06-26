package com.brickrey.yugiohcard.listener;

import android.content.Context;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Response;
import com.brickrey.yugiohcard.GlobalData;
import com.brickrey.yugiohcard.MainActivity;
import com.brickrey.yugiohcard.R;
import com.brickrey.yugiohcard.bo.CardSearchBO;
import com.brickrey.yugiohcard.helper.LogHelper;
import com.brickrey.yugiohcard.network.VolleyManager;
import com.brickrey.yugiohcard.service.CardInfoApiService;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;

public class SearchCardListener implements SearchView.OnQueryTextListener {

    public final static String TAG = SearchCardListener.class.getCanonicalName();

    private final Context mContext;
    private final Response.Listener<JSONObject> listener;
    private final Response.ErrorListener error;

    public SearchCardListener(Context context, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){
        this.mContext = context;
        this.listener = listener;
        this.error = errorListener;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        searchCardOnTextChange(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        searchCardOnTextChange(s);
        return false;
    }

    private void searchCardOnTextChange(String text){
        try {
            String input = URLEncoder.encode(text.trim(), "UTF-8");
            searchWS(input, GlobalData.getInstance().getRowsOnSearchView(), GlobalData.getInstance().getOffsetOnSearchView(), listener, error);
        } catch (UnsupportedEncodingException e) {
            LogHelper.logException(TAG, e, mContext);
        }
    }

    private void searchWS(String inputText, int rows, int offset, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){
        try{
            VolleyManager.getInstance(mContext.getApplicationContext()).getRequestQueue().cancelAll(VolleyManager.VOLLEY_REQUESTS_TAG);
            String filter = URLEncoder.encode(GlobalData.getInstance().getArchetypeFilter().trim(), "UTF-8");
            CardInfoApiService.searchCard(inputText,
                    filter,
                    rows,
                    offset,
                    mContext,
                    listener,
                    errorListener);
        } catch (Exception ex){
            Toast.makeText(mContext, R.string.detected_error, Toast.LENGTH_SHORT).show();
            LogHelper.logException(TAG, ex, mContext);
        }
    }
}
