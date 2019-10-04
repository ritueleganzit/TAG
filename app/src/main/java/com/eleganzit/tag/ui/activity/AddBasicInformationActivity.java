package com.eleganzit.tag.ui.activity;

import android.content.Intent;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.R;
import com.eleganzit.tag.adapter.CollegeListAdapter;
import com.eleganzit.tag.utils.SwipeController;
import com.eleganzit.tag.utils.SwipeControllerActions;

import java.util.ArrayList;

public class AddBasicInformationActivity extends AppCompatActivity {

    TextView next,addmore;
    AddCourseAdapter addCourseAdapter;
    RecyclerView rc_course_data;
    ArrayList numdata=new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_basic_information);
        next=findViewById(R.id.next);
        addCourseAdapter=new AddCourseAdapter();
        rc_course_data=findViewById(R.id.rc_course_data);
        addmore=findViewById(R.id.addmore);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddBasicInformationActivity.this,AddPersonalInformationActivity.class));
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
               // finish();
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

                if (numdata.size()<=1)
                {
                    numdata.add("");
                    rc_course_data.setAdapter(new AddCourseAdapter());

                    if (numdata.size()>1)
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
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numdata.remove(i);
                    notifyDataSetChanged();
                    addmore.setVisibility(View.VISIBLE);

                }
            });
        }

        @Override
        public int getItemCount() {
            return numdata.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {


            public MyViewHolder(@NonNull View itemView) {
                super(itemView);


            }
        }

        public void removeItem(int position) {
            numdata.remove(position);
            notifyItemRemoved(position);
            addmore.setVisibility(View.VISIBLE);

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
