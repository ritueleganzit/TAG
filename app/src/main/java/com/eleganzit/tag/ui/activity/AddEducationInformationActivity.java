package com.eleganzit.tag.ui.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.R;

import java.util.ArrayList;

public class AddEducationInformationActivity extends AppCompatActivity {
    ArrayList numdata=new ArrayList();
    ArrayList numdata2=new ArrayList();
    RecyclerView rc_edu,rc_competitive;

    TextView addeducation,addmorecompetitive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_education_information);

        rc_edu=findViewById(R.id.rc_edu);
        rc_competitive=findViewById(R.id.rc_competitive);
        addeducation=findViewById(R.id.addeducation);
        addmorecompetitive=findViewById(R.id.addmorecompetitive);
        addeducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numdata.size()<=1)
                {
                    numdata.add("");
                    rc_edu.setAdapter(new AddCourseAdapter());
                    if (numdata.size()>1)
                    {
                        addeducation.setVisibility(View.GONE);
                    }
                }
                else {
                    Toast.makeText(AddEducationInformationActivity.this, "Max 3 data", Toast.LENGTH_SHORT).show();

                }
            }
        });addmorecompetitive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numdata2.size()<=1)
                {
                    numdata2.add("");
                    rc_competitive.setAdapter(new AddCompetitiveAdapter());
                    if (numdata2.size()>1)
                    {
                        addmorecompetitive.setVisibility(View.GONE);
                    }
                }
                else {
                    Toast.makeText(AddEducationInformationActivity.this, "Max 3 data", Toast.LENGTH_SHORT).show();

                }
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });
    }

    public class AddCourseAdapter extends RecyclerView.Adapter<AddCourseAdapter.MyViewHolder> {
        public AddCourseAdapter() {
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.addeducation,viewGroup,false);
            AddCourseAdapter.MyViewHolder myViewHolder=new AddCourseAdapter.MyViewHolder(v);

            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull AddCourseAdapter.MyViewHolder myViewHolder, final int i) {

            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numdata.remove(i);
                    notifyDataSetChanged();
                    addeducation.setVisibility(View.VISIBLE);
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
    }


    public class AddCompetitiveAdapter extends RecyclerView.Adapter<AddCompetitiveAdapter.MyViewHolder> {
        public AddCompetitiveAdapter() {
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.addcompetitive,viewGroup,false);
            AddCompetitiveAdapter.MyViewHolder myViewHolder=new AddCompetitiveAdapter.MyViewHolder(v);

            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull AddCompetitiveAdapter.MyViewHolder myViewHolder, final int i) {

            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numdata2.remove(i);
                    notifyDataSetChanged();
                    addmorecompetitive.setVisibility(View.VISIBLE);

                }
            });
        }

        @Override
        public int getItemCount() {
            return numdata2.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {


            public MyViewHolder(@NonNull View itemView) {
                super(itemView);


            }
        }
    }


}
