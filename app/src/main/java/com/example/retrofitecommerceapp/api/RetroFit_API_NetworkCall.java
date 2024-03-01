package com.example.retrofitecommerceapp.api;

import com.example.retrofitecommerceapp.model.CategoryDataModel;
import com.example.retrofitecommerceapp.model.DataModel;
import com.example.retrofitecommerceapp.model.LoginDataModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
public interface RetroFit_API_NetworkCall {
    String BASE_URL = "https://fakestoreapi.com/";
    String BASE_URL_LOGIN = "https://fakestoreapi.com/";
    @GET("products")
    Call<List<DataModel>> getAllData();
    @GET("products/categories")
    Call<List<CategoryDataModel>> getAllCategory();
    @POST("auth/login")
    Call<ResponseBody> login(@Body LoginDataModel loginDataModel);
}



















//    String BASE_URL_LOGIN = "https://fakestoreapi.com/";
//
//
//    @GET("products")
//    Call<List<DataModel>> getAllData();
//    @GET("products/categories")
//    Call<List<CategoryDataModel>> getAllCategory();
//    @POST("auth/login")
//    Call<ResponseBody> login(@Body LoginDataModel loginDataModel);
//}
//
//
//
//
//
//
//
//
//
//
//
//
//     WORKING
//
////    https://reqres.in/
////            api/login
////            eve.holt@reqres.in
////    cityslicka