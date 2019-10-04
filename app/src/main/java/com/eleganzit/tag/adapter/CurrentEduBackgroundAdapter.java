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
import com.eleganzit.tag.ui.activity.AddEducationPreference;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentEduBackgroundAdapter extends RecyclerView.Adapter<CurrentEduBackgroundAdapter.MyViewHolder>
{

    ProgressDialog progressDialog;

    Context context;
    Activity activity;
   List<Preferancedata> accounts;
    public CurrentEduBackgroundAdapter(List<Preferancedata> accounts, Context context) {

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

        final Preferancedata workdata=accounts.get(i);

        holder.stream.setText(workdata.getStream());
        holder.course.setText(workdata.getCourse());
        holder.specialisation.setText(workdata.getSpecialisation());
        holder.mode_of_study.setText(workdata.getMode_of_study());


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog=     new AlertDialog.Builder(context).setMessage("Are you sure you want to delete?").setCancelable(false)

                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                deleteData(workdata.getPreferance_id());
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
        updateEduPref(holder,workdata.getPreferance_id(),workdata.getUserId());
    }
});

    }

    private void deleteData(String preferance_id) {
        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofit().create(RetrofitInterface.class);

        Call<DeleteApiResponse> call=myInterface.delete_preferancedata("delete_preferancedata",preferance_id);
        call.enqueue(new Callback<DeleteApiResponse>() {
            @Override
            public void onResponse(Call<DeleteApiResponse> call, Response<DeleteApiResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    Log.d("ddddd", "" + response.message());
                    Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show();
                    ((Activity)context).finish();

                }

            }

            @Override
            public void onFailure(Call<DeleteApiResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "Server and Internet Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateEduPref(MyViewHolder holder, String preferance_id, String userId) {
        progressDialog.show();
        Log.d("specialisation",""+userId);
        Log.d("specialisation",""+preferance_id);
        Log.d("specialisation",""+holder.stream.getText().toString());
        Log.d("specialisation",""+holder.course.getText().toString());
        Log.d("specialisation",""+holder.specialisation.getText().toString());
        Log.d("specialisation",""+holder.mode_of_study.getText().toString());
        RetrofitInterface myInterface = RetrofitAPI.getRetrofit().create(RetrofitInterface.class);
        Call<UpdateEducationPreferanceResponse> call=myInterface.update_education_preferance(
                "update_education_preferance"
                ,userId
                ,preferance_id
                ,holder.stream.getText().toString()
                ,holder.course.getText().toString()
                ,holder.specialisation.getText().toString()
                ,holder.mode_of_study.getText().toString()
        );
        call.enqueue(new Callback<UpdateEducationPreferanceResponse>() {
            @Override
            public void onResponse(Call<UpdateEducationPreferanceResponse> call, Response<UpdateEducationPreferanceResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful())
                {                        Log.d("ddddd",""+response.message());

                    if (response.body().getData()!=null)
                    {

                        Log.d("ddddd",""+response.body().getData().get(0).getPreferance_id());
                        Log.d("ddddd",""+response.body().getData().get(0).getModeOfStudy());
                        Log.d("ddddd",""+response.body().getData().get(0).getCourse());
                        Log.d("ddddd",""+response.body().getData().get(0).getSpecialisation());
                        Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
                        ((Activity)context).finish();

                    }
                }

            }

            @Override
            public void onFailure(Call<UpdateEducationPreferanceResponse> call, Throwable t) {
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
}
