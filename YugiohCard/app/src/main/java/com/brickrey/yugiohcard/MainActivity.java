package com.brickrey.yugiohcard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.brickrey.yugiohcard.bo.ArchetypeBO;
import com.brickrey.yugiohcard.bo.CardBanBO;
import com.brickrey.yugiohcard.bo.CardInfoBO;
import com.brickrey.yugiohcard.bo.CardSearchBO;
import com.brickrey.yugiohcard.helper.LogHelper;
import com.brickrey.yugiohcard.listener.SearchCardListener;
import com.brickrey.yugiohcard.service.CardInfoApiService;
import com.brickrey.yugiohcard.view.activity.CardDetailActivity;
import com.brickrey.yugiohcard.view.activity.FilterActivity;
import com.brickrey.yugiohcard.view.adapter.CardItemAdapter;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = MainActivity.class.getCanonicalName();

    private SearchView searchView;
    private ListView listView;
    private String searchFilter;

    private ArrayList<ArchetypeBO> archetypes;
    private final ArrayList<CardInfoBO> cardList = new ArrayList<>();
    private CardItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = findViewById(R.id.searchView);
        listView = findViewById(R.id.listView);

        getAllArchetypesWS();

        adapter = new CardItemAdapter(getApplicationContext(), cardList);
        View emptyView = getLayoutInflater().inflate(R.layout.empty_search_view, null);
        addContentView(emptyView, listView.getLayoutParams());
        listView.setEmptyView(emptyView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> showCardDetailActivity(i));

        searchView.setOnQueryTextListener(new SearchCardListener(getApplicationContext(), this::listCardSearchResponse, this::listCardErrorResponse));
        findViewById(R.id.filterButton).setOnClickListener(view -> showArchetypeFilter());

        searchFilter = GlobalData.getInstance().getArchetypeFilter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!searchFilter.equalsIgnoreCase(GlobalData.getInstance().getArchetypeFilter())){
            reloadListWithFilter();
            searchFilter = GlobalData.getInstance().getArchetypeFilter();
        }
    }

    private void getAllArchetypesWS(){
        try{
            CardInfoApiService.getAllArchetypesRequest(
                    getApplicationContext(),
                    response -> {
                        try {
                            archetypes = CardInfoApiService.getAllArchetypesResponse(response, getApplicationContext());
                            GlobalData.getInstance().setArchetypeList(archetypes);
                        }catch (Exception ex){
                            Toast.makeText(getApplicationContext(), R.string.detected_error, Toast.LENGTH_SHORT).show();
                            LogHelper.logException(TAG, ex, getApplicationContext());
                        }
                    },
                    error -> LogHelper.log(TAG, "Consulta sin resultado", 2, getApplicationContext()));
        } catch (Exception ex){
            Toast.makeText(getApplicationContext(), R.string.detected_error, Toast.LENGTH_SHORT).show();
            LogHelper.logException(TAG, ex, getApplicationContext());
        }
    }

    private void listCardSearchResponse(JSONObject response){
        try {
            CardSearchBO searchBO = CardInfoApiService.searchCardResponse(response, getApplicationContext());
            if(searchBO != null){
                cardList.clear();
                if(searchBO.getData().length > 0){
                    cardList.addAll(Arrays.asList(searchBO.getData()));
                    removedBannedCardsFromList(cardList);
                }
                if(searchBO.getMeta() != null){
                    LogHelper.log(TAG, searchBO.getMeta().toString(), 1, getApplicationContext());
                }
                adapter.notifyDataSetChanged();
            }
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), R.string.detected_error, Toast.LENGTH_SHORT).show();
            LogHelper.logException(TAG, ex, getApplicationContext());
        }
    }

    private void listCardErrorResponse(VolleyError error){
        if(error.networkResponse.statusCode == 400){
            cardList.clear();
            adapter.notifyDataSetChanged();
        }
        LogHelper.log(TAG, "No Result", 2, getApplicationContext());
    }

    private void showCardDetailActivity(int itemPosition){
        CardInfoBO cardInfoBO = cardList.get(itemPosition);
        Intent intent = new Intent(MainActivity.this, CardDetailActivity.class);
        intent.putExtra("card", cardInfoBO);
        startActivity(intent);
    }

    private void showArchetypeFilter(){
        Intent intent = new Intent(MainActivity.this, FilterActivity.class);
        startActivity(intent);
    }

    public void removedBannedCardsFromList(ArrayList<CardInfoBO> cardList){
        cardList.removeIf(cardInfoBO -> {
            CardBanBO ban = cardInfoBO.getCard_banList();
            if (ban != null){
                return ban.getBan_goat().equalsIgnoreCase("Banned")
                        || ban.getBan_tcg().equalsIgnoreCase("Banned")
                        || ban.getBan_ocg().equalsIgnoreCase("Banned");
            }
            return false;
        });
    }

    private void reloadListWithFilter(){
        try{
            String input = URLEncoder.encode(searchView.getQuery().toString().trim(), "UTF-8");
            String filter = URLEncoder.encode(GlobalData.getInstance().getArchetypeFilter().trim(), "UTF-8");
            CardInfoApiService.searchCard(input,
                    filter,
                    GlobalData.getInstance().getRowsOnSearchView(),
                    GlobalData.getInstance().getOffsetOnSearchView(),
                    getApplicationContext(),
                    this::listCardSearchResponse,
                    this::listCardErrorResponse);
            adapter.notifyDataSetChanged();
        } catch (Exception ex){
            Toast.makeText(getApplicationContext(), R.string.detected_error, Toast.LENGTH_SHORT).show();
            LogHelper.logException(TAG, ex, getApplicationContext());
        }
    }
}