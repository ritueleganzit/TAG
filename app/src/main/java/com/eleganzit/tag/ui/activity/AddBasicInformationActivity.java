package com.eleganzit.tag.ui.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.LoginActivity;
import com.eleganzit.tag.R;
import com.eleganzit.tag.adapter.AppliedAdapter;
import com.eleganzit.tag.adapter.CollegeListAdapter;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.AppliedCollegeListResponse;
import com.eleganzit.tag.model.ApplyCategory;
import com.eleganzit.tag.model.QuestionAnsResponse;
import com.eleganzit.tag.model.appliedcollege.ApplyCollegeMobileResponse;
import com.eleganzit.tag.utils.HideKeyBoard;
import com.eleganzit.tag.utils.MyNestedScrollView;
import com.eleganzit.tag.utils.SwipeController;
import com.eleganzit.tag.utils.SwipeControllerActions;
import com.eleganzit.tag.utils.UserLoggedInSession;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AddBasicInformationActivity extends AppCompatActivity {
    private int edit_position;
    private View view;
    boolean isData=false;
    private boolean add = false;
    private Paint p = new Paint();
    TextView next,addmore;
    String stateArrayList[]={"Male","Female"};
    String categorys[]={"Test","Test1"};
    MyNestedScrollView nessted;
ArrayList<ApplyCategory> categorydata=new ArrayList();
    EditText first_name,middle_name,last_name,d_o_b,gender,category,applied_course_name,applied_specialization_name;
    AddCourseAdapter addCourseAdapter;
    RecyclerView rc_course_data;
    ArrayList numdata=new ArrayList();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_basic_information);
        next=findViewById(R.id.next);
        sharedPreferences=getSharedPreferences("dataapply",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        addCourseAdapter=new AddCourseAdapter();
        nessted=findViewById(R.id.nessted);
        rc_course_data=findViewById(R.id.rc_course_data);
        category=findViewById(R.id.category);
        d_o_b=findViewById(R.id.d_o_b);
        last_name=findViewById(R.id.last_name);
        middle_name=findViewById(R.id.middle_name);
        first_name=findViewById(R.id.first_name);
        applied_course_name=findViewById(R.id.applied_course_name);
        gender=findViewById(R.id.gender);
        applied_specialization_name=findViewById(R.id.applied_specialization_name);
        addmore=findViewById(R.id.addmore);
        HideKeyBoard.setupUI(nessted, AddBasicInformationActivity.this);
        addCourseAdapter=new AddCourseAdapter();
        rc_course_data.setAdapter(addCourseAdapter);

        d_o_b.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        final Calendar c = Calendar.getInstance();
      int  mYear = c.get(Calendar.YEAR);
        int  mMonth = c.get(Calendar.MONTH);
        int   mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(AddBasicInformationActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {


                        int month= monthOfYear+1;
                        String fm=""+month;
                        String fd=""+dayOfMonth;
                        if(month<10){
                            fm ="0"+month;
                        }
                        if (dayOfMonth<10){
                            fd="0"+dayOfMonth;
                        }
                        d_o_b.setText(fd + "-" + (fm) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
});
gender.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        final ListAdapter adapter = new ArrayAdapter(AddBasicInformationActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, stateArrayList);

        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(new ContextThemeWrapper(AddBasicInformationActivity.this, R.style.AlertDialogCustom));

        builder.setSingleChoiceItems(adapter, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();


                gender.setText(stateArrayList[i]);




            }
        });
        builder.show();
    }
});

