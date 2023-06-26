package com.brickrey.yugiohcard;

import com.brickrey.yugiohcard.bo.ArchetypeBO;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {

    //<editor-fold defaultState="collapsed" desc="DriverApp Global Data Implementation"
    private static final GlobalData instance = new GlobalData();

    public static GlobalData getInstance() {
        return instance;
    }
    //</editor-fold>

    private int rowsOnSearchView;
    private int offsetOnSearchView;
    private String archetypeFilter;
    private List<ArchetypeBO> archetypeList;

    public GlobalData(){
        rowsOnSearchView = 0;
        offsetOnSearchView = 0;
        archetypeFilter = "";
        archetypeList = new ArrayList<>();
    }

    public int getRowsOnSearchView() {
        return rowsOnSearchView;
    }

    public void setRowsOnSearchView(int rowsOnSearchView) {
        this.rowsOnSearchView = rowsOnSearchView;
    }

    public int getOffsetOnSearchView() {
        return offsetOnSearchView;
    }

    public void setOffsetOnSearchView(int offsetOnSearchView) {
        this.offsetOnSearchView = offsetOnSearchView;
    }

    public String getArchetypeFilter() {
        return archetypeFilter;
    }

    public void setArchetypeFilter(String archetypeFilter) {
        this.archetypeFilter = archetypeFilter;
    }

    public List<ArchetypeBO> getArchetypeList() {
        return archetypeList;
    }

    public void setArchetypeList(List<ArchetypeBO> archetypeList) {
        this.archetypeList = archetypeList;
    }
}
