package com.eleganzit.tag.ui.activity.rankpredictor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.eleganzit.tag.R;

public class RankPredictorSearchActivity extends AppCompatActivity {

    TextView predictrank;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_predictor_search);
        predictrank=findViewById(R.id.predictrank);
        predictrank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
startActivity(new Intent(RankPredictorSearchActivity.this,RankPredictorList.class));
overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });
    }
}
