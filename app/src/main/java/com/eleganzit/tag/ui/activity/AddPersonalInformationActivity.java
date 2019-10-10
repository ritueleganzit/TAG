package com.eleganzit.tag.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddPersonalInformationActivity extends AppCompatActivity {

    TextView next;
    EditText for_NRI,temporary_address,permanent_address,mobile,mother_occupation,mother_name,email_id,father_occupation,father_name;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_personal_information);
        for_NRI=findViewById(R.id.for_NRI);
        next=findViewById(R.id.next);
        sharedPreferences=getSharedPreferences("dataapply",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        temporary_address=findViewById(R.id.temporary_address);
        permanent_address=findViewById(R.id.permanent_address);
        mobile=findViewById(R.id.mobile);
        mother_occupation=findViewById(R.id.mother_occupation);
        mother_name=findViewById(R.id.mother_name);
        email_id=findViewById(R.id.email_id);
        father_occupation=findViewById(R.id.father_occupation);
        father_name=findViewById(R.id.father_name);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {

                    editor.putString("father_name", father_name.getText().toString())   ;
                    editor .putString("father_occupation", father_occupation.getText().toString());
                    editor  .putString("mother_name", mother_name.getText().toString());
                    editor .putString("mother_occupation", mother_occupation.getText().toString());
                    editor .putString("email_id", email_id.getText().toString());
                    editor .putString("mobile", mobile.getText().toString());
                    editor  .putString("permanent_address", permanent_address.getText().toString());
                    editor .putString("temporary_address", temporary_address.getText().toString());
                    editor .putString("for_NRI", for_NRI.getText().toString());
                    editor.commit();
                    startActivity(new Intent(AddPersonalInformationActivity.this, AddEducationInformationActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email_id.getText().toString());


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

            email_id.requestFocus();
            return false;
        }
        else if (mobile.getText().toString().trim().equals("") || mobile.getText().toString().trim().length()<10) {

            Toast.makeText(this, "Phone number must contain atleast 10 digits", Toast.LENGTH_SHORT).show();


            mobile.requestFocus();

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
