package com.eleganzit.tag.ui.activity.school;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.R;
import com.eleganzit.tag.utils.HideKeyBoard;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplyPersonalInfoActivity extends AppCompatActivity {
    TextView next;
    LinearLayout lintouch;
    EditText father_name,father_occupation,mother_name,mother_occupation,ed_email,mobilenum,permanent_address,temporary_address,for_NRI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_personal_info);

        lintouch=findViewById(R.id.lintouch);
        father_name=findViewById(R.id.father_name);
        next=findViewById(R.id.next);
        father_occupation=findViewById(R.id.father_occupation);
        mother_name=findViewById(R.id.mother_name);
        ed_email=findViewById(R.id.ed_email);
        mobilenum=findViewById(R.id.mobilenum);
        permanent_address=findViewById(R.id.permanent_address);
        mother_occupation=findViewById(R.id.mother_occupation);
        for_NRI=findViewById(R.id.for_NRI);
        temporary_address=findViewById(R.id.temporary_address);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });

        HideKeyBoard.setupUI(lintouch,ApplyPersonalInfoActivity.this);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid())
                {
                    startActivity(new Intent(ApplyPersonalInfoActivity.this,ApplyEducationInfoActivity.class));
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                }
            }
        });
    }
    public boolean isValid() {
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(ed_email.getText().toString());


        if (father_name.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please Enter Father's Name", Toast.LENGTH_SHORT).show();

            father_name.requestFocus();

            return false;
        }else if (father_occupation.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please Enter Father's Occupation", Toast.LENGTH_SHORT).show();

            father_occupation.requestFocus();

            return false;
        }
        else if (mother_name.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please Enter Mother's Name", Toast.LENGTH_SHORT).show();

            mother_name.requestFocus();

            return false;
        } else if (mother_occupation.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please Mother's Occupation", Toast.LENGTH_SHORT).show();

            mother_occupation.requestFocus();

            return false;
        }
        else if (!matcher.matches()) {
            Toast.makeText(this, "Please enter a Valid e-mail id", Toast.LENGTH_SHORT).show();

            ed_email.requestFocus();
            return false;
        }
        else if (mobilenum.getText().toString().trim().equals("") || mobilenum.getText().toString().trim().length()<10) {

            Toast.makeText(this, "Phone number must contain atleast 10 digits", Toast.LENGTH_SHORT).show();


            mobilenum.requestFocus();

            return false;
        }

        else if (permanent_address.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please Enter Permanent Address", Toast.LENGTH_SHORT).show();

            permanent_address.requestFocus();

            return false;
        }else if (temporary_address.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please Enter Temporary Address", Toast.LENGTH_SHORT).show();

            temporary_address.requestFocus();

            return false;
        }



        return true;
    }
}
