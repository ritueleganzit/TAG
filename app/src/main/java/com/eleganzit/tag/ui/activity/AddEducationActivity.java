package com.eleganzit.tag.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.R;
import com.eleganzit.tag.SignUpActivity;
import com.eleganzit.tag.utils.UserLoggedInSession;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddEducationActivity extends AppCompatActivity {
    private static final int MAX_YEAR = 2099;
    EditText year,marks,subject,board,cource_year,school_name,cource_level;
    String user_id;
    TextView submit;
    ArrayList<String> years = new ArrayList<String>();
    ProgressDialog progressDialog;

    UserLoggedInSession userLoggedInSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_education);
        year=findViewById(R.id.year);
        marks=findViewById(R.id.marks);
        subject=findViewById(R.id.subject);
        board=findViewById(R.id.board);
        school_name=findViewById(R.id.school_name);
        submit=findViewById(R.id.submit);
        cource_level=findViewById(R.id.cource_level);
        userLoggedInSession=new UserLoggedInSession(AddEducationActivity.this);
        user_id=userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID);
        progressDialog = new ProgressDialog(AddEducationActivity.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
cource_level.setText("Bachelor");
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1900; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid())
                {
                    Toast.makeText(AddEducationActivity.this, "valid", Toast.LENGTH_SHORT).show();
                }
            }
        });
        year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Collections.reverse(years);
                AlertDialog.Builder builder = new AlertDialog.Builder(AddEducationActivity.this);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddEducationActivity.this, android.R.layout.simple_list_item_1, years);
               builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                       year.setText(""+years.get(which));
                   }
               });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private boolean isValid() {

        if (cource_level.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please select course level", Toast.LENGTH_SHORT).show();

          //  name.requestFocus();

            return false;
        }

        else if (school_name.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please enter school name", Toast.LENGTH_SHORT).show();


            school_name.requestFocus();

            return false;
        }else if (year.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please select year", Toast.LENGTH_SHORT).show();



            return false;
        }else if (board.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please enter board", Toast.LENGTH_SHORT).show();


            board.requestFocus();

            return false;
        }else if (subject.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please enter subject", Toast.LENGTH_SHORT).show();


            subject.requestFocus();

            return false;
        }else if (marks.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please enter marks", Toast.LENGTH_SHORT).show();


            marks.requestFocus();

            return false;
        }
        return true;
    }
}