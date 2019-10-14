package com.eleganzit.tag.ui.activity.school;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.R;
import com.eleganzit.tag.ui.activity.AddBasicInformationActivity;
import com.eleganzit.tag.utils.HideKeyBoard;

import java.util.Calendar;

public class ApplySchoolBasicInfoActivity extends AppCompatActivity {

    String stateArrayList[]={"Male","Female"};
    String categorys[]={"Test","Test1"};
    TextView next;
    LinearLayout lintouch;
    EditText first_name,middle_name,last_name,d_o_b,gender,category,category_ed,applied_specialization_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_school_basic_info);
        next=findViewById(R.id.next);
        first_name=findViewById(R.id.first_name);
        lintouch=findViewById(R.id.lintouch);
        middle_name=findViewById(R.id.middle_name);
        last_name=findViewById(R.id.last_name);
        d_o_b=findViewById(R.id.d_o_b);
        gender=findViewById(R.id.gender);
        category=findViewById(R.id.category);
        category_ed=findViewById(R.id.category_ed);
        applied_specialization_name=findViewById(R.id.applied_specialization_name);
        HideKeyBoard.setupUI(lintouch,ApplySchoolBasicInfoActivity.this);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (isValid())
               { startActivity(new Intent(ApplySchoolBasicInfoActivity.this,ApplyPersonalInfoActivity.class));
                   overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

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
        gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ListAdapter adapter = new ArrayAdapter(ApplySchoolBasicInfoActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, stateArrayList);

                final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(new ContextThemeWrapper(ApplySchoolBasicInfoActivity.this, R.style.AlertDialogCustom));

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
                final ListAdapter adapter = new ArrayAdapter(ApplySchoolBasicInfoActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, categorys);

                final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(new ContextThemeWrapper(ApplySchoolBasicInfoActivity.this, R.style.AlertDialogCustom));

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

        d_o_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int  mYear = c.get(Calendar.YEAR);
                int  mMonth = c.get(Calendar.MONTH);
                int   mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(ApplySchoolBasicInfoActivity.this,
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
    }

    public boolean isValid()

    {
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
        else if (category_ed.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please enter course name", Toast.LENGTH_SHORT).show();

            category_ed.requestFocus();

            return false;
        }


        else if (applied_specialization_name.getText().toString().trim().equals("")) {


            Toast.makeText(this, "Please enter specialization name", Toast.LENGTH_SHORT).show();

            applied_specialization_name.requestFocus();

            return false;
        }



        return true;
    }
}
