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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.eleganzit.tag.R;
import com.eleganzit.tag.api.RetrofitAPI;
import com.eleganzit.tag.api.RetrofitInterface;
import com.eleganzit.tag.model.AddworkexpResponse;
import com.eleganzit.tag.model.DeleteApiResponse;
import com.eleganzit.tag.model.Education;
import com.eleganzit.tag.model.Workdata;
import com.eleganzit.tag.model.addwork.AddWorkExperience;
import com.eleganzit.tag.model.profileinfo.ExperienceInfo;
import com.eleganzit.tag.ui.activity.AddWorkActivity;
import com.eleganzit.tag.ui.activity.MyProfileActivity;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


