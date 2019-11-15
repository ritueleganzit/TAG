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
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.R;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.AddEducationPreferanceResponse;
import com.eleganzit.tag.model.AddworkexpResponse;
import com.eleganzit.tag.model.addeducation.EducationDeleteResponse;
import com.eleganzit.tag.model.addwork.AddWorkExperience;
import com.eleganzit.tag.model.dropdowndata.DropDownListResponse;
import com.eleganzit.tag.model.dropdowndata.StreamList;
import com.eleganzit.tag.model.homefacility.FacilitiesResponse;
import com.eleganzit.tag.utils.UserLoggedInSession;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AddEducationPreference extends AppCompatActivity {
TextView submit;
    String user_id;
    ProgressDialog progressDialog;
    UserLoggedInSession userLoggedInSession;

    EditText mode_of_study,specialisation,course,stream;
    private ArrayList<String> streamname;
    private ArrayList<String> streamid;
  private ArrayList<String> coursename;
    private ArrayList<String> couseid;
private ArrayList<String> modename;
private ArrayList<String> specialization;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_education_preference);
        stream=findViewById(R.id.stream);
        submit=findViewById(R.id.submit);
        mode_of_study=findViewById(R.id.mode_of_study);
        specialisation=findViewById(R.id.specialisation);
        course=findViewById(R.id.course);

        userLoggedInSession=new UserLoggedInSession(AddEducationPreference.this);
        progressDialog = new ProgressDialog(AddEducationPreference.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        user_id=userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid())
                {
                    addEduPref();
                }
            }
        });

        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddEducationPreference.this);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddEducationPreference.this, android.R.layout.simple_list_item_1, coursename);
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        course.setText(""+coursename.get(which));
                        Toast.makeText(AddEducationPreference.this, ""+couseid.get(which), Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });   mode_of_study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddEducationPreference.this);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddEducationPreference.this, android.R.layout.simple_list_item_1, modename);
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mode_of_study.setText(""+modename.get(which));
                        Toast.makeText(AddEducationPreference.this, ""+modename.get(which), Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        getPref();
        stream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddEducationPreference.this);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddEducationPreference.this, android.R.layout.simple_list_item_1, streamname);
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        stream.setText(""+streamname.get(which));
                        Toast.makeText(AddEducationPreference.this, ""+streamid.get(which), Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });specialisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddEducationPreference.this);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddEducationPreference.this, android.R.layout.simple_list_item_1, specialization);
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        specialisation.setText(""+specialization.get(which));
                        Toast.makeText(AddEducationPreference.this, ""+specialization.get(which), Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void getPref(){
        progressDialog.show();
        streamid=new ArrayList<>();
        streamname=new ArrayList<>();
        modename=new ArrayList<>();
        specialization=new ArrayList<>();

        couseid=new ArrayList<>();
        coursename=new ArrayList<>();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
        Call<DropDownListResponse> call=myInterface.dropDownList();
        call.enqueue(new Callback<DropDownListResponse>() {
            @Override
            public void onResponse(Call<DropDownListResponse> call, Response<DropDownListResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful())
                {

                    if (response.body().getData().getStreamList()!=null)
                    {

                        for (int i=0;i<response.body().getData().getStreamList().size();i++)
                        {
                            streamid.add(""+response.body().getData().getStreamList().get(i).getSpecializationId());
                            streamname.add(response.body().getData().getStreamList().get(i).getSpecializationName());

                        }
                    } if (response.body().getData().getStudyMode()!=null)
                    {

                        for (int i=0;i<response.body().getData().getStreamList().size();i++)
                        {
                            modename.add(""+response.body().getData().getStudyMode().get(i).getModeName());

                        }
                    }if (response.body().getData().getCourseList()!=null)
                    {

                        for (int i=0;i<response.body().getData().getCourseList().size();i++)
                        {
                            couseid.add(""+response.body().getData().getCourseList().get(i).getCourceId());
                            coursename.add(response.body().getData().getCourseList().get(i).getCourseName());

                        }
                    }if (response.body().getData().getSpecializationFieldList()!=null)
                    {

                        for (int i=0;i<response.body().getData().getSpecializationFieldList().size();i++)
                        {
                            specialization.add(""+response.body().getData().getSpecializationFieldList().get(i).getSpeFieldName());

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<DropDownListResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(AddEducationPreference.this, "Server or Internet error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void addEduPref() {

        progressDialog.show();


        JsonObject paramObject = new JsonObject();
        paramObject.addProperty("user_id", user_id);

        JsonObject paramObject2=new JsonObject();
        paramObject2.addProperty("course",course.getText().toString());
        paramObject2.addProperty("stream",stream.getText().toString());
        paramObject2.addProperty("specialization",specialisation.getText().toString());
        paramObject2.addProperty("study_mode",mode_of_study.getText().toString());
        JsonArray jsonArray=new JsonArray();

        jsonArray.add(paramObject2);

        paramObject.add("preference_details", jsonArray);








        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://eleganzit.online/testhost/users/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface myInterface = retrofit.create(RetrofitInterface.class);
        Call<EducationDeleteResponse> call=myInterface.updatePreferences(paramObject);

        call.enqueue(new Callback<EducationDeleteResponse>() {
            @Override
            public void onResponse(Call<EducationDeleteResponse> call, Response<EducationDeleteResponse> response) {
                if (response.isSuccessful())
                {
                    if (response.isSuccessful())
                    {


                        Toast.makeText(AddEducationPreference.this, "Successfully Inserted", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<EducationDeleteResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(AddEducationPreference.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();
            }
        });



    }

    private boolean isValid() {
        if (stream.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please select education stream", Toast.LENGTH_SHORT).show();

            stream.requestFocus();

            return false;
        }

        else if (course.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please select course", Toast.LENGTH_SHORT).show();


            course.requestFocus();

            return false;
        }else if (mode_of_study.getText().toString().trim().equals("")) {

            Toast.makeText(this, "Please select mode of study", Toast.LENGTH_SHORT).show();


            mode_of_study.requestFocus();

            return false;
        }
        return true;
    }
}
