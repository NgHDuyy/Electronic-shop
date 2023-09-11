package com.ocr.applinhkien.interfaceAPI;


import com.ocr.applinhkien.model.GetUserResponse;
import com.ocr.applinhkien.model.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

//    Gson gson = new GsonBuilder()
//            .setDateFormat("yyyy-MM-dd HH:mm:ss")
//            .create();
//
//    ApiInterface apiInterface = new Retrofit.Builder()
//            .baseUrl("http://192.168.1.13/DIOR/")
//            .addConverterFactory(GsonConverterFactory.create(gson))
//                    .build()
//                    .create(ApiInterface.class);

    @GET("getSignIn/readGetUser.php")
    Call<GetUserResponse> getListUser();

    @POST("getUser/dangky.php")
    @FormUrlEncoded
    Call<SignUpResponse> dangky(
            @Field("username") String username,
            @Field("gender") String gender,
            @Field("phoneNumber") String phonenumber,
            @Field("address") String address,
            @Field("city") String city,
            @Field("email") String email,
             @Field("password") String password
    );

}


