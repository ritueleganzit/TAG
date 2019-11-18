package com.eleganzit.tag.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.R;
import com.eleganzit.tag.adapter.EduBackgroundAdapter;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.DeleteApiResponse;
import com.eleganzit.tag.model.Workdata;
import com.eleganzit.tag.model.addeducation.EducationDeleteResponse;
import com.eleganzit.tag.model.addwork.AddWorkExperience;
import com.eleganzit.tag.model.profileinfo.ExperienceInfo;
import com.eleganzit.tag.utils.UserLoggedInSession;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class GetWorkActivty extends AppCompatActivity {

    CardView add_work;
    RecyclerView rc_work_get;
    Button btnsave;
    TextView submit;
    boolean isValid;
    WorkBackgroundAdapter workBackgroundAdapter;
    EditText employee_exp;
    List<ExperienceInfo> workdata=new ArrayList<>();
    List<ExperienceInfo> accounts2=new ArrayList<>();
    String user_id,exp;
    ProgressDialog progressDialog;
    UserLoggedInSession userLoggedInSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_work_activty);
        employee_exp=findViewById(R.id.employee_exp);
        submit=findViewById(R.id.submit);
        btnsave=findViewById(R.id.btnsave);
        rc_work_get=findViewById(R.id.rc_work_get);
        workdata=getIntent().getParcelableArrayListExtra("workdata");
        exp=getIntent().getStringExtra("exp");
        employee_exp.setText(exp);
        userLoggedInSession=new UserLoggedInSession(GetWorkActivty.this);
        user_id=userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID);
        progressDialog = new ProgressDialog(GetWorkActivty.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateWork();
            }
        });

        employee_exp.setText(""+exp);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JsonObject paramObject = new JsonObject();
                paramObject.addProperty("user_id", user_id);
                paramObject.addProperty("total_exp", employee_exp.getText().toString());
                JsonArray jsonArray=new JsonArray();



                for (int i=0;i<workBackgroundAdapter.getArrayList().size();i++)
                {


                    ExperienceInfo experienceInfo=workBackgroundAdapter.getArrayList().get(i);
                    Log.d("sdaad",""+experienceInfo.getEmployeeName());
                    if (experienceInfo.getEmployeeName().trim().equals("")) {


                        Toast.makeText(GetWorkActivty.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                        isValid=false;

                        break;


                    }

                    else if (experienceInfo.getDesignation().trim().equals("")) {

                        Toast.makeText(GetWorkActivty.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                        isValid=false;


                        break;


                    }else if (experienceInfo.getDepartment().trim().equals("")) {

                        Toast.makeText(GetWorkActivty.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                        isValid=false;

                        break;

                    }else if (experienceInfo.getCurrentJobQue().equalsIgnoreCase("nodata")) {

                        Toast.makeText(GetWorkActivty.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                        isValid=false;


                        break;

                    }
                    else {
                        isValid=true;

                        JsonObject paramObject2=new JsonObject();
                        paramObject2.addProperty("experience_id",experienceInfo.getExperienceId());
                        paramObject2.addProperty("employee_name",experienceInfo.getEmployeeName());
                        paramObject2.addProperty("designation",experienceInfo.getDesignation());
                        paramObject2.addProperty("department",experienceInfo.getDepartment());
                        paramObject2.addProperty("current_job_que",experienceInfo.getCurrentJobQue());

                        jsonArray.add(paramObject2);
                    }


                }

                if (isValid)
                {
                    paramObject.add("work_details", jsonArray);




                    Log.d("dataaaa","-"+paramObject.toString());

                    updateWork2(paramObject);
                }

            }
        });
        add_work=findViewById(R.id.add_work);
        add_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GetWorkActivty.this, AddWorkActivity.class));
                finish();
            }
        });
        workBackgroundAdapter= new WorkBackgroundAdapter (workdata,GetWorkActivty.this,user_id);
        rc_work_get.setAdapter(workBackgroundAdapter);

    }

    public  class WorkBackgroundAdapter extends RecyclerView.Adapter<WorkBackgroundAdapter.MyViewHolder>
    {
        ProgressDialog progressDialog;
        String iscurrent="nodata";

        Context context;
        Activity activity;
        String id;
        List<ExperienceInfo> accounts;
        public WorkBackgroundAdapter(List<ExperienceInfo> accounts, Context context,String id
        ) {
            activity = (Activity) context;

            this.context = context;
            this.id = id;
            this.accounts = accounts;
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Please Wait");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_work,viewGroup,false);
            MyViewHolder myViewHolder=new MyViewHolder(v);

            return myViewHolder;
        }
        public  List<ExperienceInfo> getArrayList(){
            return accounts;
        }

        @Override
        public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {

            final ExperienceInfo workdata=accounts.get(i);

            holder.employee_name.setText(workdata.getEmployeeName());
            holder.designation.setText(workdata.getDesignation());
            holder.department.setText(workdata.getDepartment());


           holder.employee_name.addTextChangedListener(new TextWatcher() {
               @Override
               public void beforeTextChanged(CharSequence s, int start, int count, int after) {

               }

               @Override
               public void onTextChanged(CharSequence s, int start, int before, int count) {


               }

               @Override
               public void afterTextChanged(Editable s) {
            workdata.setEmployeeName(s.toString());
               }
           });
holder.designation.addTextChangedListener(new TextWatcher() {
               @Override
               public void beforeTextChanged(CharSequence s, int start, int count, int after) {

               }

               @Override
               public void onTextChanged(CharSequence s, int start, int before, int count) {


               }

               @Override
               public void afterTextChanged(Editable s) {
            workdata.setDesignation(s.toString());
               }
           });holder.department.addTextChangedListener(new TextWatcher() {
               @Override
               public void beforeTextChanged(CharSequence s, int start, int count, int after) {

               }

               @Override
               public void onTextChanged(CharSequence s, int start, int before, int count) {


               }

               @Override
               public void afterTextChanged(Editable s) {
            workdata.setDepartment(s.toString());
               }
           });


            if (workdata.getCurrentJobQue().equalsIgnoreCase("No"))
            {

                holder.rb1.setChecked(false);
                holder.rb2.setChecked(true);
                iscurrent="no";


            }
            else
            {

                iscurrent="yes";
                holder.rb2.setChecked(false);
                holder.rb1.setChecked(true);
            }

            holder.current_job.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId==R.id.rb1)
                    {
                        iscurrent="yes";
                        workdata.setCurrentJobQue(iscurrent);
                    } if (checkedId==R.id.rb2)
                    {
                        iscurrent="no";
                        workdata.setCurrentJobQue(iscurrent);
                    }
                }
            });

            holder.btnsave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (holder.employee_name.getText().toString().trim().equals("")) {


                        Toast.makeText(context, "Please enter employee name", Toast.LENGTH_SHORT).show();

                        holder.employee_name.requestFocus();

                    }

                    else if (holder.designation.getText().toString().trim().equals("")) {

                        Toast.makeText(context, "Please enter designation", Toast.LENGTH_SHORT).show();


                        holder.designation.requestFocus();

                    }else if (holder.department.getText().toString().trim().equals("")) {

                        Toast.makeText(context, "Please enter department", Toast.LENGTH_SHORT).show();

                        holder.department.requestFocus();

                    }else if (iscurrent.equalsIgnoreCase("nodata")) {

                        Toast.makeText(context, "Please select whether it is existing job", Toast.LENGTH_SHORT).show();



                    }
                    else {

                        updateWork(holder,""+ workdata.getExperienceId());
                    }

                }
            });
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog alertDialog=     new AlertDialog.Builder(context).setMessage("Are you sure you want to delete?").setCancelable(false)

                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    deleteData(""+workdata.getExperienceId());
                                    //signOut();
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).show();

                }
            });

        }

        private void updateWork(MyViewHolder holder, String work_id) {





            progressDialog.show();
            JsonObject paramObject = new JsonObject();
            paramObject.addProperty("user_id", id);
            paramObject.addProperty("total_exp", employee_exp.getText().toString());

            JsonObject paramObject2=new JsonObject();
            paramObject2.addProperty("experience_id",work_id);
            paramObject2.addProperty("employee_name",holder.employee_name.getText().toString());
            paramObject2.addProperty("designation",holder.designation.getText().toString());
            paramObject2.addProperty("department",holder.department.getText().toString());
            paramObject2.addProperty("current_job_que",iscurrent);
            JsonArray jsonArray=new JsonArray();

            jsonArray.add(paramObject2);

            paramObject.add("work_details", jsonArray);



            Log.d("dataaaa","-"+paramObject.toString());

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://eleganzit.online/testhost/users/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RetrofitInterface myInterface = retrofit.create(RetrofitInterface.class);
            Call<AddWorkExperience> call=myInterface.updateWorkExperience(paramObject);
            call.enqueue(new Callback<AddWorkExperience>() {
                @Override
                public void onResponse(Call<AddWorkExperience> call, Response<AddWorkExperience> response) {
                    progressDialog.dismiss();
                    if (response.isSuccessful())
                    {
                        if (response.body().getStatus().toString().equalsIgnoreCase("1"))
                        {
                            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
                            activity.finish();
                        }
                        else
                        {
                            Toast.makeText(context, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                }

                @Override
                public void onFailure(Call<AddWorkExperience> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(context, "Server and Internet Error", Toast.LENGTH_SHORT).show();
                }
            });




        }


        private void deleteData(String work_id) {
            progressDialog.show();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://eleganzit.online/testhost/users/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RetrofitInterface myInterface = retrofit.create(RetrofitInterface.class);
            Call<EducationDeleteResponse> call=myInterface.deleteWorkData(work_id);
            call.enqueue(new Callback<EducationDeleteResponse>() {
                @Override
                public void onResponse(Call<EducationDeleteResponse> call, Response<EducationDeleteResponse> response) {
                    progressDialog.dismiss();
                    if (response.isSuccessful()) {
                        Log.d("ddddd", "" + response.message());
                        Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show();
                        ((Activity)context).finish();

                    }
                }

                @Override
                public void onFailure(Call<EducationDeleteResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(context, "Server and Internet Error", Toast.LENGTH_SHORT).show();
                }
            });


        }
        @Override
        public int getItemCount() {
            return accounts.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            Button btnsave;
            RadioGroup current_job;
            RadioButton rb1,rb2;

            ImageView delete;
            EditText employee_name,designation,department;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                current_job=itemView.findViewById(R.id.current_job);
                rb1=itemView.findViewById(R.id.rb1);
                rb2=itemView.findViewById(R.id.rb2);

                delete=itemView.findViewById(R.id.delete);
                employee_name=itemView.findViewById(R.id.employee_name);
                designation=itemView.findViewById(R.id.designation);
                department=itemView.findViewById(R.id.department);
                current_job=itemView.findViewById(R.id.current_job);
                btnsave=itemView.findViewById(R.id.btnsave);



            }
        }
    }

    private void updateWork() {





        progressDialog.show();
        JsonObject paramObject = new JsonObject();
        paramObject.addProperty("user_id", user_id);
        paramObject.addProperty("total_exp", employee_exp.getText().toString());


        JsonArray jsonArray=new JsonArray();


        paramObject.add("work_details", jsonArray);



        Log.d("dataaaa","-"+paramObject.toString());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://eleganzit.online/testhost/users/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface myInterface = retrofit.create(RetrofitInterface.class);
        Call<AddWorkExperience> call=myInterface.updateWorkExperience(paramObject);
        call.enqueue(new Callback<AddWorkExperience>() {
            @Override
            public void onResponse(Call<AddWorkExperience> call, Response<AddWorkExperience> response) {
                progressDialog.dismiss();
                if (response.isSuccessful())
                {
                    if (response.body().getStatus().toString().equalsIgnoreCase("1"))
                    {
                        Toast.makeText(GetWorkActivty.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else
                    {
                        Toast.makeText(GetWorkActivty.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<AddWorkExperience> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(GetWorkActivty.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();
            }
        });




    }
    private void updateWork2(JsonObject params) {





        progressDialog.show();



        Log.d("dataaaa","-"+params.toString());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://eleganzit.online/testhost/users/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface myInterface = retrofit.create(RetrofitInterface.class);
        Call<AddWorkExperience> call=myInterface.updateWorkExperience(params);
        call.enqueue(new Callback<AddWorkExperience>() {
            @Override
            public void onResponse(Call<AddWorkExperience> call, Response<AddWorkExperience> response) {
                progressDialog.dismiss();
                if (response.isSuccessful())
                {
                    if (response.body().getStatus().toString().equalsIgnoreCase("1"))
                    {
                        Toast.makeText(GetWorkActivty.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else
                    {
                        Toast.makeText(GetWorkActivty.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<AddWorkExperience> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(GetWorkActivty.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();
            }
        });




    }

}
