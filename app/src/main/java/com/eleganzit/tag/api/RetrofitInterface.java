package com.eleganzit.tag.api;


import com.eleganzit.tag.model.AddEducationDataResponse;
import com.eleganzit.tag.model.AddEducationPreferance;
import com.eleganzit.tag.model.AddEducationPreferanceResponse;
import com.eleganzit.tag.model.AddPersonalInfoResponse;
import com.eleganzit.tag.model.AddworkexpResponse;
import com.eleganzit.tag.model.AppliedCollegeListResponse;
import com.eleganzit.tag.model.AskQuestionResponse;
import com.eleganzit.tag.model.CollegeHomeResponse;
import com.eleganzit.tag.model.DeleteApiResponse;
import com.eleganzit.tag.model.FetchedUserResponse;
import com.eleganzit.tag.model.GetCollegeById;
import com.eleganzit.tag.model.GetCoursesResponse;
import com.eleganzit.tag.model.GetFaqListResponse;
import com.eleganzit.tag.model.GetProfileDataResponse;
import com.eleganzit.tag.model.GetSpecializationResponse;
import com.eleganzit.tag.model.LoginNodeResponse;
import com.eleganzit.tag.model.LoginResponse;
import com.eleganzit.tag.model.NationalityResponse;
import com.eleganzit.tag.model.QuestionAnsResponse;
import com.eleganzit.tag.model.RegisterOtpSent;
import com.eleganzit.tag.model.ResetPasswordResponse;
import com.eleganzit.tag.model.SendOtpResponse;
import com.eleganzit.tag.model.TopCollegeResponse;
import com.eleganzit.tag.model.UpdateEducationPreferanceResponse;
import com.eleganzit.tag.model.VerifiedResponse;
import com.eleganzit.tag.model.appliedcollege.ApplyCollegeMobileResponse;
import com.eleganzit.tag.model.homecourse.CourseResponse;
import com.eleganzit.tag.model.homefacility.FacilitiesResponse;
import com.eleganzit.tag.model.homegallery.GalleryResponse;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by eleganz on 30/4/19.
 */

public interface RetrofitInterface {


    @POST("/testhost/users/userLogin")
    Call<LoginNodeResponse> doLogin(
            @Body JsonObject jsonObject

    );

    @POST("/testhost/users/userSignUp")
    Call<LoginNodeResponse> doSignUP(
            @Body JsonObject jsonObject

    );

   /* @FormUrlEncoded()
    @POST("/tags/checkcode.php")
    Call<LoginResponse> checkcode(
            @Field("username") String username ,
            @Field("sentcode") String sentcode
    );*/

   @POST("/testhost/users/verifyOtp")
   Call<VerifiedResponse> checkcode(
           @Body JsonObject jsonObject

   );

 /*@FormUrlEncoded()
    @POST("/tags/temp-api.php")
    Call<LoginResponse> get_restpassword(
            @Field("action") String get_restpassword ,
            @Field("user_id") String user_id ,
            @Field("new_password") String new_password
    );*/
    @POST("/testhost/users/resetPassword")
    Call<ResetPasswordResponse> get_restpassword(
            @Body JsonObject jsonObject
    );


    @POST("/testhost/users/sendOtp")
    Call<SendOtpResponse> sendOtp(
            @Body JsonObject jsonObject
    );

 @POST("/testhost/users/sendOtp")
    Call<RegisterOtpSent> sendregOtp(
            @Body JsonObject jsonObject
    );





 @FormUrlEncoded()
    @POST("/tags/temp-api.php")
    Call<AskQuestionResponse> askquestion(
            @Field("action") String ask_question ,
            @Field("user_id") String user_id,
            @Field("question_text") String question_text
    );

 @FormUrlEncoded()
    @POST("/tags/temp-api.php")
    Call<AddPersonalInfoResponse> updatePersonalInfo(
            @Field("action") String personal_information ,
            @Field("user_id") String user_id,
            @Field("name") String name,
            @Field("mobile") String mobile,
            @Field("user_email") String user_email,

            @Field("location") String location
    );
 @FormUrlEncoded()
    @POST("/tags/temp-api.php")
    Call<AddPersonalInfoResponse> get_userdata(
            @Field("action") String get_userdata ,
            @Field("user_id") String user_id

    );


