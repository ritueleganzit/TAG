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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.R;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.AddEducationDataResponse;
import com.eleganzit.tag.model.DeleteApiResponse;
import com.eleganzit.tag.model.Education;
import com.eleganzit.tag.model.Workdata;
import com.eleganzit.tag.ui.activity.AddEducationActivity;
import com.eleganzit.tag.ui.activity.MyProfileActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EduBackgroundAdapter extends RecyclerView.Adapter<EduBackgroundAdapter.MyViewHolder>
{
    ArrayList<String> years = new ArrayList<String>();

    Context context;
    Activity activity;
   List<Education> accounts;
   String id;
    ProgressDialog progressDialog;
    public EduBackgroundAdapter(List<Education> accounts, Context context,String id) {

        this.context = context;
        this.accounts = accounts;
        this.id = id;
        activity = (Activity) context;

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.edu_row_bg,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {

        final Education workdata=accounts.get(i);
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int j = 1900; j <= thisYear; j++) {
            years.add(Integer.toString(j));
        }
        holder.cource_year.setText(workdata.getCourceYear());
        holder.school_name.setText(workdata.getSchoolName());
        holder.marks.setText(workdata.getMarks());
        holder.subject.setText(workdata.getSubject());
        holder.board.setText(workdata.getBoard());
        holder.cource_level.setText(workdata.getCourceLevel());
        holder.cource_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Collections.reverse(years);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, years);
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        holder.cource_year.setText(""+years.get(which));
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
holder.btnsave.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //Toast.makeText(context, ""+workdata.getEducationId(), Toast.LENGTH_SHORT).show();
        updateEducation(holder,workdata.getEducationId());
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
                                deleteData(workdata.getEducationId());
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

    private void deleteData(String educationId) {

        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofit().create(RetrofitInterface.class);

        Call<DeleteApiResponse> call=myInterface.delete("delete_educationdata",educationId);
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

    private void updateEducation(MyViewHolder holder, String educationId) {
        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofit().create(RetrofitInterface.class);
        Call<AddEducationDataResponse> call=myInterface.update_education_bg("update_education_bg"
                ,id
                ,educationId
                ,holder.cource_level.getText().toString()
                ,holder.school_name.getText().toString()
                ,holder.cource_year.getText().toString()
                ,holder.board.getText().toString()
                ,holder.subject.getText().toString()
                ,holder.marks.getText().toString());
        call.enqueue(new Callback<AddEducationDataResponse>() {
            @Override
            public void onResponse(Call<AddEducationDataResponse> call, Response<AddEducationDataResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful())
                {
                    if (response.body().getData()!=null)
                    {
                        Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
                     //   context.startActivity(new Intent(context, MyProfileActivity.class));
                        ((Activity)context).finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<AddEducationDataResponse> call, Throwable t) {
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

EditText cource_level,school_name,marks,subject,board,cource_year;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cource_level=itemView.findViewById(R.id.cource_level);
            delete=itemView.findViewById(R.id.delete);
            btnsave=itemView.findViewById(R.id.btnsave);
            school_name=itemView.findViewById(R.id.school_name);
            marks=itemView.findViewById(R.id.marks);
            subject=itemView.findViewById(R.id.subject);
            board=itemView.findViewById(R.id.board);
            cource_year=itemView.findViewById(R.id.cource_year);


        }
    }
}
