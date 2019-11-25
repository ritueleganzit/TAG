package com.eleganzit.tag.api;


import com.eleganzit.tag.model.AddEducationDataResponse;
import com.eleganzit.tag.model.AddEducationPreferanceResponse;
import com.eleganzit.tag.model.AddPersonalInfoResponse;
import com.eleganzit.tag.model.AddworkexpResponse;
import com.eleganzit.tag.model.AppliedCollegeListResponse;
import com.eleganzit.tag.model.CollegeHomeResponse;
import com.eleganzit.tag.model.DeleteApiResponse;
import com.eleganzit.tag.model.FetchedUserResponse;
import com.eleganzit.tag.model.GetCollegeById;
import com.eleganzit.tag.model.GetCoursesResponse;
import com.eleganzit.tag.model.GetFaqListResponse;
import com.eleganzit.tag.model.GetProfileDataResponse;
import com.eleganzit.tag.model.GetSpecializationResponse;
import com.eleganzit.tag.model.ImageUploadedResponse;
import com.eleganzit.tag.model.LoginNodeResponse;
import com.eleganzit.tag.model.NationalityResponse;
import com.eleganzit.tag.model.QuestionAnsResponse;
import com.eleganzit.tag.model.RegisterOtpSent;
import com.eleganzit.tag.model.ResetPasswordResponse;
import com.eleganzit.tag.model.SendOtpResponse;
import com.eleganzit.tag.model.TopCollegeResponse;
import com.eleganzit.tag.model.UpdateEducationPreferanceResponse;
import com.eleganzit.tag.model.VerifiedResponse;
import com.eleganzit.tag.model.accountsettings.PasswordUpdateResponse;
import com.eleganzit.tag.model.addeducation.EducationDeleteResponse;
import com.eleganzit.tag.model.addeducation.EducationUpdateResponse;
import com.eleganzit.tag.model.addprofileinfo.UpdateProfileResponse;
import com.eleganzit.tag.model.addwork.AddWorkExperience;
import com.eleganzit.tag.model.askquestion.QuestionAnswerListResponse;
import com.eleganzit.tag.model.askquestion.UserQuestionListResponse;
import com.eleganzit.tag.model.coursedetails.CourseDetailsResponse;
import com.eleganzit.tag.model.discussion.DiscussionListResponse;
import com.eleganzit.tag.model.dropdowndata.DropDownListResponse;
import com.eleganzit.tag.model.homecourse.CourseResponse;
import com.eleganzit.tag.model.homefacility.FacilitiesResponse;
import com.eleganzit.tag.model.homegallery.GalleryResponse;
import com.eleganzit.tag.model.profileinfo.ProfileInfoDataResponse;
import com.eleganzit.tag.model.schoolstream.StreamResponse;
import com.eleganzit.tag.model.specialization.SpecialsationDetailsResponse;
import com.eleganzit.tag.model.askquestion.AskQuestionResponse;
import com.eleganzit.tag.model.unanswered.UnansweredQuestionsResponse;
import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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





    @POST("/testhost/users/askQuestions")
    Call<AskQuestionResponse> askquestion(
            @Body JsonObject jsonObject
    );

    @POST("/testhost/users/addAnswer")
    Call<AskQuestionResponse> addAnswer(
            @Body JsonObject jsonObject
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

@GET("/testhost/users/discussionList/{id}")
    Call<DiscussionListResponse> discussionList(@Path(value = "id", encoded = true) int id);


    @GET("/testhost/users/homeScreen/{id}")
    Call<CollegeHomeResponse> getCollegeById(
            @Path(value = "id", encoded = true) String id

    );

    @GET("/testhost/users/getCollege/4")
    Call<GetCollegeById> getCollegeById();


    @GET("/testhost/users/specialization/{name}")
    Call<GetSpecializationResponse> getSpecialization(@Path(value = "name", encoded = true) String name);

    @POST("testhost/users/specialization")
    Call<SpecialsationDetailsResponse> getspecializations(

            @Body JsonObject locationPost
    );




    @GET("/testhost/users/appliedCollegeList/{id}")
    Call<AppliedCollegeListResponse> appliedCollegeList(@Path(value = "id", encoded = true) String id);



    @GET("/testhost/users/quesList/{id}")
    Call<UserQuestionListResponse> getquestion(
            @Path(value = "id", encoded = true) String id
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
    @POST("/testhost/users/profile")
    Call<UpdateProfileResponse> updateProfileResponse(
@Body JsonObject jsonObject

    );
 @POST("/testhost/users/changePassword")
    Call<PasswordUpdateResponse> passwordUpdateResponse(
@Body JsonObject jsonObject

    );




    @POST("/testhost/users/updateEducationBackground")
    Call<EducationUpdateResponse> educationUpdateResponse(
            @Body JsonObject jsonObject

    );
 @POST("/testhost/users/deleteEducationData")
    Call<EducationDeleteResponse> deleteEducationData(
            @Body JsonObject jsonObject

    );
 @POST("/testhost/users/updateWorkExperience")
    Call<AddWorkExperience> updateWorkExperience(
            @Body JsonObject jsonObject

    );

 @DELETE("/testhost/users/deleteWorkData/{id}")
    Call<EducationDeleteResponse> deleteWorkData(
         @Path(value = "id", encoded = true) String id

    );@DELETE("/testhost/users/deletePreferanceData/{id}")
    Call<EducationDeleteResponse> deletePreferanceData(
         @Path(value = "id", encoded = true) String id

    );

 @POST("/testhost/users/updatePreferences")
    Call<EducationDeleteResponse> updatePreferences(
         @Body JsonObject jsonObject

    );


    @GET("/testhost/users/gallery/{id}")
    Call<GalleryResponse> getGallery(@Path(value = "id", encoded = true) String id);


    @GET("/testhost/users/dropDownList")
    Call<DropDownListResponse> dropDownList();
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



    @GET("/testhost/users/courceById/{id}")
    Call<CourseDetailsResponse> courceById(@Path(value = "id", encoded = true) String id);

    @GET("/testhost/users/getProfileById/{id}")
    Call<ProfileInfoDataResponse> getProfileById(@Path(value = "id", encoded = true) String id);


    @GET("/testhost/users/quesAnsList/{id}")
    Call<QuestionAnswerListResponse> questionListByUser(
            @Path(value = "id", encoded = true) String id
    );
    @POST("testhost/users/discussionByQuesId")
    Call<DiscussionListResponse> discussionByQuesId(

            @Body JsonObject locationPost
    );
    @GET("/testhost/users/unAnsQuesList")
    Call<UnansweredQuestionsResponse> unAnsQuesList();



@POST("/testhost/users/likeDislikeQuestion")
    Call<UnansweredQuestionsResponse> likeDislikeQuestion(
        @Body JsonObject locationPost
);
@POST("/testhost//users/updatePhoto")
    Call<UnansweredQuestionsResponse> updatePhoto
        (
        @Body JsonObject locationPost
);@POST("/testhost//users/stream")
    Call<StreamResponse> stream
        (
        @Body JsonObject stream
);

    @Multipart
    @POST("/been2/api/test")
    Call<ImageUploadedResponse> updateProfile(


            @Part MultipartBody.Part photo
    );


}
