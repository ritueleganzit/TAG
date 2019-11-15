package com.eleganzit.tag.ui.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eleganzit.tag.R;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.accountsettings.PasswordUpdateResponse;
import com.eleganzit.tag.model.addprofileinfo.UpdateProfileResponse;
import com.eleganzit.tag.ui.activity.AddPersonalInfoActivity;
import com.eleganzit.tag.utils.UserLoggedInSession;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountSettingFragment extends Fragment {
    EditText new_password,cnf_password,old_password;
    ProgressDialog progressDialog;

    UserLoggedInSession userLoggedInSession;
    public AccountSettingFragment() {
        // Required empty public constructor
    }
    TextView change;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_account_setting, container, false);
        new_password=v.findViewById(R.id.new_password);
        cnf_password=v.findViewById(R.id.cnf_password);
        old_password=v.findViewById(R.id.old_password);
        change=v.findViewById(R.id.changepassword);
        userLoggedInSession=new UserLoggedInSession(getActivity());
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);



        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               

                    if (old_password.getText().toString().trim().equals("") || old_password.getText().toString().trim().length() < 6) {
                        Toast.makeText(getActivity(), "Please enter valid password", Toast.LENGTH_SHORT).show();


                        old_password.requestFocus();

                    }

                    else if (new_password.getText().toString().trim().equals("") || new_password.getText().toString().trim().length() < 8) {

                        Toast.makeText(getActivity(), "Password must contain atleast 8 characters", Toast.LENGTH_SHORT).show();

                        new_password.requestFocus();

                    } else if (!new_password.getText().toString().trim().equals(cnf_password.getText().toString().trim())) {

                        Toast.makeText(getActivity(), "Password doesn't match", Toast.LENGTH_SHORT).show();

                        cnf_password.requestFocus();

                    }else
                    {
                        //  Toast.makeText(AddPersonalInfoActivity.this, "password  selected", Toast.LENGTH_SHORT).show();

                        updateProfilewithPassword();
                    }


                
            }
        });
        return v;
    }

    private void updateProfilewithPassword() {
        progressDialog.show();
        JsonObject paramObject = new JsonObject();
        paramObject.addProperty("user_id", userLoggedInSession.getUserDetails().get(UserLoggedInSession.USER_ID));
        paramObject.addProperty("curr_password", cnf_password.getText().toString());
        paramObject.addProperty("new_password", new_password.getText().toString());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://eleganzit.online/testhost/users/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface myInterface = retrofit.create(RetrofitInterface.class);
        Call<PasswordUpdateResponse> call=myInterface.passwordUpdateResponse(paramObject);
        call.enqueue(new Callback<PasswordUpdateResponse>() {
            @Override
            public void onResponse(Call<PasswordUpdateResponse> call, Response<PasswordUpdateResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful())
                {
                    if (response.body().getStatus().toString().equalsIgnoreCase("1"))
                    {
                        Toast.makeText(getActivity(), "Successfully Updated", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getActivity(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<PasswordUpdateResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Server and Internet Error", Toast.LENGTH_SHORT).show();

            }
        });
    }


}
