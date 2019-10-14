package com.eleganzit.tag.ui.activity.school;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.R;

public class ApplyEducationInfoActivity extends AppCompatActivity {

    TextView applysubmit;
    EditText ednameofdegree,edyearofdegree,edmarksobtained,edremarks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_education_info);
        ednameofdegree=findViewById(R.id.ednameofdegree);
        edyearofdegree=findViewById(R.id.edyearofdegree);
        applysubmit=findViewById(R.id.applysubmit);
        edmarksobtained=findViewById(R.id.edmarksobtained);
        edremarks=findViewById(R.id.edremarks);

        applysubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid())
                {

                }
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

    public boolean isValid() {

        if (ednameofdegree.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please Select Education Detail", Toast.LENGTH_SHORT).show();

            ednameofdegree.requestFocus();

            return false;
        }else if (edyearofdegree.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please Enter Year of Passing", Toast.LENGTH_SHORT).show();

            edyearofdegree.requestFocus();

            return false;
        }
        else if (edmarksobtained.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please Enter Marks Obtained", Toast.LENGTH_SHORT).show();

            edmarksobtained.requestFocus();

            return false;
        }


        return true;
    }

}
