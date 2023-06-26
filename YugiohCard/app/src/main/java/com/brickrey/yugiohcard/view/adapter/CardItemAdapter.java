package com.brickrey.yugiohcard.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.brickrey.yugiohcard.R;
import com.brickrey.yugiohcard.bo.CardInfoBO;
import com.brickrey.yugiohcard.service.DownloadImageTask;

import java.util.ArrayList;

public class CardItemAdapter extends ArrayAdapter<CardInfoBO> {

    static class ViewHolder {
        ImageView card_image, card_type_icon;
        TextView card_name, card_type_desc, card_atk_val, card_def_val;
        LinearLayout card_layout_atk, card_layout_def;
    }

    public CardItemAdapter(@NonNull Context context, ArrayList<CardInfoBO> data) {
        super(context, 0, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.card_item, parent, false);
            viewHolder = initializeViewHolder(convertView);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        populateView(getItem(position), viewHolder);
        return convertView;
    }

    private ViewHolder initializeViewHolder(View convertView){
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.card_image = convertView.findViewById(R.id.card_image);
        viewHolder.card_type_icon = convertView.findViewById(R.id.card_type_icon);
        viewHolder.card_name = convertView.findViewById(R.id.card_name);
        viewHolder.card_type_desc = convertView.findViewById(R.id.card_type_desc);
        viewHolder.card_atk_val = convertView.findViewById(R.id.card_atk_val);
        viewHolder.card_def_val = convertView.findViewById(R.id.card_def_val);
        viewHolder.card_layout_atk = convertView.findViewById(R.id.card_layout_atk);
        viewHolder.card_layout_def = convertView.findViewById(R.id.card_layout_def);
        convertView.setTag(viewHolder);
        return viewHolder;
    }

    private void populateView(CardInfoBO item, ViewHolder viewHolder){

        if(item.getCard_images() != null && item.getCard_images().length > 0){
            new DownloadImageTask(viewHolder.card_image).execute(item.getCard_images()[0].getImage_url());
        }

        viewHolder.card_name.setText(item.getName());

        String resName = "@drawable/" + item.getType().toLowerCase().replace(" ","_").replace("-","_");
        @SuppressLint("DiscouragedApi")
        int imageResource = getContext().getResources().getIdentifier(resName, null, getContext().getPackageName());
        Drawable imageDra = ContextCompat.getDrawable(getContext(), imageResource);
        viewHolder.card_type_icon.setImageDrawable(imageDra);
        viewHolder.card_type_desc.setText(item.getType());
        if(item.getAttribute() != null && !item.getAttribute().isEmpty()) {
            viewHolder.card_layout_atk.setVisibility(View.VISIBLE);
            viewHolder.card_layout_def.setVisibility(View.VISIBLE);
            viewHolder.card_atk_val.setText(String.valueOf(item.getAtk()));
            viewHolder.card_def_val.setText(String.valueOf(item.getDef()));
        }else{
            viewHolder.card_layout_atk.setVisibility(View.GONE);
            viewHolder.card_layout_def.setVisibility(View.GONE);
        }

    }
}
