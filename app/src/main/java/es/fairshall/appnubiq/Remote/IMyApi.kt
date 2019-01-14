package es.fairshall.appnubiq.Remote

import retrofit2.Call
import es.fairshall.appnubiq.Model.APIResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface IMyApi {
    @FormUrlEncoded
    @POST("register.php")
    fun registerUser(@Field("id_client")id_client:Int, @Field("nombre") nombre:String, @Field("password") password:String):Call<APIResponse>

    @FormUrlEncoded
    @POST("login.php")
    fun LoginUser(@Field("id_client")id_client:Int, @Field("nombre") nombre:String, @Field("password") password:String):Call<APIResponse>

}