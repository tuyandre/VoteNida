package xyz.developerbab.votenida.interfaces;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RESTApiInterface {

    // create pop
  //  @Headers("Content-Type: application/json")
    @POST("population/registration/")
    Call<ResponseBody> createPopulation(@Body Map<String, String> obj);


    //getdistrict
    @POST("province/district")
    Call<ResponseBody> getdistrict(@Body Map<String, String> obj);


    //getprovince
    @POST("get/province")
    Call<String> getprovince();



}
