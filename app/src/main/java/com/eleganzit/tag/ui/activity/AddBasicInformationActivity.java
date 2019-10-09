package com.eleganzit.tag.ui.activity;

import android.content.Intent;
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
    private int edit_position;
    private View view;
    private boolean add = false;
    private Paint p = new Paint();
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
        initSwipe();
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
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

           /* myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numdata.remove(i);
                    notifyDataSetChanged();
                    addmore.setVisibility(View.VISIBLE);

                }
            });*/
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
