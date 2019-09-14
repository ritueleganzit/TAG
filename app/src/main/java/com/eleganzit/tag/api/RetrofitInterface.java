package com.eleganzit.tag.api;


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

}
