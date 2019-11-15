package com.eleganzit.tag.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.R;
import com.eleganzit.tag.SignUpActivity;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.AddEducationDataResponse;
import com.eleganzit.tag.model.accountsettings.PasswordUpdateResponse;
import com.eleganzit.tag.model.addeducation.EducationUpdateResponse;
import com.eleganzit.tag.utils.UserLoggedInSession;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AddEducationActivity extends AppCompatActivity {
    private static final int MAX_YEAR = 2099;
    EditText year,marks,subject,board,cource_year,school_name,cource_level;
    EditText tenth_school_name,tenth_year,tenth_board,tenth_marks;
    EditText twelth_school_name,twelth_year,twelth_stream,twelth_board,twelth_marks;
    EditText ug_school_name,ug_degree_uni,ug_year,ug_name,ug_specialization,ug_marks;
    EditText pg_school_name,pg_degree_uni,pg_year,pg_name,pg_specialization,pg_marks;
    String user_id;
    TextView submit;
    ArrayList<String> years = new ArrayList<String>();
    ArrayList<String> courceLevel = new ArrayList<String>();
    ProgressDialog progressDialog;


    LinearLayout lin_tenth,lin_twelth,lin_ug,lin_pg;
    UserLoggedInSession userLoggedInSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_education);
