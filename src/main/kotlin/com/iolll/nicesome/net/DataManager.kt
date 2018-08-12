package com.iolll.nicesome.net

import com.iolll.nicesome.model.base.Result
import com.iolll.nicesome.model.base.SearchResult
import com.iolll.nicesome.model.entity.EchoSong
import io.reactivex.Observable

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

import java.util.ArrayList

class DataManager() {

    //
//    fun getBanner(): Observable<Result<ArrayList<EchoSong>>> {
//        return netService.banner
//
//
//
//    }

    fun get(data: String,sign:String ,apk :String): Observable<String> {
        return netService.getCities(data,apk,sign)
    }




    fun search(name: String, page: Int, limit: Int): Observable<SearchResult<ArrayList<EchoSong>>> {
        return netService.search(name, page, limit, 0)
    }
    fun getId(id: String): Observable<Result<ArrayList<EchoSong>>> {
        return netService.getId(id)
    }

    val retrofit = Retrofit.Builder()
            .baseUrl(NetService.BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    val netService = retrofit.create(NetService::class.java)
    internal fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.level = if (true)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        val postBody = "text/html;charset=gb2312"


        return builder.addInterceptor(logging)
//                .cache(Cache(File(application.cacheDir, "HttpResponseCache"), (10 * 1024 * 1024).toLong()))
                /*.addInterceptor(new Interceptor() {
                             @Override
                             public Response intercept(Chain chain) throws IOException {
                                 Request request = chain.request()
                                         .newBuilder()
                                         .addHeader("Content-Type", "text/html;charset=gb2312")
                                         .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=gb2312"),postBody))
                                         .build();
                                 return chain.proceed(request);
                             }

                         })*/
                .build()
    }
}