 @FormUrlEncoded()
    @POST("/tags/temp-api.php")
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


@FormUrlEncoded()
    @POST("/tags/temp-api.php")
    Call<AddEducationDataResponse> addeducationbg(
            @Field("action") String add_education_bg ,
            @Field("user_id") String user_id,
            @Field("cource_level") String cource_level,
            @Field("school_name") String school_name,
            @Field("cource_year") String cource_year,
            @Field("board") String board,
            @Field("subject") String subject,
            @Field("marks") String marks
    );@FormUrlEncoded()
    @POST("/tags/temp-api.php")
    Call<AddEducationDataResponse> update_education_bg(
            @Field("action") String update_education_bg ,
            @Field("user_id") String user_id,
            @Field("education_id") String education_id,
            @Field("cource_level") String cource_level,
            @Field("school_name") String school_name,
            @Field("cource_year") String cource_year,
            @Field("board") String board,
            @Field("subject") String subject,
            @Field("marks") String marks
    );
@FormUrlEncoded()
    @POST("/tags/temp-api.php")
    Call<AddworkexpResponse> addWorkExp(
            @Field("action") String add_work_exp ,
            @Field("user_id") String user_id,
            @Field("employee_name") String employee_name,
            @Field("designation") String designation,
            @Field("department") String department,
            @Field("current_job") String current_job
    );

@FormUrlEncoded()
    @POST("/tags/temp-api.php")
    Call<AddworkexpResponse> update_work(
            @Field("action") String update_work ,
            @Field("user_id") String user_id,
            @Field("work_id") String work_id,
            @Field("employee_name") String employee_name,
            @Field("designation") String designation,
            @Field("department") String department,
            @Field("current_job") String current_job
    );
@FormUrlEncoded()
    @POST("/tags/temp-api.php")
    Call<AddEducationPreferanceResponse> addeducationpreferance(
            @Field("action") String add_education_preferance ,
            @Field("user_id") String user_id,
            @Field("stream") String stream,
            @Field("course") String course,
            @Field("specialisation") String specialisation,
            @Field("mode_of_study") String mode_of_study
    );
@FormUrlEncoded()
    @POST("/tags/temp-api.php")
    Call<UpdateEducationPreferanceResponse> update_education_preferance(
            @Field("action") String update_education_preferance ,
            @Field("user_id") String user_id,
            @Field("preferance_id") String preferance_id ,
            @Field("stream") String stream,
            @Field("course") String course,
            @Field("specialisation") String specialisation,
            @Field("mode_of_study") String mode_of_study
    );


@FormUrlEncoded()
    @POST("/tags/temp-api.php")
    Call<DeleteApiResponse> delete(
            @Field("action") String update_education_preferance ,
            @Field("education_id") String education_id

    );
@FormUrlEncoded()
    @POST("/tags/temp-api.php")
    Call<DeleteApiResponse> delete_workdata(
            @Field("action") String update_education_preferance ,
            @Field("work_id") String work_id

    );
@FormUrlEncoded()
    @POST("/tags/temp-api.php")
    Call<DeleteApiResponse> delete_preferancedata(
            @Field("action") String delete_preferancedata ,
            @Field("preferance_id") String preferance_id

    );

    @FormUrlEncoded
 @POST("/tags/temp-api.php")
    Call<GetProfileDataResponse> getprofiledata(
            @Field("action") String get_profiledata ,
            @Field("user_id") String user_id

    );



    @GET("/testhost/users/cources")
    Call<GetCoursesResponse> getCourses();

  @GET("/testhost/users/getFaqList")
    Call<GetFaqListResponse> getFaqList();

    @GET("/testhost/users/courceById/{id}")
    Call<GetCoursesResponse> getCoursesbyId(@Path(value = "id", encoded = true) int id);

    @FormUrlEncoded
    @POST("/tags/college.php")
    Call<CollegeHomeResponse> getCollegeById(
            @Field("action") String action,
            @Field("college_id") String college_id

    );

    @GET("/testhost/users/getCollege/4")
    Call<GetCollegeById> getCollegeById();


    @GET("/testhost/users/specialization/{name}")
    Call<GetSpecializationResponse> getSpecialization(@Path(value = "name", encoded = true) String name);


    @GET("/testhost/users/appliedCollegeList/{id}")
    Call<AppliedCollegeListResponse> appliedCollegeList(@Path(value = "id", encoded = true) String id);



    @FormUrlEncoded()
    @POST("/tags/ques-ans-list-api.php")
    Call<QuestionAnsResponse> getquestion(

            @Field("action") String ques_ans_list,
            @Field("user_id") String user_id
    );

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("applyCollegeMobile")
    Call<JsonObject> applyCollegeMobile(

            @Body JsonObject locationPost
    );



    @POST("/testhost/users/getCollegeList")
    Call<TopCollegeResponse> getCollegeList(
@Body JsonObject jsonObject

    );


    @GET("/testhost/users/gallery/{id}")
    Call<GalleryResponse> getGallery(@Path(value = "id", encoded = true) String id);
  @GET("/testhost/users/facility/{id}")
    Call<FacilitiesResponse> getfacility(@Path(value = "id", encoded = true) String id);
    @FormUrlEncoded()
    @POST("tags/college.php")
    Call<CourseResponse> coursefees(

            @Field("action") String coursefees,
            @Field("college_id") String college_id
    );
    @GET("/testhost/users/nationalities")
    Call<NationalityResponse> getNationalityResponse();

    @GET("/testhost/users/getUserById/{id}")
    Call<FetchedUserResponse> getUserById(@Path(value = "id", encoded = true) String id);



}
