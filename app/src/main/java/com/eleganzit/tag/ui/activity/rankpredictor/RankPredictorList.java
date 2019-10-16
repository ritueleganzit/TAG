package com.eleganzit.tag.ui.activity.rankpredictor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.eleganzit.tag.R;

public class RankPredictorList extends AppCompatActivity {

    RecyclerView rc_top_colleges;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_predictor_list);
        rc_top_colleges=findViewById(R.id.rc_top_colleges);
rc_top_colleges.setAdapter(new RankPredictorAdapter(RankPredictorList.this));
findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        finish();
    }
});

    }
}
