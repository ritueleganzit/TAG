package com.eleganzit.tag.ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.R;
import com.eleganzit.tag.SelectedCourseActivity;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.GetCoursesResponse;
import com.eleganzit.tag.model.GetSpecialization;
import com.eleganzit.tag.model.GetSpecializationResponse;
import com.eleganzit.tag.utils.UserLoggedInSession;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollegeSelectSpecializationActivity extends AppCompatActivity {
    TextView next;
    EditText ed_course,e_sp;
    ProgressDialog progressDialog;
    UserLoggedInSession userLoggedInSession;
    List<String> getSpecializations;
    RecyclerView courserc;
    TextView txt_c1,txt_c2,txt_c3,txt_c4,txt_c5,txt_c6;
    public static  GetSpecialization                 getSpecialization=new GetSpecialization();

    List<String> getSpecializationsCourse;
    List<GetSpecialization> getSpecializationResponseList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_specialization);
        e_sp=findViewById(R.id.ed_specializationsCourse);
        ed_course=findViewById(R.id.ed_course);
        courserc=findViewById(R.id.courserc);
        /*txt_c1=findViewById(R.id.txt_c1);
        txt_c2=findViewById(R.id.txt_c2);
        txt_c3=findViewById(R.id.txt_c3);
        txt_c4=findViewById(R.id.txt_c4);
        txt_c5=findViewById(R.id.txt_c5);
        txt_c6=findViewById(R.id.txt_c6);
*/
        ed_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

if (getSpecializations.size()>0)
{
    showCourseDialog();

}
else
{
    getCourses();
}
            }
        }); e_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ed_course.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(CollegeSelectSpecializationActivity.this, "Please Select Course", Toast.LENGTH_SHORT).show();
                }
                else {
                    showSpecializationsCourseDialog();

                }
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        userLoggedInSession=new UserLoggedInSession(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        next=findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(CollegeSelectSpecializationActivity.this,TopCollegesActivity.class));


                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);


            }
        });


        getCourses();
    }

    public void getCourses(){
        getSpecializations=new ArrayList<>();
        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
        Call<GetCoursesResponse> call=myInterface.getCourses();
        call.enqueue(new Callback<GetCoursesResponse>() {
            @Override
            public void onResponse(Call<GetCoursesResponse> call, Response<GetCoursesResponse> response) {
                progressDialog.dismiss();


                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {


                        for(int i=0;i<response.body().getData().size();i++){

                            ed_course.setText(response.body().getData().get(0).getCourceName());

                            getSpecializations.add(response.body().getData().get(i).getCourceName());
                            Log.d("zfsdfsf",""+response.body().getData().get(i).getCourceName());


                        }
                        setGetSpecializations(getSpecializations.get(0));
                        courserc.setAdapter(new CourseAdapter(getSpecializations, CollegeSelectSpecializationActivity.this));


                      
                    }
                    else
                    {

                        Toast.makeText(CollegeSelectSpecializationActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
                else
                {
                    Toast.makeText(CollegeSelectSpecializationActivity.this, ""+response.message(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<GetCoursesResponse> call, Throwable t) {
                progressDialog.dismiss();

                Toast.makeText(CollegeSelectSpecializationActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();
            }
        });


    }

    void showCourseDialog() {

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, getSpecializations);

        final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));

        builder.setSingleChoiceItems(adapter, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                ed_course.setText(getSpecializations.get(i));
                setGetSpecializations(getSpecializations.get(i));

            }
        });
        if (getSpecializations.size()>0)
        builder.show();

    }  void showSpecializationsCourseDialog() {

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, getSpecializationsCourse);

        final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));

        builder.setSingleChoiceItems(adapter, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                e_sp.setText(getSpecializationsCourse.get(i));
                getSpecialization.setCourceName(getSpecializationResponseList.get(i).getCourceName());
                getSpecialization.setCirrculum(getSpecializationResponseList.get(i).getCirrculum());
                getSpecialization.setEligibility(getSpecializationResponseList.get(i).getEligibility());
                getSpecialization.setOverview(getSpecializationResponseList.get(i).getOverview());
               // setGetSpecializations(getSpecializationsCourse.get(i));

            }
        });
        if (getSpecializationsCourse.size()>0)
        builder.show();

    }
    public void setGetSpecializations(String coursename){
        getSpecializationsCourse=new ArrayList<>();
        getSpecializationResponseList=new ArrayList<>();
        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
        Call<GetSpecializationResponse> call=myInterface.getSpecialization(coursename);
        call.enqueue(new Callback<GetSpecializationResponse>() {
            @Override
            public void onResponse(Call<GetSpecializationResponse> call, Response<GetSpecializationResponse> response) {
                progressDialog.dismiss();


                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {
                        getSpecializationResponseList.addAll(response.body().getData());

                        for(int i=0;i<response.body().getData().size();i++){


                            e_sp.setText(""+response.body().getData().get(0).getSpecializationName());
                            getSpecialization.setCourceName(response.body().getData().get(0).getCourceName());
                            getSpecialization.setCirrculum(response.body().getData().get(0).getCirrculum());
                            getSpecialization.setEligibility(response.body().getData().get(0).getEligibility());
                            getSpecialization.setOverview(response.body().getData().get(0).getOverview());
                            getSpecializationsCourse.add(response.body().getData().get(i).getSpecializationName());
                        }



                    }
                    else
                    {
                        e_sp.setText("");


                        Toast.makeText(CollegeSelectSpecializationActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
                else
                {
                    e_sp.setText("");



                    Toast.makeText(CollegeSelectSpecializationActivity.this, "No Data", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<GetSpecializationResponse> call, Throwable t) {
                progressDialog.dismiss();
                e_sp.setText("");


                Toast.makeText(CollegeSelectSpecializationActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder>
    {
        List<String> getSpecializations;
        Context context;
        Activity activity;
        public CourseAdapter( List<String> getSpecializations,Context context) {

            this.getSpecializations = getSpecializations;
            this.context = context;
            activity = (Activity) context;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_course,viewGroup,false);
            MyViewHolder myViewHolder=new MyViewHolder(v);

            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {
holder.txt_c1.setText(getSpecializations.get(i));
holder.txt_c1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ed_course.setText(""+getSpecializations.get(i));
        setGetSpecializations(getSpecializations.get(i));
    }
});

        }

        @Override
        public int getItemCount() {
            return getSpecializations.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView txt_c1;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                txt_c1=itemView.findViewById(R.id.txt_c1);


            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        finish();
    }

}
