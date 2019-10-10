package com.eleganzit.tag.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.HomeActivity;
import com.eleganzit.tag.R;
import com.eleganzit.tag.api.RetrofitInterface;
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

public class AddEducationInformationActivity extends AppCompatActivity {
    ArrayList numdata=new ArrayList();
    ArrayList numdata2=new ArrayList();
    RecyclerView rc_edu,rc_competitive;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView applysubmit;
    ProgressDialog progressDialog;

    UserLoggedInSession userLoggedInSession;
    TextView addeducation,addmorecompetitive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_education_information);
        sharedPreferences=getSharedPreferences("dataapply",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        applysubmit=findViewById(R.id.applysubmit);
        rc_edu=findViewById(R.id.rc_edu);
        rc_competitive=findViewById(R.id.rc_competitive);
        addeducation=findViewById(R.id.addeducation);
        userLoggedInSession=new UserLoggedInSession(AddEducationInformationActivity.this);
        addmorecompetitive=findViewById(R.id.addmorecompetitive);
        applysubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });
        addeducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numdata.size()<=1)
                {
                    numdata.add("");
                    rc_edu.setAdapter(new AddCourseAdapter());
                    if (numdata.size()>1)
                    {
                        addeducation.setVisibility(View.GONE);
                    }
                }
                else {
                    Toast.makeText(AddEducationInformationActivity.this, "Max 3 data", Toast.LENGTH_SHORT).show();

                }
            }
        });addmorecompetitive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numdata2.size()<=1)
                {
                    numdata2.add("");
                    rc_competitive.setAdapter(new AddCompetitiveAdapter());
                    if (numdata2.size()>1)
                    {
                        addmorecompetitive.setVisibility(View.GONE);
                    }
                }
                else {
                    Toast.makeText(AddEducationInformationActivity.this, "Max 3 data", Toast.LENGTH_SHORT).show();

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

    public class AddCourseAdapter extends RecyclerView.Adapter<AddCourseAdapter.MyViewHolder> {
        public AddCourseAdapter() {
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.addeducation,viewGroup,false);
            AddCourseAdapter.MyViewHolder myViewHolder=new AddCourseAdapter.MyViewHolder(v);

            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull AddCourseAdapter.MyViewHolder myViewHolder, final int i) {

            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numdata.remove(i);
                    notifyDataSetChanged();
                    addeducation.setVisibility(View.VISIBLE);
                }
            });
        }

        @Override
        public int getItemCount() {
            return numdata.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {


            public MyViewHolder(@NonNull View itemView) {
                super(itemView);


            }
        }
    }


    public class AddCompetitiveAdapter extends RecyclerView.Adapter<AddCompetitiveAdapter.MyViewHolder> {
        public AddCompetitiveAdapter() {
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.addcompetitive,viewGroup,false);
            AddCompetitiveAdapter.MyViewHolder myViewHolder=new AddCompetitiveAdapter.MyViewHolder(v);

            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull AddCompetitiveAdapter.MyViewHolder myViewHolder, final int i) {

            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numdata2.remove(i);
                    notifyDataSetChanged();
                    addmorecompetitive.setVisibility(View.VISIBLE);

                }
            });
        }

        @Override
        public int getItemCount() {
            return numdata2.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {


            public MyViewHolder(@NonNull View itemView) {
                super(itemView);


            }
        }
    }

    public void addData()
    {

        JsonObject paramObject = new JsonObject();
        paramObject.addProperty("user_id", userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID));
        paramObject.addProperty("college_id", ""+sharedPreferences.getString("college_id","4"));
        paramObject.addProperty("course_list_id", 3);
        paramObject.addProperty("mobile", sharedPreferences.getString("",""));
        paramObject.addProperty("first_name", sharedPreferences.getString("first_name",""));
        paramObject.addProperty("middle_name", sharedPreferences.getString("middle_name",""));
        paramObject.addProperty("last_name", sharedPreferences.getString("last_name",""));
        paramObject.addProperty("d_o_b", sharedPreferences.getString("d_o_b",""));
        paramObject.addProperty("gender", sharedPreferences.getString("gender",""));
        paramObject.addProperty("category", sharedPreferences.getString("category",""));
        paramObject.addProperty("father_name", sharedPreferences.getString("father_name",""));
        paramObject.addProperty("father_occupation", sharedPreferences.getString("father_occupation",""));
        paramObject.addProperty("email_id", sharedPreferences.getString("email_id",""));
        paramObject.addProperty("mother_name", sharedPreferences.getString("mother_name",""));
        paramObject.addProperty("mother_occupation", sharedPreferences.getString("mother_occupation",""));
        paramObject.addProperty("permanent_address", sharedPreferences.getString("permanent_address",""));
        paramObject.addProperty("temporary_address", sharedPreferences.getString("temporary_address",""));
        paramObject.addProperty("for_NRI", sharedPreferences.getString("for_NRI",""));



        JsonObject paramObject2 = new JsonObject();
        paramObject2.addProperty("applied_course_name",sharedPreferences.getString("applied_course_name",""));
        paramObject2.addProperty("applied_specialization_name",sharedPreferences.getString("applied_specialization_name",""));

        JsonArray course_detail=new JsonArray();
        course_detail.add(paramObject2);
        paramObject.add("course_detail",course_detail);




        JsonObject paramObject3 = new JsonObject();
        paramObject3.addProperty("edu_name","BE");
        paramObject3.addProperty("passing_year","2018");
        paramObject3.addProperty("obtained_marks","80");
        paramObject3.addProperty("remarks","ada");



        JsonArray education_detail=new JsonArray();
        education_detail.add(paramObject3);
        paramObject.add("education_detail",education_detail);


        JsonObject paramObject4 = new JsonObject();
        paramObject4.addProperty("exam_name","AIEEE");
        paramObject4.addProperty("exam_marks","2018");
        paramObject4.addProperty("exam_rank","80");

        JsonArray compete_exam_detail=new JsonArray();
        compete_exam_detail.add(paramObject4);
        paramObject.add("compete_exam_detail",compete_exam_detail);


        JsonObject paramObject5 = new JsonObject();
        paramObject5.addProperty("doc_name","AIEEE");
        paramObject5.addProperty("doc_url","https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf");


        JsonArray upload_detail=new JsonArray();
        upload_detail.add(paramObject5);
        paramObject.add("upload_detail",upload_detail);


        Log.d(" adada",""+paramObject.toString());


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://eleganzit.online/testhost/users/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface myInterface = retrofit.create(RetrofitInterface.class);
        Call<JsonObject> call=myInterface.applyCollegeMobile(paramObject);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                //  Log.e("response-success", response.body().toString());

                Toast.makeText(AddEducationInformationActivity.this, "Successfully Applied", Toast.LENGTH_SHORT).show();
               startActivity(new Intent(AddEducationInformationActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
               .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));


                Log.d("reeeee","sadad"+response.body().toString());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                // Log.d("reeeee",""+t.getLocalizedMessage());
                Log.d("reeeee","saS"+t.getStackTrace());

            }
        });

    }

}
