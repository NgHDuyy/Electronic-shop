package com.ocr.applinhkien.retrofit;

import com.google.gson.Gson;
import com.ocr.applinhkien.interfaceAPI.StringCallback;
import com.ocr.applinhkien.model.GetUserResponse;
import com.ocr.applinhkien.model.Order;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class APIHelper {

    private APIService api;
    private final String BASE_URL = "";

    public APIHelper() {
        if (api == null) {
            api = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(APIService.class);
        }
    }

    public void getListUser(StringCallback result) {
        api.getListUser().enqueue(new Callback<GetUserResponse>() {
            @Override
            public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        String data = new Gson().toJson(response.body().getData());
                        result.execute(data);
                    } else result.execute("");
                } else result.execute("");
            }

            @Override
            public void onFailure(Call<GetUserResponse> call, Throwable t) {
                result.execute("");
            }
        });
    }

    public void getHistoryBill(String userId, StringCallback res) {
        if (api != null) {
            api.getHisBill(userId).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()){
                        if (response.body() != null){
                            res.execute(response.body());
                        } else {
                            res.execute("");
                        }
                    } else {
                        res.execute("");
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    res.execute("");
                }
            });
        }
    }

    public void signUp(String username, String gender, String phonenumber, String address, String city, String email, String password, StringCallback res) {
        if (api != null) {
            api.dangky(username, gender, phonenumber, address, city, email, password).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            res.execute(response.body());
                        } else {
                            res.execute("");
                        }
                    } else {
                        res.execute("");
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    res.execute("");
                }
            });
        }
    }

    public void order(Order order, StringCallback result) {
        if (api != null) {
            api.order(order.getUserId(), order.getTotalPrice(), order.getEmail(),
                    order.getPhoneNumber(), order.getAddress(), order.getNote(),
                    order.getCreateAt(), new Gson().toJson(order.getListItem())).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            result.execute(response.body());
                        } else result.execute("");
                    } else result.execute("");
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    result.execute("");
                }
            });
        }
    }

    public interface APIService {
        @GET("getProduct/read.php")
        Call<String> getProduct();

        @GET("getSignIn/readGetUser.php")
        Call<GetUserResponse> getListUser();

        @POST("getUser/dangky.php")
        @FormUrlEncoded
        Call<String> dangky(
                @Field("username") String username,
                @Field("gender") String gender,
                @Field("phoneNumber") String phonenumber,
                @Field("address") String address,
                @Field("city") String city,
                @Field("email") String email,
                @Field("password") String password
        );

        @POST("donhang.php")
        @FormUrlEncoded
        Call<String> order(
                @Field("user_id") String userId,
                @Field("total_money") int totalMoney,
                @Field("email") String email,
                @Field("sdt") String phoneNumber,
                @Field("diachi") String address,
                @Field("note") String note,
                @Field("create_date") String createAt,
                @Field("sanpham") String listItem
        );

        @GET("lichsumuahang.php")
        Call<String> getHisBill(
                @Query("user_id") String userID
        );
    }
}
