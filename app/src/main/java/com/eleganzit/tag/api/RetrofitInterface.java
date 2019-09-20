package com.eleganzit.tag.api;


import com.eleganzit.tag.model.AddPersonalInfoResponse;
import com.eleganzit.tag.model.AskQuestionResponse;
import com.eleganzit.tag.model.LoginResponse;
import com.eleganzit.tag.model.SendOtpResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by eleganz on 30/4/19.
 */

public interface RetrofitInterface {

    @FormUrlEncoded()
    @POST("/AudioWalkSystems-AdminPanel/tags/login-api.php")
    Call<LoginResponse> doLogin(
            @Field("username") String username ,
            @Field("password") String password
    );


    @FormUrlEncoded()
    @POST("/AudioWalkSystems-AdminPanel/tags/checkcode.php")
    Call<LoginResponse> checkcode(
            @Field("username") String username ,
            @Field("sentcode") String sentcode
    );


    @FormUrlEncoded()
    @POST("/AudioWalkSystems-AdminPanel/tags/send-otp.php")
    Call<SendOtpResponse> sendOtp(
            @Field("username") String username
    );

  @FormUrlEncoded()
    @POST("/AudioWalkSystems-AdminPanel/tags/signup-api.php")
    Call<LoginResponse> doSignUP(
            @Field("name") String name ,
            @Field("mobile") String mobile,
            @Field("user_email") String user_email,
            @Field("password") String password,
            @Field("nationality") String nationality
    );

 @FormUrlEncoded()
    @POST("/AudioWalkSystems-AdminPanel/tags/temp-api.php")
    Call<AskQuestionResponse> askquestion(
            @Field("action") String ask_question ,
            @Field("user_id") String user_id,
            @Field("question_text") String question_text
    );

 @FormUrlEncoded()
    @POST("/AudioWalkSystems-AdminPanel/tags/temp-api.php")
    Call<AddPersonalInfoResponse> updatePersonalInfo(
            @Field("action") String personal_information ,
            @Field("user_id") String user_id,
            @Field("name") String name,
            @Field("mobile") String mobile,
            @Field("user_email") String user_email,

            @Field("location") String location
    ); @FormUrlEncoded()
    @POST("/AudioWalkSystems-AdminPanel/tags/temp-api.php")
    Call<AddPersonalInfoResponse> updatePersonalInfo(
            @Field("action") String personal_information ,
            @Field("user_id") String user_id,
            @Field("name") String name,
            @Field("mobile") String mobile,
            @Field("user_email") String user_email,
            @Field("old_password") String old_password,
            @Field("new_password") String new_password,
            @Field("location") String location
    );

}
