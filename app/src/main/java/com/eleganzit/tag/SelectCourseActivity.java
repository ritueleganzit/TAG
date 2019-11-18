package com.eleganzit.tag;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.CoursesData;
import com.eleganzit.tag.model.GetCoursesData;
import com.eleganzit.tag.model.GetCoursesResponse;
import com.eleganzit.tag.model.GetSpecialization;
import com.eleganzit.tag.model.LoginResponse;
import com.eleganzit.tag.model.dropdowndata.DropDownListResponse;
import com.eleganzit.tag.ui.activity.AddEducationPreference;
import com.eleganzit.tag.utils.UserLoggedInSession;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectCourseActivity extends AppCompatActivity {
    private ArrayList<String> coursename;
    private ArrayList<String> couseid;
    TextView next,txt_c1,txt_c2,txt_c3,txt_c4,txt_c5,txt_c6;
    List<String> coursesArrayList=new ArrayList<>();
    List<CoursesData> coursesList=new ArrayList<>();
    String course_name,course_overview,course_specialization,course_eligibility;
    int course_id;
    EditText ed_course;
    ProgressDialog progressDialog;
    UserLoggedInSession userLoggedInSession;
    public static CoursesData coursesData;
    public static CoursesData coursesData1;
    public static CoursesData coursesData2;
    public static CoursesData coursesData3;
    public static CoursesData coursesData4;
    public static CoursesData coursesData5;
    public static CoursesData coursesData6;

    GetCoursesData getCoursesData=new GetCoursesData();
RecyclerView courserc;
    List<GetCoursesData> getCoursesResponseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course);

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
        courserc=findViewById(R.id.courserc);

        ed_course=findViewById(R.id.ed_course);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!ed_course.getText().toString().isEmpty()){

                  //  coursesData=new CoursesData(course_id,course_name,course_overview,course_specialization,course_eligibility);
                    startActivity(new Intent(SelectCourseActivity.this,SelectedCourseActivity.class)
                            .putExtra("course_id",course_id)
                            .putExtra("course_name",course_name+"")

                    );
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

                }
                else
                {
                    Toast.makeText(SelectCourseActivity.this, "Please select course", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ed_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCourseDialog();
            }
        });

        getPref();



    }


    public void getCourses(){
        getCoursesResponseList=new ArrayList<>();
        progressDialog.show();
        RetrofitInterface myInterface = RetrofitAPI.getRetrofitN().create(RetrofitInterface.class);
        Call<GetCoursesResponse> call=myInterface.getCourses();
        call.enqueue(new Callback<GetCoursesResponse>() {
            @Override
            public void onResponse(Call<GetCoursesResponse> call, Response<GetCoursesResponse> response) {
                progressDialog.dismiss();


                if (response.isSuccessful()) {

                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {

if (response.body().getData()!=null)
{
    for(int i=0;i<response.body().getData().size();i++){

        CoursesData coursesData=new CoursesData(response.body().getData().get(i).getCourceId(),response.body().getData().get(i).getCourceName(),response.body().getData().get(i).getOverview(),response.body().getData().get(i).getSpecialization(),response.body().getData().get(i).getEligibility());

        coursesList.add(coursesData);

        coursesArrayList.add(response.body().getData().get(i).getCourceName());
       // courserc.setAdapter(new CourseAdapter(coursesArrayList,SelectCourseActivity.this));

    }

    getCoursesResponseList.addAll(response.body().getData());




}

                    }
                    else
                    {

                        Toast.makeText(SelectCourseActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
                else
                {
                    Toast.makeText(SelectCourseActivity.this, ""+response.message(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<GetCoursesResponse> call, Throwable t) {
                progressDialog.dismiss();

                Toast.makeText(SelectCourseActivity.this, "Server and Internet Error", Toast.LENGTH_SHORT).show();
            }
        });


    }

    void showCourseDialog() {

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, coursename);

        final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));

        builder.setSingleChoiceItems(adapter, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                ed_course.setText(coursename.get(i));
                course_id=coursesList.get(i).getId();
                course_name=coursesList.get(i).getTitle();
                /*getCoursesData.setCourceName(getCoursesResponseList.get(i).getCourceName());
                getCoursesData.setCourceDescription(getCoursesResponseList.get(i).getCourceDescription());
                getCoursesData.setEligibility(getCoursesResponseList.get(i).getEligibility());
                getCoursesData.setOverview(getCoursesResponseList.get(i).getOverview());
                getCoursesData.setSpecialization(getCoursesResponseList.get(i).getSpecialization());
                getCoursesData.setCourceId(getCoursesResponseList.get(i).getCourceId());
                course_id=coursesList.get(i).getId();
                course_name=coursesList.get(i).getTitle();
                course_overview=coursesList.get(i).getCourse_overview();
                course_specialization=coursesList.get(i).getCourse_specialization();
                course_eligibility=coursesList.get(i).getCourse_eligibility();*/

            }
        });
        builder.show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder>
    {
        List<CoursesData> getSpecializations;
        Context context;
        Activity activity;
        public CourseAdapter( List<CoursesData> getSpecializations,Context context) {

            this.getSpecializations = getSpecializations;
            this.context = context;
            activity = (Activity) context;
        }

        @NonNull
        @Override
        public CourseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_course,viewGroup,false);
            CourseAdapter.MyViewHolder myViewHolder=new CourseAdapter.MyViewHolder(v);

            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull final CourseAdapter.MyViewHolder holder, final int i) {

         final CoursesData coursesData=getSpecializations.get(i);
            holder.txt_c1.setText(coursesData.getTitle());
            holder.txt_c1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ed_course.setText(""+coursesData.getTitle());
                    course_id=coursesData.getId();
                    course_name=coursesData.getTitle();

                   /* getCoursesData.setCourceName(getCoursesResponseList.get(i).getCourceName());
                    getCoursesData.setCourceDescription(getCoursesResponseList.get(i).getCourceDescription());
                    getCoursesData.setEligibility(getCoursesResponseList.get(i).getEligibility());
                    getCoursesData.setOverview(getCoursesResponseList.get(i).getOverview());
                    getCoursesData.setSpecialization(getCoursesResponseList.get(i).getSpecialization());
                    getCoursesData.setCourceId(getCoursesResponseList.get(i).getCourceId());
                    course_id=coursesList.get(i).getId();
                    course_name=coursesList.get(i).getTitle();
                    course_overview=coursesList.get(i).getCourse_overview();
                    course_specialization=coursesList.get(i).getCourse_specialization();
                    course_eligibility=coursesList.get(i).getCourse_eligibility();*/
                }
            });

        }

        @Override
        public int getItemCount() {
            if (getSpecializations.size()>6)
            {
                return 6;
            }
            else {
                return     getSpecializations.size();
            }

        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView txt_c1;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                txt_c1=itemView.findViewById(R.id.txt_c1);


            }
        }
    }


    public void getPref(){
        progressDialog.show();


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

                    if (response.body().getData().getCourseList()!=null)
                {

                    for (int i=0;i<response.body().getData().getCourseList().size();i++)
                    {
                        couseid.add(""+response.body().getData().getCourseList().get(i).getCourceId());
                        coursename.add(response.body().getData().getCourseList().get(i).getCourseName());
                        CoursesData coursesData=new CoursesData(response.body().getData().getCourseList().get(i).getCourseName(),response.body().getData().getCourseList().get(i).getCourceId());

                        coursesList.add(coursesData);

                        coursesArrayList.add(response.body().getData().getCourseList().get(i).getCourseName());

                        courserc.setAdapter(new CourseAdapter(coursesList,SelectCourseActivity.this));
                    }
                }
                }
            }

            @Override
            public void onFailure(Call<DropDownListResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(SelectCourseActivity.this, "Server or Internet error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
