package com.iolll.nicesome.net

import com.iolll.nicesome.model.base.Result
import com.iolll.nicesome.model.base.SearchResult
import com.iolll.nicesome.model.entity.EchoSong

import io.reactivex.Observable
import retrofit2.http.*

import java.util.ArrayList


interface NetService {

    @GET("http://www.app-Echo.com/api/search/sound")
    fun search(@Query("keyword") name: String, @Query("page") page: Int, @Query("limit") limit: Int, @Query("src") src: Int): Observable<SearchResult<ArrayList<EchoSong>>>

    @Headers("X-Requested-With:XMLHttpRequest")
    @GET("http://www.app-echo.com/sound/api-infos")
    fun getId(@Query("ids") id: String): Observable<Result<ArrayList<EchoSong>>>


    @FormUrlEncoded
    @POST("http://192.168.1.212:8089/crm/getcompnyconf")
    fun getCities(@Field("data") data: String,
                  @Field("appkey") appkey: String,
                  @Field("sign") sign: String): Observable<String>

    companion object {
        val BASE_URL = "http://app.hxhz.com/"
    }
}
