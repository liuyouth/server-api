package com.iolll.nicesome

import com.google.gson.Gson
import com.iolll.nicesome.model.base.Result
import com.iolll.nicesome.net.DataManager
import okhttp3.*

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.io.IOException
import java.util.Arrays
import java.util.HashMap
import java.util.concurrent.TimeUnit

/**
 * @author lcj
 * @version 1.0
 * @description okhttp get post工具类
 * @Create 2017-06-16
 */
object OkHttpUtil {
    private val LOGGER = LoggerFactory.getLogger(OkHttpUtil::class.java)
    private val CLIENT: OkHttpClient

    init {
        CLIENT = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS) //连接超时
                .writeTimeout(10, TimeUnit.SECONDS) //写超时
                .readTimeout(30, TimeUnit.SECONDS) //读超时
                .build()

    }

    enum class OkHttpMethod {
        POST,
        PUT,
        DELETE
    }

    /**
     * get请求,支持http和https
     *
     * @param url     地址,比如: http://wwww.baidu.com
     * @param params  参数,可以为null
     * @param headers 请求头,可以为null
     * @return
     */
    operator fun get(url: String, params: Map<String, Any>?, headers: Map<String, String>?): String {
        var url = url
        //Builder对象
        val builder = Request.Builder()

        //处理参数
        if (null != params && params.size > 0) {
            val stringBuilder = StringBuilder("?")
            params.forEach { k, v -> stringBuilder.append(k).append("=").append(v).append("&") }
            val param = stringBuilder.toString()
            url += param.substring(0, param.lastIndexOf("&"))
        }

        //处理请求头
        if (null != headers && headers.size > 0) {
            headers.forEach { k, v -> builder.header(k, v) }
        }

        //LOGGER.info("url:{}",url);

        val request = builder.url(url).build()

        //LOGGER.info("所有的请求头: ");
        //request.headers().toMultimap().forEach((k,v) -> {
        //    LOGGER.info("{} : {}",k,v);
        //});

        //创建响应对象
        try {
            val response = CLIENT.newCall(request).execute()
            if (!response.isSuccessful) {
                LOGGER.error("发送get请求失败,状态码:{}", response.code())
                return ""
            }
            return response.body()!!.string()
        } catch (e: IOException) {
            LOGGER.error("发送get请求失败,原因:{$e}", e.cause)
            return ""
        }

    }

    /**
     * post,put,delete请求,支持http和https
     *
     * @param url          地址,比如: http://wwww.baidu.com
     * @param params       参数,可以为null
     * @param headers      请求头,可以为null
     * @param okHttpMethod 请求方式
     * @return
     */
    fun postOrPutOrDelete(url: String, params: Map<String, Any>?, headers: Map<String, String>?, okHttpMethod: OkHttpMethod): String {
        //Builder对象
        val builder = Request.Builder()

        //处理请求头
        if (null != headers && headers.size > 0) {
            headers.forEach { k, v -> builder.header(k, v) }
        }

        //处理参数
        if (null != params && params.size > 0) {
            val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), Gson().toJson(params))
            when (okHttpMethod) {
                OkHttpUtil.OkHttpMethod.POST -> builder.post(body)
                OkHttpUtil.OkHttpMethod.PUT -> builder.put(body)
                OkHttpUtil.OkHttpMethod.DELETE -> builder.delete(body)
                else -> builder.post(body)
            }
        } else {
            when (okHttpMethod) {
                OkHttpUtil.OkHttpMethod.DELETE -> builder.delete()
                else -> builder.delete()
            }
        }

        //LOGGER.info("url:{}",url);

        val request = builder.url(url).build()

        //LOGGER.info("所有的请求头: ");
        //request.headers().toMultimap().forEach((k,v) -> {
        //    LOGGER.info("{} : {}",k,v);
        //});

        //创建响应对象
        try {
            val response = CLIENT.newCall(request).execute()
            if (!response.isSuccessful) {
                LOGGER.error("发送请求失败,状态码:{}", response.code())
                return ""
            }
            return response.body()!!.string()
        } catch (e: IOException) {
            LOGGER.error("发送请求失败,原因:{" + e.message + "}", e.cause)
            return ""
        }

    }

//    @JvmStatic
//    fun main(args: Array<String>) {


//        val dataManager: DataManager = DataManager()
//        dataManager.getBanner().subscribe({ r ->
//            run {
//                println(r.getData())
//            }
//        })

//        val url = "http://www.app-Echo.com/"
//
////        val params = HashMap<String, Any>()
////        params["title"] = "title"
////        params["age"] = 23
////        params["sex"] = true
////
////        val headers = HashMap<String, String>()
////        headers["User-Agent"] = "Android"
////        headers["Content-Type"] = "application/json"
////
////        val result = get(url, params, headers)
//
//        val params = HashMap<String, Any>()
//        params["keyword"] = "悟空"
//        params["page"] = 1
//        params["limit"] = 10
//        params["src"] = 0
//
//        val headers = HashMap<String, String>()
//        headers["User-Agent"] = "Paw/3.0.12 (Macintosh; OS X/10.12.6) GCDHTTPRequest"
//        headers["Content-Type"] = "application/json"
//
//        val result = get("http://www.app-Echo.com/api/search/sound", params, headers)


//        println(result)

//    }


}