package com.brickrey.yugiohcard.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.brickrey.yugiohcard.R;
import com.brickrey.yugiohcard.bo.CardInfoBO;
import com.brickrey.yugiohcard.helper.LogHelper;
import com.brickrey.yugiohcard.service.DownloadImageTask;

public class CardDetailActivity extends AppCompatActivity {

    public final static String TAG = CardDetailActivity.class.getCanonicalName();

    private CardInfoBO cardInfoBO;

    @SuppressLint("DiscouragedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);

        cardInfoBO = getIntent().getParcelableExtra("card");

        ((TextView) findViewById(R.id.card_detail_name_lab)).setText(cardInfoBO.getName());

        String resName = "@drawable/" + cardInfoBO.getType().toLowerCase().replace(" ","_").replace("-","_");
        int imageResource = getApplicationContext().getResources().getIdentifier(resName, null, getApplication().getPackageName());
        Drawable imageDra = ContextCompat.getDrawable(getApplicationContext(), imageResource);
        ((ImageView) findViewById(R.id.card_detail_type_ico)).setImageDrawable(imageDra);
        ((TextView) findViewById(R.id.card_detail_type_des)).setText(cardInfoBO.getType());

        resName = "@drawable/" + cardInfoBO.getRace().toLowerCase().replace(" ","_").replace("-","_");
        imageResource = getApplicationContext().getResources().getIdentifier(resName, null, getApplication().getPackageName());
        imageDra = ContextCompat.getDrawable(getApplicationContext(), imageResource);
        ((ImageView) findViewById(R.id.card_detail_race_ico)).setImageDrawable(imageDra);
        ((TextView) findViewById(R.id.card_detail_race_des)).setText(cardInfoBO.getRace());

        if(cardInfoBO.getAttribute() != null && !cardInfoBO.getAttribute().isEmpty()) {
            findViewById(R.id.card_detail_attr_lay).setVisibility(View.VISIBLE);
            findViewById(R.id.card_detail_lev_lay).setVisibility(View.VISIBLE);
            findViewById(R.id.card_detail_atck_lay).setVisibility(View.VISIBLE);
            findViewById(R.id.card_detail_def_lay).setVisibility(View.VISIBLE);

            resName = "@drawable/" + cardInfoBO.getAttribute().toLowerCase().replace(" ", "_").replace("-", "_");
            imageResource = getApplicationContext().getResources().getIdentifier(resName, null, getApplication().getPackageName());
            imageDra = ContextCompat.getDrawable(getApplicationContext(), imageResource);
            ((ImageView) findViewById(R.id.card_detail_attr_ico)).setImageDrawable(imageDra);
            ((TextView) findViewById(R.id.card_detail_attr_des)).setText(cardInfoBO.getAttribute());

            ((TextView) findViewById(R.id.card_detail_lev_des)).setText(String.valueOf(cardInfoBO.getLevel()));
            ((TextView) findViewById(R.id.card_detail_atck_des)).setText(String.valueOf(cardInfoBO.getAtk()));
            ((TextView) findViewById(R.id.card_detail_def_des)).setText(String.valueOf(cardInfoBO.getDef()));
        }else{
            findViewById(R.id.card_detail_attr_lay).setVisibility(View.GONE);
            findViewById(R.id.card_detail_lev_lay).setVisibility(View.GONE);
            findViewById(R.id.card_detail_atck_lay).setVisibility(View.GONE);
            findViewById(R.id.card_detail_def_lay).setVisibility(View.GONE);
        }

        if(cardInfoBO.getArchetype() != null && !cardInfoBO.getArchetype().isEmpty()) {
            findViewById(R.id.card_detail_arch_lay).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.card_detail_arch_des)).setText(cardInfoBO.getArchetype());
        }else{
            findViewById(R.id.card_detail_arch_lay).setVisibility(View.GONE);
        }
        ((TextView) findViewById(R.id.card_detail_desc_des)).setText(cardInfoBO.getDesc());

        if(cardInfoBO.getCard_images() != null && cardInfoBO.getCard_images().length > 0){
            new DownloadImageTask(findViewById(R.id.card_detail_img)).execute(cardInfoBO.getCard_images()[0].getImage_url());
        }
        LogHelper.log(TAG, cardInfoBO.toString(), 1, getApplicationContext());
    }
}