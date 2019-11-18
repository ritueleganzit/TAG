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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.R;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.AddEducationDataResponse;
import com.eleganzit.tag.model.DeleteApiResponse;
import com.eleganzit.tag.model.Education;
import com.eleganzit.tag.model.Workdata;
import com.eleganzit.tag.model.addeducation.EducationDeleteResponse;
import com.eleganzit.tag.model.addeducation.EducationUpdateResponse;
import com.eleganzit.tag.model.profileinfo.EducationDetail;
import com.eleganzit.tag.ui.activity.AddEducationActivity;
import com.eleganzit.tag.ui.activity.MyProfileActivity;
import com.eleganzit.tag.utils.UserLoggedInSession;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class EduBackgroundAdapter extends RecyclerView.Adapter<EduBackgroundAdapter.MyViewHolder>
{
    ArrayList<String> years = new ArrayList<String>();
    ArrayList<String> level = new ArrayList<String>();
boolean tenth,twelth,ug,pg;
    Context context;
    Activity activity;
   List<EducationDetail> accounts;
   String id;
    ProgressDialog progressDialog;
    public EduBackgroundAdapter(List<EducationDetail> accounts, Context context,String id) {

        this.context = context;
        this.accounts = accounts;
        this.id = id;
        activity = (Activity) context;
        level.add("10");
        level.add("12");
        level.add("UG");
        level.add("PG");

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

        final EducationDetail workdata=accounts.get(i);
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int j = 1900; j <= thisYear; j++) {
            years.add(Integer.toString(j));
        }

        if (workdata.getType().equalsIgnoreCase("school"))
        {

            if (workdata.getCourseLevel().equalsIgnoreCase("10"))
            {
                tenth=true;
                holder.lin_tenth.setVisibility(View.VISIBLE);
                holder.tenth_year.setText(workdata.getCompletionYear());
                holder.tenth_school_name.setText(workdata.getSchoolName());
                holder.tenth_marks.setText(workdata.getMarks());

                holder.tenth_board.setText(workdata.getBoard());
                holder.cource_level.setText(workdata.getCourseLevel());
            }
            if (workdata.getCourseLevel().equalsIgnoreCase("12"))

            {
                twelth=true;

                holder.lin_twelth.setVisibility(View.VISIBLE);

                holder.twelth_year.setText(workdata.getCompletionYear());
                holder.twelth_school_name.setText(workdata.getSchoolName());
                holder.twelth_marks.setText(workdata.getMarks());
holder.subject.setText(workdata.getSubject());
                holder.twelth_board.setText(workdata.getBoard());
                holder.cource_level.setText(workdata.getCourseLevel());
            }
        }
        else
        {
            if (workdata.getCourseLevel().equalsIgnoreCase("UG"))
            {
                ug=true;
                holder.lin_ug.setVisibility(View.VISIBLE);
                holder.ug_specialization.setText(workdata.getSpecialization());
                holder.ug_name.setText(workdata.getCollegeName());
                holder.ug_school_name.setText(workdata.getUniversity());
                holder.ug_degree_uni.setText(workdata.getDegree());

                holder.ug_marks.setText(workdata.getMarks());


                holder.cource_level.setText(workdata.getCourseLevel());

                holder.ug_year.setText(workdata.getCompletionYear());
            }
            if (workdata.getCourseLevel().equalsIgnoreCase("PG"))
            {
                pg=true;
                holder.lin_pg.setVisibility(View.VISIBLE);
                holder.pg_specialization.setText(workdata.getSpecialization());
                holder.pg_name.setText(workdata.getCollegeName());
                holder.pg_school_name.setText(workdata.getUniversity());
                holder.pg_degree_uni.setText(workdata.getDegree());

                holder.pg_marks.setText(workdata.getMarks());


                holder.cource_level.setText(workdata.getCourseLevel());

                holder.pg_year.setText(workdata.getCompletionYear());
            }

        }


        /*holder.cource_level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, level);
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (level.get(which).equalsIgnoreCase("10")) {
                            if (tenth) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setMessage("The course level 10 already exists. Please update information in the form below");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                    }
                                });
                                builder.setCancelable(false);
                                AlertDialog dialog2 = builder.create();
                                dialog2.show();
                            } else {

                                tenth=false;
                                Toast.makeText(context, ""+tenth, Toast.LENGTH_SHORT).show();
                                Toast.makeText(context, ""+twelth, Toast.LENGTH_SHORT).show();
                                Toast.makeText(context, ""+ug, Toast.LENGTH_SHORT).show();
                                Toast.makeText(context, ""+pg, Toast.LENGTH_SHORT).show();
                                holder.cource_level.setText("" + level.get(which));
                                if (holder.cource_level.getText().toString().equalsIgnoreCase("10")) {
                                    holder.lin_tenth.setVisibility(View.VISIBLE);
                                    holder.lin_twelth.setVisibility(View.GONE);
                                    holder.lin_ug.setVisibility(View.GONE);
                                    holder.lin_pg.setVisibility(View.GONE);
                                }
                            }
                        }


                        if (level.get(which).equalsIgnoreCase("12")) {
                            if (twelth) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setMessage("The course level 12 already exists. Please update information in the form below");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                builder.setCancelable(false);
                                AlertDialog dialog2 = builder.create();
                                dialog2.show();
                            } else {
                                twelth=false;
                                Toast.makeText(context, ""+tenth, Toast.LENGTH_SHORT).show();
                                Toast.makeText(context, ""+twelth, Toast.LENGTH_SHORT).show();
                                Toast.makeText(context, ""+ug, Toast.LENGTH_SHORT).show();
                                Toast.makeText(context, ""+pg, Toast.LENGTH_SHORT).show();
                                holder.cource_level.setText("" + level.get(which));
                                if (holder.cource_level.getText().toString().equalsIgnoreCase("12")) {
                                    holder.lin_tenth.setVisibility(View.GONE);
                                    holder.lin_twelth.setVisibility(View.VISIBLE);
                                    holder.lin_ug.setVisibility(View.GONE);
                                    holder.lin_pg.setVisibility(View.GONE);
                                }
                            }
                        }
                        if (level.get(which).equalsIgnoreCase("UG")) {
                            if (ug) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setMessage("The course level UG already exists. Please update information in the form below");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                builder.setCancelable(false);
                                AlertDialog dialog2 = builder.create();
                                dialog2.show();
                            } else {
                                ug=false;
                                Toast.makeText(context, ""+tenth, Toast.LENGTH_SHORT).show();
                                Toast.makeText(context, ""+twelth, Toast.LENGTH_SHORT).show();
                                Toast.makeText(context, ""+ug, Toast.LENGTH_SHORT).show();
                                Toast.makeText(context, ""+pg, Toast.LENGTH_SHORT).show();
                                holder.cource_level.setText("" + level.get(which));
                                if (holder.cource_level.getText().toString().equalsIgnoreCase("UG")) {
                                    holder.lin_tenth.setVisibility(View.GONE);
                                    holder.lin_twelth.setVisibility(View.GONE);
                                    holder.lin_ug.setVisibility(View.VISIBLE);
                                    holder.lin_pg.setVisibility(View.GONE);
                                }
                            }
                        }
                        if (level.get(which).equalsIgnoreCase("PG")) {
                            if (pg) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setMessage("The course level PG already exists. Please update information in the form below");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                builder.setCancelable(false);
                                AlertDialog dialog2 = builder.create();
                                dialog2.show();
                            } else {
                                pg=false;
                                Toast.makeText(context, ""+tenth, Toast.LENGTH_SHORT).show();
                                Toast.makeText(context, ""+twelth, Toast.LENGTH_SHORT).show();
                                Toast.makeText(context, ""+ug, Toast.LENGTH_SHORT).show();
                                Toast.makeText(context, ""+pg, Toast.LENGTH_SHORT).show();
                                holder.cource_level.setText("" + level.get(which));
                                if (holder.cource_level.getText().toString().equalsIgnoreCase("PG")) {
                                    holder.lin_tenth.setVisibility(View.GONE);
                                    holder.lin_twelth.setVisibility(View.GONE);
                                    holder.lin_ug.setVisibility(View.GONE);
                                    holder.lin_pg.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });*/

        holder.cource_level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, level);
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("fffff","10"+tenth);
                        Log.d("fffff","12"+twelth);
                        Log.d("fffff","ug"+ug);
                        Log.d("fffff","pg"+pg);

                        if (level.get(which).equalsIgnoreCase("10"))
                        {

                            if (tenth)
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setMessage("The course level 10th already exists. Please update information in the form below");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                builder.setCancelable(false);
                                AlertDialog dialog2 = builder.create();
                                dialog2.show();
                            }
                            else {
                                tenth=true;
                                if (holder.cource_level.getText().toString().equalsIgnoreCase("12"))
                                {
                                    twelth=false;
                                }
                                else if (holder.cource_level.getText().toString().equalsIgnoreCase("UG"))
                                {
                                    ug=false;
                                }
                                else if (holder.cource_level.getText().toString().equalsIgnoreCase("PG"))
                                {
                                    pg=false;
                                }
                                holder.cource_level.setText("" + level.get(which));
                                holder.lin_tenth.setVisibility(View.VISIBLE);
                                holder.lin_twelth.setVisibility(View.GONE);
                                holder.lin_ug.setVisibility(View.GONE);
                                holder.lin_pg.setVisibility(View.GONE);
                            }

                        }
                        else if (level.get(which).equalsIgnoreCase("12")) {
                            if (twelth) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setMessage("The course level 12th already exists. Please update information in the form below");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                builder.setCancelable(false);
                                AlertDialog dialog2 = builder.create();
                                dialog2.show();
                            } else{
                                twelth=true;
                                if (holder.cource_level.getText().toString().equalsIgnoreCase("10"))
                                {
                                tenth=false;
                                }
                                else if (holder.cource_level.getText().toString().equalsIgnoreCase("UG"))
                                {
                                    ug=false;
                                }
                                else if (holder.cource_level.getText().toString().equalsIgnoreCase("PG"))
                                {
                                    pg=false;
                                }

                                holder.cource_level.setText("" + level.get(which));
                                holder.lin_tenth.setVisibility(View.GONE);
                                holder.lin_twelth.setVisibility(View.VISIBLE);
                                holder.lin_ug.setVisibility(View.GONE);
                                holder.lin_pg.setVisibility(View.GONE);
                            }
                        } else if (level.get(which).equalsIgnoreCase("UG"))
                        {
                            if (ug)
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setMessage("The course level UG already exists. Please update information in the form below");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                builder.setCancelable(false);
                                AlertDialog dialog2 = builder.create();
                                dialog2.show();
                            }
                            else
                            {
                                ug=true;
                                if (holder.cource_level.getText().toString().equalsIgnoreCase("10"))
                                {
                                    tenth=false;
                                }
                                else if (holder.cource_level.getText().toString().equalsIgnoreCase("12"))
                                {
                                    twelth=false;
                                }
                                else if (holder.cource_level.getText().toString().equalsIgnoreCase("PG"))
                                {
                                    pg=false;
                                }
                                holder.cource_level.setText("" + level.get(which));
                                holder.lin_tenth.setVisibility(View.GONE);
                                holder.lin_twelth.setVisibility(View.GONE);
                                holder.lin_ug.setVisibility(View.VISIBLE);
                                holder.lin_pg.setVisibility(View.GONE);
                            }

                        } else if (level.get(which).equalsIgnoreCase("PG"))
                        {

                            if (pg)
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setMessage("The course level PG already exists. Please update information in the form below");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                builder.setCancelable(false);
                                AlertDialog dialog2 = builder.create();
                                dialog2.show();
                            }
                            else
                            {
                                pg=true;
                                if (holder.cource_level.getText().toString().equalsIgnoreCase("10"))
                                {
                                    tenth=false;
                                }
                                else if (holder.cource_level.getText().toString().equalsIgnoreCase("12"))
                                {
                                    twelth=false;
                                }
                                else if (holder.cource_level.getText().toString().equalsIgnoreCase("UG"))
                                {
                                    ug=false;
                                }
                                holder.cource_level.setText("" + level.get(which));
                                holder.lin_tenth.setVisibility(View.GONE);
                                holder.lin_twelth.setVisibility(View.GONE);
                                holder.lin_ug.setVisibility(View.GONE);
                                holder.lin_pg.setVisibility(View.VISIBLE);
                            }

                        }
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        holder.tenth_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Collections.reverse(years);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, years);
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        holder.tenth_year.setText(""+years.get(which));
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        holder.twelth_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.reverse(years);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, years);
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        holder.twelth_year.setText(""+years.get(which));
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        holder. ug_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.reverse(years);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, years);
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        holder.ug_year.setText(""+years.get(which));
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        holder.pg_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.reverse(years);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, years);
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        holder. pg_year.setText(""+years.get(which));
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        holder.btnsave.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (holder.cource_level.getText().toString().equalsIgnoreCase("10"))
        {
            if (isValid10(holder))
            {
                addeducation(holder,""+workdata.getDetailsId());

            }
        }
        if (holder.cource_level.getText().toString().equalsIgnoreCase("12"))
        {
            if (isValid12(holder))
            {
                addeducation(holder,""+workdata.getDetailsId());

            }
        } if (holder.cource_level.getText().toString().equalsIgnoreCase("UG"))
        {
            if (isValidUG(holder))
            {
                addeducation(holder,""+workdata.getEducationCollegeId());

            }
        }if (holder.cource_level.getText().toString().equalsIgnoreCase("PG"))
        {
            if (isValidPG(holder))
            {
                addeducation(holder,""+workdata.getEducationCollegeId());

            }
        }

        //Toast.makeText(context, ""+workdata.getEducationId(), Toast.LENGTH_SHORT).show();
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
                                if (holder.cource_level.getText().toString().equalsIgnoreCase("10"))
                                {
                                    if (isValid10(holder))
                                    {tenth=false;
                                        deleteData("1",""+workdata.getDetailsId());

                                    }
                                }
                                if (holder.cource_level.getText().toString().equalsIgnoreCase("12"))
                                {
                                    if (isValid12(holder))
                                    {
                                        twelth=false;
                                        deleteData("1",""+workdata.getDetailsId());

                                    }
                                } if (holder.cource_level.getText().toString().equalsIgnoreCase("UG"))
                                {
                                    if (isValidUG(holder))
                                    {
                                        ug=false;
                                        deleteData("2",""+workdata.getEducationCollegeId());

                                    }
                                }if (holder.cource_level.getText().toString().equalsIgnoreCase("PG"))
                                {
                                    if (isValidPG(holder))
                                    {
                                        pg=false;

                                        deleteData("2",""+workdata.getEducationCollegeId());

                                    }
                                }
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

    private void deleteData(String type,String edu_id) {

        progressDialog.show();
        JsonObject paramObject = new JsonObject();
        paramObject.addProperty("type", type);
        paramObject.addProperty("edu_id", edu_id);





        Log.d("dataaaa","-"+paramObject.toString());


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://eleganzit.online/testhost/users/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface myInterface = retrofit.create(RetrofitInterface.class);
        Call<EducationDeleteResponse> call=myInterface.deleteEducationData(paramObject);
        call.enqueue(new Callback<EducationDeleteResponse>() {
            @Override
            public void onResponse(Call<EducationDeleteResponse> call, Response<EducationDeleteResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful())
                {
                    if (response.body().getStatus().toString().equalsIgnoreCase("1"))
                    {
                        Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                        activity.finish();
                    }
                    else
                    {
                        Toast.makeText(context, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

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
LinearLayout lin_twelth,lin_tenth,lin_ug,lin_pg;
EditText cource_level,tenth_school_name,tenth_marks,subject,tenth_board,tenth_year;
        EditText ug_school_name,ug_degree_uni,ug_year,ug_name,ug_specialization,ug_marks;
        EditText twelth_school_name,twelth_year,twelth_board,twelth_marks;
        EditText pg_school_name,pg_degree_uni,pg_year,pg_name,pg_specialization,pg_marks;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            pg_specialization=itemView.findViewById(R.id.pg_specialization);
            pg_school_name=itemView.findViewById(R.id.pg_school_name);
            pg_degree_uni=itemView.findViewById(R.id.pg_degree_uni);
            pg_marks=itemView.findViewById(R.id.pg_marks);
            pg_name=itemView.findViewById(R.id.pg_name);
            pg_year=itemView.findViewById(R.id.pg_year);
            pg_specialization=itemView.findViewById(R.id.pg_specialization);
            lin_pg=itemView.findViewById(R.id.lin_pg);
            lin_tenth=itemView.findViewById(R.id.lin_tenth);
            lin_ug=itemView.findViewById(R.id.lin_ug);
            lin_twelth=itemView.findViewById(R.id.lin_twelth);
            ug_year=itemView.findViewById(R.id.ug_year);
            ug_school_name=itemView.findViewById(R.id.ug_school_name);
            ug_degree_uni=itemView.findViewById(R.id.ug_degree_uni);
            ug_marks=itemView.findViewById(R.id.ug_marks);
            ug_name=itemView.findViewById(R.id.ug_name);
            ug_specialization=itemView.findViewById(R.id.ug_specialization);
            ug_school_name=itemView.findViewById(R.id.ug_school_name);
            ug_marks=itemView.findViewById(R.id.ug_marks);
            ug_name=itemView.findViewById(R.id.ug_name);
            twelth_marks=itemView.findViewById(R.id.twelth_marks);
            twelth_school_name=itemView.findViewById(R.id.twelth_school_name);
            twelth_year=itemView.findViewById(R.id.twelth_year);
            twelth_board=itemView.findViewById(R.id.twelth_board);
            cource_level=itemView.findViewById(R.id.cource_level);
            delete=itemView.findViewById(R.id.delete);
            btnsave=itemView.findViewById(R.id.btnsave);
            tenth_school_name=itemView.findViewById(R.id.tenth_school_name);
            tenth_marks=itemView.findViewById(R.id.tenth_marks);
            subject=itemView.findViewById(R.id.twelth_stream);
            tenth_board=itemView.findViewById(R.id.tenth_board);
            tenth_year=itemView.findViewById(R.id.tenth_year);


        }
    }


    private void addeducation(MyViewHolder workdata,String idd)
    { progressDialog.show();
        JsonObject paramObject = new JsonObject();
        paramObject.addProperty("user_id", id);




        JsonObject paramObject2=new JsonObject();
        if (workdata.cource_level.getText().toString().equalsIgnoreCase("10"))
        {
            paramObject2.addProperty("details_id", idd);

            paramObject2.addProperty("course_level",workdata.cource_level.getText().toString());
            paramObject2.addProperty("type","school");
            paramObject2.addProperty("school_name",workdata.tenth_school_name.getText().toString());
            paramObject2.addProperty("completion_year",workdata.tenth_year.getText().toString());
            paramObject2.addProperty("board",workdata.tenth_board.getText().toString());
            paramObject2.addProperty("marks",workdata.tenth_marks.getText().toString());
            JsonArray jsonArray=new JsonArray();

            jsonArray.add(paramObject2);

            paramObject.add("edu_details", jsonArray);

        }
        if (workdata.cource_level.getText().toString().equalsIgnoreCase("12"))
        {
            paramObject2.addProperty("details_id", idd);

            paramObject2.addProperty("course_level",workdata.cource_level.getText().toString());
            paramObject2.addProperty("type","school");
            paramObject2.addProperty("school_name",workdata.twelth_school_name.getText().toString());
            paramObject2.addProperty("completion_year",workdata.twelth_year.getText().toString());
            paramObject2.addProperty("board",workdata.twelth_board.getText().toString());
            paramObject2.addProperty("subject",workdata.subject.getText().toString());
            paramObject2.addProperty("marks",workdata.twelth_marks.getText().toString());
            JsonArray jsonArray=new JsonArray();

            jsonArray.add(paramObject2);

            paramObject.add("edu_details", jsonArray);

        }
        if (workdata.cource_level.getText().toString().equalsIgnoreCase("UG"))
        {
            paramObject2.addProperty("education_college_id", idd);

            paramObject2.addProperty("course_level",workdata.cource_level.getText().toString());
            paramObject2.addProperty("type","college");
            paramObject2.addProperty("college_name",workdata.ug_name.getText().toString());
            paramObject2.addProperty("completion_year",workdata.ug_year.getText().toString());
            paramObject2.addProperty("degree",workdata.ug_degree_uni.getText().toString());
            paramObject2.addProperty("specialization",workdata.ug_specialization.getText().toString());
            paramObject2.addProperty("university",workdata.ug_school_name.getText().toString());
            paramObject2.addProperty("marks",workdata.ug_marks.getText().toString());
            JsonArray jsonArray=new JsonArray();

            jsonArray.add(paramObject2);

            paramObject.add("edu_details", jsonArray);

        }
        if (workdata.cource_level.getText().toString().equalsIgnoreCase("PG"))
        {
            paramObject2.addProperty("education_college_id", idd);

            paramObject2.addProperty("course_level",workdata.cource_level.getText().toString());
            paramObject2.addProperty("type","college");
            paramObject2.addProperty("college_name",workdata.pg_name.getText().toString());
            paramObject2.addProperty("completion_year",workdata.pg_year.getText().toString());
            paramObject2.addProperty("degree",workdata.pg_degree_uni.getText().toString());
            paramObject2.addProperty("specialization",workdata.pg_specialization.getText().toString());
            paramObject2.addProperty("university",workdata.pg_school_name.getText().toString());
            paramObject2.addProperty("marks",workdata.pg_marks.getText().toString());
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
            public void onFailure(Call<EducationUpdateResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "Server and Internet Error", Toast.LENGTH_SHORT).show();

            }
        });


    }
    private boolean isValid10(MyViewHolder holder) {
        if (holder.cource_level.getText().toString().trim().equals("")) {


            Toast.makeText(context, "Please select course level", Toast.LENGTH_SHORT).show();

            //  name.requestFocus();

            return false;

        }

        else if (holder.tenth_school_name.getText().toString().trim().equals("")) {

            Toast.makeText(context, "Please enter school name", Toast.LENGTH_SHORT).show();


            holder.tenth_school_name.requestFocus();
            return false;


        }else if (holder.tenth_year.getText().toString().trim().equals("")) {

            Toast.makeText(context, "Please select year", Toast.LENGTH_SHORT).show();

            return false;


        }else if (holder.tenth_board.getText().toString().trim().equals("")) {

            Toast.makeText(context, "Please enter board", Toast.LENGTH_SHORT).show();


            holder.tenth_board.requestFocus();
            return false;


        }else if (holder.tenth_marks.getText().toString().trim().equals("")) {

            Toast.makeText(context, "Please enter marks", Toast.LENGTH_SHORT).show();


            holder. tenth_marks.requestFocus();
            return false;


        }
        return true;

    }
    private boolean isValid12(MyViewHolder holder) {

        if (holder.cource_level.getText().toString().trim().equals("")) {


            Toast.makeText(context, "Please select course level", Toast.LENGTH_SHORT).show();

            //  name.requestFocus();

            return false;
        }

        else if (holder.twelth_school_name.getText().toString().trim().equals("")) {

            Toast.makeText(context, "Please enter school name", Toast.LENGTH_SHORT).show();


            holder.twelth_school_name.requestFocus();

            return false;
        }else if (holder.twelth_year.getText().toString().trim().equals("")) {

            Toast.makeText(context, "Please select year", Toast.LENGTH_SHORT).show();



            return false;
        }else if (holder.twelth_board.getText().toString().trim().equals("")) {

            Toast.makeText(context, "Please enter board", Toast.LENGTH_SHORT).show();


            holder.twelth_board.requestFocus();

            return false;
        }
        else if (holder.subject.getText().toString().trim().equals("")) {

            Toast.makeText(context, "Please enter stream", Toast.LENGTH_SHORT).show();


            holder.subject.requestFocus();

            return false;
        }


        else if (holder.twelth_marks.getText().toString().trim().equals("")) {

            Toast.makeText(context, "Please enter marks", Toast.LENGTH_SHORT).show();


            holder.twelth_marks.requestFocus();

            return false;
        }
        return true;
    }
    private boolean isValidUG(MyViewHolder holder) {
        if (holder.cource_level.getText().toString().trim().equals("")) {


            Toast.makeText(context, "Please select course level", Toast.LENGTH_SHORT).show();

            //  name.requestFocus();

            return false;
        }

        else if (holder.ug_school_name.getText().toString().trim().equals("")) {

            Toast.makeText(context, "Please enter university name", Toast.LENGTH_SHORT).show();


            holder.ug_school_name.requestFocus();

            return false;
        }else if (holder.ug_degree_uni.getText().toString().trim().equals("")) {

            Toast.makeText(context, "Please enter degree name", Toast.LENGTH_SHORT).show();
            holder.ug_degree_uni.requestFocus();


            return false;
        }else if (holder.ug_year.getText().toString().trim().equals("")) {

            Toast.makeText(context, "Please select year", Toast.LENGTH_SHORT).show();


            holder.ug_year.requestFocus();

            return false;
        }
        else if (holder.ug_name.getText().toString().trim().equals("")) {

            Toast.makeText(context, "Please enter college name", Toast.LENGTH_SHORT).show();


            holder.ug_name.requestFocus();

            return false;
        }
        else if (holder.ug_marks.getText().toString().trim().equals("")) {

            Toast.makeText(context, "Please enter marks", Toast.LENGTH_SHORT).show();


            holder.ug_marks.requestFocus();

            return false;
        }



        return true;

    }

    private boolean isValidPG(MyViewHolder holder) {
        if (holder.cource_level.getText().toString().trim().equals("")) {


            Toast.makeText(context, "Please select course level", Toast.LENGTH_SHORT).show();

            //  name.requestFocus();

            return false;
        }

        else if (holder.pg_school_name.getText().toString().trim().equals("")) {

            Toast.makeText(context, "Please enter university name", Toast.LENGTH_SHORT).show();


            holder. pg_school_name.requestFocus();

            return false;
        }else if (holder.pg_degree_uni.getText().toString().trim().equals("")) {

            Toast.makeText(context, "Please enter degree name", Toast.LENGTH_SHORT).show();
            holder.pg_degree_uni.requestFocus();


            return false;
        }else if (holder.pg_year.getText().toString().trim().equals("")) {

            Toast.makeText(context, "Please select year", Toast.LENGTH_SHORT).show();


            holder.pg_year.requestFocus();

            return false;
        }
        else if (holder.pg_name.getText().toString().trim().equals("")) {

            Toast.makeText(context, "Please enter college name", Toast.LENGTH_SHORT).show();


            holder.pg_name.requestFocus();

            return false;
        }
        else if (holder.pg_marks.getText().toString().trim().equals("")) {

            Toast.makeText(context, "Please enter marks", Toast.LENGTH_SHORT).show();


            holder.pg_marks.requestFocus();

            return false;
        }



        return true;

    }
    

}