category.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        final ListAdapter adapter = new ArrayAdapter(AddBasicInformationActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, categorys);

        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(new ContextThemeWrapper(AddBasicInformationActivity.this, R.style.AlertDialogCustom));

        builder.setSingleChoiceItems(adapter, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();


                category.setText(categorys[i]);




            }
        });
        builder.show();
    }
});

  findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });

        addmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (categorydata.size()<=1)
                {
                    categorydata.add(new ApplyCategory());
                    addCourseAdapter.notifyDataSetChanged();
                    if (categorydata.size()>1)
                    {
                        addmore.setVisibility(View.GONE);
                    }
                }
                else {

                    //Toast.makeText(AddBasicInformationActivity.this, "Max 3 data", Toast.LENGTH_SHORT).show();

                }

               // finish();
            }
        });
       // initSwipe();
        //addData();




        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid())
                {

                    if (categorydata.size()>0)
                    {
                        for (int i=0;i<categorydata.size();i++) {
                            if ((categorydata.get(i).getApplied_course_name().equalsIgnoreCase("")) || (categorydata.get(i).getApplied_specialization_name().equalsIgnoreCase("")))
                            {
                                Log.d("eerrrr","if"+i);

                                isData=true;
                                Toast.makeText(AddBasicInformationActivity.this, "Please insert  data", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            else {
                                isData=false;
                            }

                        }

                        if (!isData)
                        {

                            for (int j = 0; j < categorydata.size(); j++) {

                                Log.d("asdad", " " + categorydata.get(j).getApplied_course_name() + System.getProperty("line.separator"));

                            }

                            editor.putString("first_name", first_name.getText().toString());
                            editor.putString("middle_name", middle_name.getText().toString());
                            editor.putString("last_name", last_name.getText().toString());
                            editor.putString("d_o_b", d_o_b.getText().toString());
                            editor.putString("gender", gender.getText().toString());
                            editor.putString("category", category.getText().toString());
                            editor.putString("applied_course_name", applied_course_name.getText().toString());
                            editor.putString("applied_specialization_name", applied_specialization_name.getText().toString());
                            editor.putString("applied_specialization_name", applied_specialization_name.getText().toString());
                            Gson gson = new Gson();
                            String jsonCars = gson.toJson(categorydata);
                            editor.putString("jsoncars", jsonCars);
                            editor.commit();
                            startActivity(new Intent(AddBasicInformationActivity.this, AddPersonalInformationActivity.class)


                            );
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);



                        }


                    }

                    else
                    {
                        editor.putString("first_name", first_name.getText().toString());
                        editor.putString("middle_name", middle_name.getText().toString());
                        editor.putString("last_name", last_name.getText().toString());
                        editor.putString("d_o_b", d_o_b.getText().toString());
                        editor.putString("gender", gender.getText().toString());
                        editor.putString("category", category.getText().toString());
                        editor.putString("applied_course_name", applied_course_name.getText().toString());
                        editor.putString("applied_specialization_name", applied_specialization_name.getText().toString());
                        editor.putString("applied_specialization_name", applied_specialization_name.getText().toString());

                        editor.commit();
                        startActivity(new Intent(AddBasicInformationActivity.this, AddPersonalInformationActivity.class)


                        );
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        finish();
                    }


                }


                /*
                 */


                // finish();
            }
        });

    }

    public boolean isValid() {

        if (first_name.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please Enter First Name", Toast.LENGTH_SHORT).show();

            first_name.requestFocus();

            return false;
        }else if (middle_name.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please Enter Middle Name", Toast.LENGTH_SHORT).show();

            middle_name.requestFocus();

            return false;
        }
        else if (last_name.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please Enter Last Name", Toast.LENGTH_SHORT).show();

            last_name.requestFocus();

            return false;
        } else if (d_o_b.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please Select Date of Birth", Toast.LENGTH_SHORT).show();

            d_o_b.requestFocus();

            return false;
        } else if (gender.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please Select Gender", Toast.LENGTH_SHORT).show();

            gender.requestFocus();

            return false;
        }else if (category.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please Select Category", Toast.LENGTH_SHORT).show();

            category.requestFocus();

            return false;
        }
else if (applied_course_name.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please enter course name", Toast.LENGTH_SHORT).show();

            applied_course_name.requestFocus();

            return false;
        }


else if (applied_specialization_name.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please enter specialization name", Toast.LENGTH_SHORT).show();

            applied_specialization_name.requestFocus();

            return false;
        }



        return true;
    }

    private void initSwipe(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT){
                    addCourseAdapter.removeItem(position);
                } else {
                    removeView();
                   /* edit_position = position;
                    alertDialog.setTitle("Edit Country");
                    et_country.setText(countries.get(position));
                    alertDialog.show();*/
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Bitmap icon;
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if(dX > 0){
                        p.setColor(Color.parseColor("#388E3C"));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX,(float) itemView.getBottom());
                        c.drawRect(background,p);
                        Drawable drawable = ContextCompat.getDrawable(AddBasicInformationActivity.this,R.drawable.ic_close);
                        icon = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width ,(float) itemView.getTop() + width,(float) itemView.getLeft()+ 2*width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    } else {
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background,p);
                        Drawable drawable = ContextCompat.getDrawable(AddBasicInformationActivity.this,R.drawable.ic_close);
                        icon = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX/8, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rc_course_data);
    }
    private void removeView(){
        if(view.getParent()!=null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }
        public class AddCourseAdapter extends RecyclerView.Adapter<AddCourseAdapter.MyViewHolder> {
        public AddCourseAdapter() {
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.addcourse,viewGroup,false);
            MyViewHolder myViewHolder=new MyViewHolder(v);

            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
            myViewHolder.ed_course.setText(categorydata.get(i).getApplied_course_name());
            myViewHolder.ed_sp.setText(categorydata.get(i).getApplied_specialization_name());

            myViewHolder.deleteimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    categorydata.remove(i);
                    notifyDataSetChanged();
                    addmore.setVisibility(View.VISIBLE);



                }
            });
        }

        @Override
        public int getItemCount() {
            return categorydata.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
EditText ed_course,ed_sp;
ImageView deleteimg;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                ed_course=itemView.findViewById(R.id.ed_course);
                deleteimg=itemView.findViewById(R.id.deleteimg);
                ed_sp=itemView.findViewById(R.id.ed_sp);
                ed_course.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        categorydata.get(getAdapterPosition()).setApplied_course_name(ed_course.getText().toString());
                        categorydata.get(getAdapterPosition()).setApplied_specialization_name(ed_sp.getText().toString());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                ed_sp.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        categorydata.get(getAdapterPosition()).setApplied_specialization_name(ed_sp.getText().toString());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

            }
        }

       /* public void removeItem(int position) {
            numdata.remove(position);
            notifyItemRemoved(position);
            addmore.setVisibility(View.VISIBLE);

        }*/
            public void removeItem(int position) {
                numdata.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, numdata.size());
            }
        public void restoreItem(String item, int position) {
            numdata.add(position, item);
            notifyItemInserted(position);
        }

        public ArrayList<String> getData() {
            return numdata;
        }
    }
}