courceLevel.add("10");
courceLevel.add("12");
courceLevel.add("UG");
courceLevel.add("PG");
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        lin_pg=findViewById(R.id.lin_pg);
        ug_year=findViewById(R.id.ug_year);
        pg_year=findViewById(R.id.pg_year);
        tenth_school_name=findViewById(R.id.tenth_school_name);

        tenth_year=findViewById(R.id.tenth_year);
        tenth_board=findViewById(R.id.tenth_board);

        tenth_marks=findViewById(R.id.tenth_marks);
        twelth_marks=findViewById(R.id.twelth_marks);
        twelth_school_name=findViewById(R.id.twelth_school_name);
        twelth_year=findViewById(R.id.twelth_year);
        twelth_board=findViewById(R.id.twelth_board);
        twelth_stream=findViewById(R.id.twelth_stream);
        lin_tenth=findViewById(R.id.lin_tenth);
        lin_ug=findViewById(R.id.lin_ug);
        lin_twelth=findViewById(R.id.lin_twelth);

        submit=findViewById(R.id.submit);
        cource_level=findViewById(R.id.cource_level);
        ug_school_name=findViewById(R.id.ug_school_name);
        ug_degree_uni=findViewById(R.id.ug_degree_uni);
        ug_marks=findViewById(R.id.ug_marks);
        ug_name=findViewById(R.id.ug_name);
        ug_specialization=findViewById(R.id.ug_specialization);ug_school_name=findViewById(R.id.ug_school_name);
        ug_degree_uni=findViewById(R.id.ug_degree_uni);
        ug_marks=findViewById(R.id.ug_marks);
        ug_name=findViewById(R.id.ug_name);
        pg_specialization=findViewById(R.id.pg_specialization);
        pg_school_name=findViewById(R.id.pg_school_name);
        pg_degree_uni=findViewById(R.id.pg_degree_uni);
        pg_marks=findViewById(R.id.pg_marks);
        pg_name=findViewById(R.id.pg_name);
        pg_specialization=findViewById(R.id.pg_specialization);
        userLoggedInSession=new UserLoggedInSession(AddEducationActivity.this);
        user_id=userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID);
        Log.d("ciuyn",""+userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID));
        progressDialog = new ProgressDialog(AddEducationActivity.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1900; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }



        tenth_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.reverse(years);
                AlertDialog.Builder builder = new AlertDialog.Builder(AddEducationActivity.this);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddEducationActivity.this, android.R.layout.simple_list_item_1, years);
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        tenth_year.setText(""+years.get(which));
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

 ug_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.reverse(years);
                AlertDialog.Builder builder = new AlertDialog.Builder(AddEducationActivity.this);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddEducationActivity.this, android.R.layout.simple_list_item_1, years);
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ug_year.setText(""+years.get(which));
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

 pg_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.reverse(years);
                AlertDialog.Builder builder = new AlertDialog.Builder(AddEducationActivity.this);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddEducationActivity.this, android.R.layout.simple_list_item_1, years);
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        pg_year.setText(""+years.get(which));
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });



        cource_level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddEducationActivity.this);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddEducationActivity.this, android.R.layout.simple_list_item_1, courceLevel);
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        cource_level.setText(""+courceLevel.get(which));

                        if (cource_level.getText().toString().equalsIgnoreCase("10"))
                        {
                            lin_tenth.setVisibility(View.VISIBLE);
                            lin_twelth.setVisibility(View.GONE);
                            lin_ug.setVisibility(View.GONE);
                            lin_pg.setVisibility(View.GONE);
                        }
                        if (cource_level.getText().toString().equalsIgnoreCase("12"))
                        {
                            lin_twelth.setVisibility(View.VISIBLE);
                            lin_tenth.setVisibility(View.GONE);
                            lin_ug.setVisibility(View.GONE);
                            lin_pg.setVisibility(View.GONE);
                        }
                        if (cource_level.getText().toString().equalsIgnoreCase("UG"))
                        {
                            lin_ug.setVisibility(View.VISIBLE);
                            lin_tenth.setVisibility(View.GONE);
                            lin_twelth.setVisibility(View.GONE);

                            lin_pg.setVisibility(View.GONE);

                        }if (cource_level.getText().toString().equalsIgnoreCase("PG"))
                        {
                            lin_pg.setVisibility(View.VISIBLE);
                            lin_ug.setVisibility(View.GONE);
                            lin_tenth.setVisibility(View.GONE);
                            lin_twelth.setVisibility(View.GONE);


                        }
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cource_level.getText().toString().equalsIgnoreCase("10")) {
if (isValid10())
{
    addeducation();
}



                }
                 if (cource_level.getText().toString().equalsIgnoreCase("12")) {
if (isValid12())
{
    addeducation();
}



                } if (cource_level.getText().toString().equalsIgnoreCase("UG")) {

                    if (isValidUG())
                    {
                        addeducation();
                    }


                } if (cource_level.getText().toString().equalsIgnoreCase("PG")) {

                    if (isValidPG())
                    {
                        addeducation();
                    }
                        //addeducation10();

                }


            }
        });
        twelth_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Collections.reverse(years);
                AlertDialog.Builder builder = new AlertDialog.Builder(AddEducationActivity.this);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddEducationActivity.this, android.R.layout.simple_list_item_1, years);
               builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                       twelth_year.setText(""+years.get(which));
                   }
               });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private boolean isValidPG() {
        if (cource_level.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please select course level", Toast.LENGTH_SHORT).show();

            //  name.requestFocus();

            return false;
        }

        else if (pg_school_name.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please enter university name", Toast.LENGTH_SHORT).show();


            pg_school_name.requestFocus();

            return false;
        }else if (pg_degree_uni.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please enter degree name", Toast.LENGTH_SHORT).show();
            pg_degree_uni.requestFocus();


            return false;
        }else if (pg_year.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please select year", Toast.LENGTH_SHORT).show();


            pg_year.requestFocus();

            return false;
        }
        else if (pg_name.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please enter college name", Toast.LENGTH_SHORT).show();


            pg_name.requestFocus();

            return false;
        }
        else if (pg_marks.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please enter marks", Toast.LENGTH_SHORT).show();


            pg_marks.requestFocus();

            return false;
        }



        return true;
        
    }

    private boolean isValidUG() {
        if (cource_level.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please select course level", Toast.LENGTH_SHORT).show();

            //  name.requestFocus();

            return false;
        }

        else if (ug_school_name.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please enter university name", Toast.LENGTH_SHORT).show();


            ug_school_name.requestFocus();

            return false;
        }else if (ug_degree_uni.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please enter degree name", Toast.LENGTH_SHORT).show();
            ug_degree_uni.requestFocus();


            return false;
        }else if (ug_year.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please select year", Toast.LENGTH_SHORT).show();


            ug_year.requestFocus();

            return false;
        }
        else if (ug_name.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please enter college name", Toast.LENGTH_SHORT).show();


            ug_name.requestFocus();

            return false;
        }
else if (ug_marks.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please enter marks", Toast.LENGTH_SHORT).show();


            ug_marks.requestFocus();

            return false;
        }



        return true;

    }

    private void addeducation()
    {
        submit.setEnabled(false);
        submit.setClickable(false);
        progressDialog.show();
        JsonObject paramObject = new JsonObject();
        paramObject.addProperty("user_id", userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID));




        JsonObject paramObject2=new JsonObject();
        if (cource_level.getText().toString().equalsIgnoreCase("10"))
        {
            paramObject2.addProperty("course_level",cource_level.getText().toString());
            paramObject2.addProperty("type","school");
            paramObject2.addProperty("school_name",tenth_school_name.getText().toString());
            paramObject2.addProperty("completion_year",tenth_year.getText().toString());
            paramObject2.addProperty("board",tenth_board.getText().toString());
            paramObject2.addProperty("marks",tenth_marks.getText().toString());
            JsonArray jsonArray=new JsonArray();

            jsonArray.add(paramObject2);

            paramObject.add("edu_details", jsonArray);

        }
        if (cource_level.getText().toString().equalsIgnoreCase("12"))
        {
            paramObject2.addProperty("course_level",cource_level.getText().toString());
            paramObject2.addProperty("type","school");
            paramObject2.addProperty("school_name",twelth_school_name.getText().toString());
            paramObject2.addProperty("completion_year",twelth_year.getText().toString());
            paramObject2.addProperty("board",twelth_board.getText().toString());
            paramObject2.addProperty("subject",twelth_stream.getText().toString());
            paramObject2.addProperty("marks",twelth_marks.getText().toString());
            JsonArray jsonArray=new JsonArray();

            jsonArray.add(paramObject2);

            paramObject.add("edu_details", jsonArray);

        }if (cource_level.getText().toString().equalsIgnoreCase("UG"))
        {
            paramObject2.addProperty("course_level",cource_level.getText().toString());
            paramObject2.addProperty("type","college");
            paramObject2.addProperty("college_name",ug_name.getText().toString());
            paramObject2.addProperty("completion_year",ug_year.getText().toString());
            paramObject2.addProperty("degree",ug_degree_uni.getText().toString());
            paramObject2.addProperty("specialization",ug_specialization.getText().toString());
            paramObject2.addProperty("university",ug_school_name.getText().toString());
            paramObject2.addProperty("marks",ug_marks.getText().toString());
            JsonArray jsonArray=new JsonArray();

            jsonArray.add(paramObject2);

            paramObject.add("edu_details", jsonArray);

        }
        if (cource_level.getText().toString().equalsIgnoreCase("PG"))
        {
            paramObject2.addProperty("course_level",cource_level.getText().toString());
            paramObject2.addProperty("type","college");
            paramObject2.addProperty("college_name",pg_name.getText().toString());
            paramObject2.addProperty("completion_year",pg_year.getText().toString());
            paramObject2.addProperty("degree",pg_degree_uni.getText().toString());
            paramObject2.addProperty("specialization",pg_specialization.getText().toString());
            paramObject2.addProperty("university",pg_school_name.getText().toString());
            paramObject2.addProperty("marks",pg_marks.getText().toString());
            JsonArray jsonArray=new JsonArray();

            jsonArray.add(paramObject2);

            paramObject.add("edu_details", jsonArray);

        }




        Log.d("dataaaa","-"+paramObject.toString());


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://eleganzit.online/testhost/users/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface myInterface = retrofit.create(RetrofitInterface.class);
        Call<EducationUpdateResponse> call=myInterface.educationUpdateResponse(paramObject);
        call.enqueue(new Callback<EducationUpdateResponse>() {
            @Override
            public void onResponse(Call<EducationUpdateResponse> call, Response<EducationUpdateResponse> response) {
                progressDialog.dismiss();
                submit.setEnabled(true);
                submit.setClickable(true);
                if (response.isSuccessful())
                {
                    if (response.body().getStatus().toString().equalsIgnoreCase("1"))
                    {
                        Toast.makeText(AddEducationActivity.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else
                    {
                        Toast.makeText(AddEducationActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<EducationUpdateResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(AddEducationActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();
                submit.setEnabled(true);
                submit.setClickable(true);
            }
        });


    }



    private boolean isValid10() {

        if (cource_level.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please select course level", Toast.LENGTH_SHORT).show();

          //  name.requestFocus();

            return false;
        }

        else if (tenth_school_name.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please enter school name", Toast.LENGTH_SHORT).show();


            tenth_school_name.requestFocus();

            return false;
        }else if (tenth_year.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please select year", Toast.LENGTH_SHORT).show();



            return false;
        }else if (tenth_board.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please enter board", Toast.LENGTH_SHORT).show();


            tenth_board.requestFocus();

            return false;
        }else if (tenth_marks.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please enter marks", Toast.LENGTH_SHORT).show();


            tenth_marks.requestFocus();

            return false;
        }
        return true;
    }

    private boolean isValid12() {

        if (cource_level.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please select course level", Toast.LENGTH_SHORT).show();

            //  name.requestFocus();

            return false;
        }

        else if (twelth_school_name.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please enter school name", Toast.LENGTH_SHORT).show();


            twelth_school_name.requestFocus();

            return false;
        }else if (twelth_year.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please select year", Toast.LENGTH_SHORT).show();



            return false;
        }else if (twelth_board.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please enter board", Toast.LENGTH_SHORT).show();


            twelth_board.requestFocus();

            return false;
        }
        else if (twelth_stream.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please enter stream", Toast.LENGTH_SHORT).show();


            twelth_stream.requestFocus();

            return false;
        }


        else if (twelth_marks.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please enter marks", Toast.LENGTH_SHORT).show();


            twelth_marks.requestFocus();

            return false;
        }
        return true;
    }
}
