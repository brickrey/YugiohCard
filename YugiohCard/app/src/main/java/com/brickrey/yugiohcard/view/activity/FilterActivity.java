package com.brickrey.yugiohcard.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.brickrey.yugiohcard.GlobalData;
import com.brickrey.yugiohcard.R;
import com.brickrey.yugiohcard.helper.LogHelper;

import java.util.ArrayList;

public class FilterActivity extends AppCompatActivity {

    public final static String TAG = FilterActivity.class.getCanonicalName();

    SearchView searchView;
    ListView listView;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        list = new ArrayList<>();
        list.add( "No Filter");
        GlobalData.getInstance().getArchetypeList().forEach(archetypeBO -> list.add(archetypeBO.getArchetype_name()));

        searchView = findViewById(R.id.filterSearchView);
        listView = findViewById(R.id.filterListView);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            GlobalData.getInstance().setArchetypeFilter(i == 0 ? "" : adapter.getItem(i));
            Intent intent = new Intent();
            intent.putExtra("archetype", GlobalData.getInstance().getArchetypeFilter());
            setResult(RESULT_OK, intent);
            finish();
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        int pos;
        try {
            if(!GlobalData.getInstance().getArchetypeFilter().isEmpty())
                pos = adapter.getPosition(GlobalData.getInstance().getArchetypeFilter());
            else
                pos = adapter.getPosition("No Filter");
            listView.setSelection(pos);
            listView.setItemChecked(pos, true);
            View v = listView.getChildAt(pos);
            v.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white_sel));
        }catch(Exception e){
            LogHelper.logException(TAG, e, getApplicationContext());
        }
    }
}