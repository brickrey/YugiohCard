package com.brickrey.yugiohcard.bo;

import org.json.JSONException;
import org.json.JSONObject;

public class CardMetaBO implements Comparable{

    private int current_rows;
    private int total_rows;
    private int rows_remaining;
    private int total_pages;
    private int pages_remaining;
    private String previous_page;
    private int previous_page_offset;
    private String next_page;
    private int next_page_offset;

    public CardMetaBO (){
        current_rows = 0;
        total_rows = 0;
        rows_remaining = 0;
        total_pages = 0;
        pages_remaining = 0;
        previous_page = "";
        previous_page_offset = 0;
        next_page = "";
        next_page_offset = 0;
    }

    public CardMetaBO(JSONObject jsonObject) throws JSONException{
        this();
        if(jsonObject != null){
            if(jsonObject.has(JSON_Meta_CurrentRows)) current_rows = jsonObject.getInt(JSON_Meta_CurrentRows);
            if(jsonObject.has(JSON_Meta_TotalRows)) total_rows = jsonObject.getInt(JSON_Meta_TotalRows);
            if(jsonObject.has(JSON_Meta_RowsRemaining)) rows_remaining = jsonObject.getInt(JSON_Meta_RowsRemaining);
            if(jsonObject.has(JSON_Meta_TotalPages)) total_pages = jsonObject.getInt(JSON_Meta_TotalPages);
            if(jsonObject.has(JSON_Meta_PagesRemaining)) pages_remaining = jsonObject.getInt(JSON_Meta_PagesRemaining);
            if(jsonObject.has(JSON_Meta_PreviousPage)) previous_page = jsonObject.getString(JSON_Meta_PreviousPage);
            if(jsonObject.has(JSON_Meta_PrevPagOffset)) previous_page_offset = jsonObject.getInt(JSON_Meta_PrevPagOffset);
            if(jsonObject.has(JSON_Meta_NextPage)) next_page = jsonObject.getString(JSON_Meta_NextPage);
            if(jsonObject.has(JSON_Meta_NextPagOffset)) next_page_offset = jsonObject.getInt(JSON_Meta_NextPagOffset);
        }
    }

    public int getCurrent_rows() {
        return current_rows;
    }

    public void setCurrent_rows(int current_rows) {
        this.current_rows = current_rows;
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getRows_remaining() {
        return rows_remaining;
    }

    public void setRows_remaining(int rows_remaining) {
        this.rows_remaining = rows_remaining;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getPages_remaining() {
        return pages_remaining;
    }

    public void setPages_remaining(int pages_remaining) {
        this.pages_remaining = pages_remaining;
    }

    public String getPrevious_page() {
        return previous_page;
    }

    public void setPrevious_page(String previous_page) {
        this.previous_page = previous_page;
    }

    public int getPrevious_page_offset() {
        return previous_page_offset;
    }

    public void setPrevious_page_offset(int previous_page_offset) {
        this.previous_page_offset = previous_page_offset;
    }

    public String getNext_page() {
        return next_page;
    }

    public void setNext_page(String next_page) {
        this.next_page = next_page;
    }

    public int getNext_page_offset() {
        return next_page_offset;
    }

    public void setNext_page_offset(int next_page_offset) {
        this.next_page_offset = next_page_offset;
    }

    @Override
    public String toString() {
        return "{" +
                "\"current_rows\": " + current_rows +
                ", \"total_rows\": " + total_rows +
                ", \"rows_remaining\": " + rows_remaining +
                ", \"total_pages\": " + total_pages +
                ", \"pages_remaining\": " + pages_remaining +
                ", \"previous_page\": \"" + previous_page + '\"' +
                ", \"previous_page_offset\": " + previous_page_offset +
                ", \"next_page\": \"" + next_page + '\"' +
                ", \"next_page_offset\": " + next_page_offset +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return this == o ? 0 : 1;
    }

    public static String JSON_Meta_CurrentRows = "current_rows";
    public static String JSON_Meta_TotalRows = "total_rows";
    public static String JSON_Meta_RowsRemaining = "rows_remaining";
    public static String JSON_Meta_TotalPages = "total_pages";
    public static String JSON_Meta_PagesRemaining = "pages_remaining";
    public static String JSON_Meta_PreviousPage = "previous_page";
    public static String JSON_Meta_PrevPagOffset = "previous_page_offset";
    public static String JSON_Meta_NextPage = "next_page";
    public static String JSON_Meta_NextPagOffset = "next_page_offset";
}
