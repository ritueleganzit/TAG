package com.eleganzit.tag.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.eleganzit.tag.model.AddworkexpResponse;
import com.eleganzit.tag.model.DeleteApiResponse;
import com.eleganzit.tag.model.Education;
import com.eleganzit.tag.model.Workdata;
import com.eleganzit.tag.ui.activity.AddWorkActivity;
import com.eleganzit.tag.ui.activity.MyProfileActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkBackgroundAdapter extends RecyclerView.Adapter<WorkBackgroundAdapter.MyViewHolder>
{
    ProgressDialog progressDialog;

    Context context;
    Activity activity;
    String id;
   List<Workdata> accounts;
    public WorkBackgroundAdapter(List<Workdata> accounts, Context context,String id
    ) {

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
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_work,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {

        final Workdata workdata=accounts.get(i);

        holder.employee_name.setText(workdata.getEmployee_name());
        holder.designation.setText(workdata.getDesignation());
        holder.department.setText(workdata.getDepartment());
        holder.current_job.setText(workdata.getCurrent_job());

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

        }else if (holder.current_job.getText().toString().trim().equals("")) {

            Toast.makeText(context, "Please enter data", Toast.LENGTH_SHORT).show();


            holder.current_job.requestFocus();

        }
        else {

            updateWork(holder, workdata.getWork_id());
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
                        deleteData(workdata.getWork_id());
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

        RetrofitInterface myInterface = RetrofitAPI.getRetrofit().create(RetrofitInterface.class);
        Call<AddworkexpResponse> call=myInterface.update_work(
                "update_work"
                ,id
                ,work_id
                ,holder.employee_name.getText().toString()
                ,holder.designation.getText().toString()
                ,holder.department.getText().toString()
                ,holder.current_job.getText().toString()
        );
        call.enqueue(new Callback<AddworkexpResponse>() {
            @Override
            public void onResponse(Call<AddworkexpResponse> call, Response<AddworkexpResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful())
                {
                    if (response.body().getData()!=null)
                    {


                        Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
                       // context.startActivity(new Intent(context, MyProfileActivity.class));
                        ((Activity)context).finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<AddworkexpResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "Server and Internet Error", Toast.LENGTH_SHORT).show();

            }
        });


    }
    private void deleteData(String work_id) {
        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofit().create(RetrofitInterface.class);

        Call<DeleteApiResponse> call=myInterface.delete_workdata("delete_workdata",work_id);
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
    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        Button btnsave;
        ImageView delete;
EditText employee_name,designation,department,current_job;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            delete=itemView.findViewById(R.id.delete);
            employee_name=itemView.findViewById(R.id.employee_name);
            designation=itemView.findViewById(R.id.designation);
            department=itemView.findViewById(R.id.department);
            current_job=itemView.findViewById(R.id.current_job);
            btnsave=itemView.findViewById(R.id.btnsave);



        }
    }
}
