package com.eleganzit.tag.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.ApplyCompetitive;
import com.eleganzit.tag.HomeActivity;
import com.eleganzit.tag.R;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.ApplyCategory;
import com.eleganzit.tag.model.ApplyEducation;
import com.eleganzit.tag.utils.UserLoggedInSession;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    ArrayList<ApplyEducation> categorydata=new ArrayList();
    ArrayList<ApplyCompetitive> applyCompetitives=new ArrayList();
    AddCourseAdapter addCourseAdapter;
    AddCompetitiveAdapter addCompetitiveAdapter;
    ArrayList<ApplyCategory> lstArrayList;
    UserLoggedInSession userLoggedInSession;
    TextView addeducation,addmorecompetitive;
    EditText ednameofdegree,edyearofdegree,edmarksobtained,edremarks,ed_competitive,ed_competitive_mark,ed_competitive_rank;
    private boolean isData=false;
    private boolean isData2=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_education_information);
        sharedPreferences=getSharedPreferences("dataapply",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        applysubmit=findViewById(R.id.applysubmit);
        rc_edu=findViewById(R.id.rc_edu);
        rc_competitive=findViewById(R.id.rc_competitive);
        edyearofdegree=findViewById(R.id.edyearofdegree);
        edmarksobtained=findViewById(R.id.edmarksobtained);
        ed_competitive=findViewById(R.id.ed_competitive);
        ed_competitive_mark=findViewById(R.id.ed_competitive_mark);
        ed_competitive_rank=findViewById(R.id.ed_competitive_rank);
        edremarks=findViewById(R.id.edremarks);
        ednameofdegree=findViewById(R.id.ednameofdegree);
        addeducation=findViewById(R.id.addeducation);
        userLoggedInSession=new UserLoggedInSession(AddEducationInformationActivity.this);
        addmorecompetitive=findViewById(R.id.addmorecompetitive);
        addCompetitiveAdapter=new AddCompetitiveAdapter();
        addCourseAdapter=new AddCourseAdapter();
        rc_edu.setAdapter(addCourseAdapter);
        rc_competitive.setAdapter(addCompetitiveAdapter);
        Gson gson = new Gson();
        String response=sharedPreferences.getString("jsoncars" , "");
        lstArrayList= gson.fromJson(response,
                new TypeToken<List<ApplyCategory>>(){}.getType());




        applysubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid())
                {

                    if ((categorydata.size()>0) || (applyCompetitives.size()>0) ) {
                        Log.d("eerrrr", "if" + categorydata.size());

                        for (int i = 0; i < categorydata.size(); i++) {
                            if ((categorydata.get(i).getName().equalsIgnoreCase("")) || (categorydata.get(i).getMarks().equalsIgnoreCase("")) || (categorydata.get(i).getYear().equalsIgnoreCase(""))) {
                                Log.d("eerrrr", "if" + i);

                                isData = true;
                                Toast.makeText(AddEducationInformationActivity.this, "Please insert  data", Toast.LENGTH_SHORT).show();
                                break;
                            } else {
                                isData = false;
                            }

                        }
                        for (int i=0;i<applyCompetitives.size();i++) {
                            if ((applyCompetitives.get(i).getName().equalsIgnoreCase("")) || (applyCompetitives.get(i).getMark().equalsIgnoreCase("")) || (applyCompetitives.get(i).getRank().equalsIgnoreCase("")))
                            {
                                Log.d("eerrrr","if"+i);

                                isData2=true;
                                Toast.makeText(AddEducationInformationActivity.this, "Please insert  data", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            else {
                                isData2=false;
                            }

                        }

                        if (!isData && !isData2) {

                           // Toast.makeText(AddEducationInformationActivity.this, "Addded", Toast.LENGTH_SHORT).show();

                            addData();


                        }


                    }


                    else
                    {
                       addData();
                    }



                }

            }
        });
        addeducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (categorydata.size()<=1)
                {
                    categorydata.add(new ApplyEducation());
                    addCourseAdapter.notifyDataSetChanged();
                    if (categorydata.size()>1)
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
                if (applyCompetitives.size()<=1)
                {
                    applyCompetitives.add(new ApplyCompetitive());
                    addCompetitiveAdapter.notifyDataSetChanged();
                    if (applyCompetitives.size()>1)
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
    public boolean isValid() {

        if (ednameofdegree.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please Enter Degree", Toast.LENGTH_SHORT).show();

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
       else if (ed_competitive.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please Enter Exam Name", Toast.LENGTH_SHORT).show();

            ed_competitive.requestFocus();

            return false;
        }else if (ed_competitive_mark.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please Enter Marks", Toast.LENGTH_SHORT).show();

            ed_competitive_mark.requestFocus();

            return false;
        }
        else if (ed_competitive_rank.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please Enter Rank", Toast.LENGTH_SHORT).show();

            ed_competitive_rank.requestFocus();

            return false;
        }



        return true;
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

            myViewHolder.edname.setText(categorydata.get(i).getName());
            myViewHolder.edyear.setText(categorydata.get(i).getYear());
            myViewHolder.edmarks.setText(categorydata.get(i).getMarks());
            myViewHolder.edremarks.setText(categorydata.get(i).getRemarks());

            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    categorydata.remove(i);
                    notifyDataSetChanged();
                    addeducation.setVisibility(View.VISIBLE);

                }
            });
        }

        @Override
        public int getItemCount() {
            return categorydata.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            EditText edname,edyear,edmarks,edremarks;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                edname=itemView.findViewById(R.id.edname);
                edyear=itemView.findViewById(R.id.edyear);
                edmarks=itemView.findViewById(R.id.edmarks);
                edremarks=itemView.findViewById(R.id.edremarks);
                edname.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        categorydata.get(getAdapterPosition()).setName(edname.getText().toString());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                edyear.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        categorydata.get(getAdapterPosition()).setYear(edyear.getText().toString());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });  edmarks.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        categorydata.get(getAdapterPosition()).setMarks(edmarks.getText().toString());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                }); edremarks.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        categorydata.get(getAdapterPosition()).setRemarks(edremarks.getText().toString());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
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


            myViewHolder.name_ed.setText(applyCompetitives.get(i).getName());
            myViewHolder.ed_mark.setText(applyCompetitives.get(i).getMark());
            myViewHolder.ed_rank.setText(applyCompetitives.get(i).getRank());


            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    applyCompetitives.remove(i);
                    notifyDataSetChanged();
                    addmorecompetitive.setVisibility(View.VISIBLE);

                }
            });
        }

        @Override
        public int getItemCount() {
            return applyCompetitives.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
EditText ed_mark,ed_rank,name_ed;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                ed_mark=itemView.findViewById(R.id.ed_mark);
                name_ed=itemView.findViewById(R.id.name_ed);
                ed_rank=itemView.findViewById(R.id.ed_rank);
                ed_mark.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        applyCompetitives.get(getAdapterPosition()).setMark(ed_mark.getText().toString());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });




                name_ed.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        applyCompetitives.get(getAdapterPosition()).setName(name_ed.getText().toString());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });


                ed_rank.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        applyCompetitives.get(getAdapterPosition()).setRank(ed_rank.getText().toString());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            }
        }
    }

    public void addData()
    {
try {
    JsonObject paramObject = new JsonObject();
    paramObject.addProperty("user_id", userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID));
    paramObject.addProperty("college_id", "" + sharedPreferences.getString("college_id", "4"));
    paramObject.addProperty("course_list_id", 3);
    paramObject.addProperty("mobile", sharedPreferences.getString("", ""));
    paramObject.addProperty("first_name", sharedPreferences.getString("first_name", ""));
    paramObject.addProperty("middle_name", sharedPreferences.getString("middle_name", ""));
    paramObject.addProperty("last_name", sharedPreferences.getString("last_name", ""));
    paramObject.addProperty("d_o_b", sharedPreferences.getString("d_o_b", ""));
    paramObject.addProperty("gender", sharedPreferences.getString("gender", ""));
    paramObject.addProperty("category", sharedPreferences.getString("category", ""));
    paramObject.addProperty("father_name", sharedPreferences.getString("father_name", ""));
    paramObject.addProperty("father_occupation", sharedPreferences.getString("father_occupation", ""));
    paramObject.addProperty("email_id", sharedPreferences.getString("email_id", ""));
    paramObject.addProperty("mother_name", sharedPreferences.getString("mother_name", ""));
    paramObject.addProperty("mother_occupation", sharedPreferences.getString("mother_occupation", ""));
    paramObject.addProperty("permanent_address", sharedPreferences.getString("permanent_address", ""));
    paramObject.addProperty("temporary_address", sharedPreferences.getString("temporary_address", ""));
    paramObject.addProperty("for_NRI", sharedPreferences.getString("for_NRI", ""));


    if (lstArrayList != null) {
        ApplyCategory applyCategory = new ApplyCategory();
        applyCategory.setApplied_course_name(sharedPreferences.getString("applied_course_name", ""));
        applyCategory.setApplied_specialization_name(sharedPreferences.getString("applied_specialization_name", ""));
        lstArrayList.add(applyCategory);

        Log.d("ggggg", "" + lstArrayList.size());
        JsonArray course_detail = new JsonArray();

        HashMap<String, JsonObject> map = new HashMap<String, JsonObject>();
        for (int i = 0; i < lstArrayList.size(); i++) {
            JsonObject paramObject2 = new JsonObject();
            Log.d("ggggg", "" + lstArrayList.get(i).getApplied_course_name());
            paramObject2.addProperty("applied_course_name", lstArrayList.get(i).getApplied_course_name());
            paramObject2.addProperty("applied_specialization_name", lstArrayList.get(i).getApplied_specialization_name());

            map.put("json" + i, paramObject2);
            course_detail.add(map.get("json" + i));
            paramObject.add("course_detail", course_detail);

        }
        Log.d("rrrr", "" + course_detail.toString());


    } else {
        JsonObject paramObject2 = new JsonObject();


        paramObject2.addProperty("applied_course_name", sharedPreferences.getString("applied_course_name", ""));
        paramObject2.addProperty("applied_specialization_name", sharedPreferences.getString("applied_specialization_name", ""));
        JsonArray course_detail = new JsonArray();
        course_detail.add(paramObject2);
        paramObject.add("course_detail", course_detail);

    }


    if (categorydata != null) {
        ApplyEducation applyCategory = new ApplyEducation();
        applyCategory.setName(ednameofdegree.getText().toString());
        applyCategory.setRemarks(edremarks.getText().toString());
        applyCategory.setMarks(edmarksobtained.getText().toString());
        applyCategory.setYear(edyearofdegree.getText().toString());
        categorydata.add(applyCategory);
        JsonArray education_detail = new JsonArray();

        HashMap<String, JsonObject> map = new HashMap<String, JsonObject>();
        for (int i = 0; i < categorydata.size(); i++) {
            JsonObject paramObject3 = new JsonObject();

            Log.d("ggggg", "" + categorydata.get(i).getName());
            Log.d("ggggg", "" + categorydata.get(i).getYear());
            paramObject3.addProperty("edu_name", "" + categorydata.get(i).getName());
            paramObject3.addProperty("passing_year", "" + categorydata.get(i).getYear());
            paramObject3.addProperty("obtained_marks", "" + categorydata.get(i).getMarks());
            paramObject3.addProperty("remarks", "" + categorydata.get(i).getRemarks());
            map.put("json" + i, paramObject3);
            education_detail.add(map.get("json" + i));
            paramObject.add("education_detail", education_detail);
        }


    } else {
        JsonObject paramObject3 = new JsonObject();
        paramObject3.addProperty("edu_name", "" + ednameofdegree.getText().toString());
        paramObject3.addProperty("passing_year", "" + edyearofdegree.getText().toString());
        paramObject3.addProperty("obtained_marks", "" + edmarksobtained.getText().toString());
        paramObject3.addProperty("remarks", "" + edremarks.getText().toString());


        JsonArray education_detail = new JsonArray();
        education_detail.add(paramObject3);
        paramObject.add("education_detail", education_detail);
    }

if (applyCompetitives!=null)
{
    ApplyCompetitive applyCategory = new ApplyCompetitive ();
    applyCategory.setName(ed_competitive.getText().toString());
    applyCategory.setRank(ed_competitive_rank.getText().toString());
    applyCategory.setMark(ed_competitive_mark.getText().toString());
    applyCompetitives.add(applyCategory);
    JsonArray compete_exam_detail = new JsonArray();

    HashMap<String, JsonObject> map = new HashMap<String, JsonObject>();
    for (int i = 0; i < applyCompetitives.size(); i++) {
        JsonObject paramObject4 = new JsonObject();


        Log.d("ggggg", "" + applyCompetitives.get(i).getName());
        Log.d("ggggg", "" + applyCompetitives.get(i).getRank());
        paramObject4.addProperty("exam_name", "" + applyCompetitives.get(i).getName());
        paramObject4.addProperty("exam_marks", "" + applyCompetitives.get(i).getMark());
        paramObject4.addProperty("exam_rank", "" + applyCompetitives.get(i).getRank());
        map.put("json" + i, paramObject4);
        compete_exam_detail.add(map.get("json" + i));
        paramObject.add("compete_exam_detail", compete_exam_detail);
    }
}
else
{
    JsonObject paramObject4 = new JsonObject();
    paramObject4.addProperty("exam_name", ""+ed_competitive.getText().toString());
    paramObject4.addProperty("exam_marks", ""+ed_competitive_mark.getText().toString());
    paramObject4.addProperty("exam_rank", ""+ed_competitive_rank.getText().toString());

    JsonArray compete_exam_detail = new JsonArray();
    compete_exam_detail.add(paramObject4);
    paramObject.add("compete_exam_detail", compete_exam_detail);


}

    JsonObject paramObject5 = new JsonObject();
    paramObject5.addProperty("doc_name", "AIEEE");
    paramObject5.addProperty("doc_url", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf");


    JsonArray upload_detail = new JsonArray();
    upload_detail.add(paramObject5);
    paramObject.add("upload_detail", upload_detail);


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

                editor.clear();
                editor.commit();
                Toast.makeText(AddEducationInformationActivity.this, "Successfully Applied", Toast.LENGTH_SHORT).show();
               startActivity(new Intent(AddEducationInformationActivity.this, HomeActivity.class)
                       .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
               .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));

                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

                Log.d("reeeee","sadad"+response.body().toString());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                // Log.d("reeeee",""+t.getLocalizedMessage());
                Log.d("reeeee","saS"+t.getStackTrace());

            }
        });
}
catch (Exception e)
{

}

    }

}
