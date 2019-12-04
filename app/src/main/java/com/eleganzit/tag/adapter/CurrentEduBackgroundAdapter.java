package com.eleganzit.tag.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.eleganzit.tag.R;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.AddEducationPreferanceResponse;
import com.eleganzit.tag.model.DeleteApiResponse;
import com.eleganzit.tag.model.Preferancedata;
import com.eleganzit.tag.model.UpdateEducationPreferanceResponse;
import com.eleganzit.tag.model.Workdata;
import com.eleganzit.tag.model.addeducation.EducationDeleteResponse;
import com.eleganzit.tag.model.dropdowndata.DropDownListResponse;
import com.eleganzit.tag.model.profileinfo.PreferenceInfo;
import com.eleganzit.tag.ui.activity.AddEducationPreference;
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

public class CurrentEduBackgroundAdapter extends RecyclerView.Adapter<CurrentEduBackgroundAdapter.MyViewHolder>
{

    ProgressDialog progressDialog;
    private ArrayList<String> streamname;
    private ArrayList<String> streamid;
    private ArrayList<String> coursename;
    private ArrayList<String> couseid;
    private ArrayList<String> modename;
    private ArrayList<String> specialization;
    Context context;
    Activity activity;
   List<PreferenceInfo> accounts;
    public CurrentEduBackgroundAdapter(List<PreferenceInfo> accounts, Context context) {

        this.context = context;
        this.accounts = accounts;
        activity = (Activity) context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.edu_pref_row,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {

        if (i==0)
        {
            getPref();
        }
        final PreferenceInfo workdata=accounts.get(i);

        holder.stream.setText(workdata.getStream());
        holder.course.setText(workdata.getCourse());
        holder.specialisation.setText(workdata.getSpecialization());
        holder.mode_of_study.setText(workdata.getStudyMode());

        holder.course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, coursename);
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        holder.course.setText(""+coursename.get(which));
                      //  Toast.makeText(context, ""+couseid.get(which), Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });    holder.mode_of_study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, modename);
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        holder.mode_of_study.setText(""+modename.get(which));
                       // Toast.makeText(context, ""+modename.get(which), Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        holder.stream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, streamname);
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        holder.stream.setText(""+streamname.get(which));
                      //  Toast.makeText(context, ""+streamid.get(which), Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        holder.specialisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, specialization);
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        holder. specialisation.setText(""+specialization.get(which));
                        //Toast.makeText(context, ""+specialization.get(which), Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
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
                                deleteData(""+workdata.getPreferenceId());
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
holder.btnsave.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        if (holder.stream.getText().toString().trim().equals("")) {


            Toast.makeText(context, "Please enter education stream", Toast.LENGTH_SHORT).show();

            holder.stream.requestFocus();

        }

        else if (holder.course.getText().toString().trim().equals("")) {

            Toast.makeText(context, "Please enter course", Toast.LENGTH_SHORT).show();


            holder.course.requestFocus();

        }else if (holder.mode_of_study.getText().toString().trim().equals("")) {

            Toast.makeText(context, "Please enter mode of study", Toast.LENGTH_SHORT).show();


            holder.mode_of_study.requestFocus();

        }
        else {
            updateEduPref(holder,""+workdata.getPreferenceId(),""+workdata.getUserId());

        }
    }
});

    }

    private void deleteData(String preferance_id) {
        progressDialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://eleganzit.online/testhost/users/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface myInterface = retrofit.create(RetrofitInterface.class);
        Call<EducationDeleteResponse> call=myInterface.deletePreferanceData(preferance_id);
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

    private void updateEduPref(MyViewHolder holder, String preferance_id, String userId) {
        progressDialog.show();


        JsonObject paramObject = new JsonObject();
        paramObject.addProperty("user_id", userId);

        JsonObject paramObject2=new JsonObject();
        paramObject2.addProperty("preference_id",preferance_id);
        paramObject2.addProperty("course",holder.course.getText().toString());
        paramObject2.addProperty("stream",holder.stream.getText().toString());
        paramObject2.addProperty("specialization",holder.specialisation.getText().toString());
        paramObject2.addProperty("study_mode",holder.mode_of_study.getText().toString());
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


                        Toast.makeText(context, "Successfully Inserted", Toast.LENGTH_SHORT).show();
                        activity.finish();
                    }
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
ImageView delete;
EditText stream,course,specialisation,mode_of_study;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            delete=itemView.findViewById(R.id.delete);
            stream=itemView.findViewById(R.id.stream);
            btnsave=itemView.findViewById(R.id.btnsave);
            course=itemView.findViewById(R.id.course);
            specialisation=itemView.findViewById(R.id.specialisation);
            mode_of_study=itemView.findViewById(R.id.mode_of_study);



        }
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

                    for (int i=0;i<response.body().getData().getStudyMode().size();i++)
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
                Toast.makeText(context, "Server or Internet error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
