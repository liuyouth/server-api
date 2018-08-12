package com.iolll.nicesome.controller

import com.iolll.nicesome.OkHttpUtil.get
import com.iolll.nicesome.model.base.Result
import com.iolll.nicesome.model.base.SearchResult
import com.iolll.nicesome.model.entity.EchoSong
import com.iolll.nicesome.model.entity.Order
import com.iolll.nicesome.net.DataManager
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/EchoSong/")
class EchoAPIController {

    @GetMapping("/url/")
    fun getById(): Result<ArrayList<EchoSong>> {
        var url: Result<ArrayList<EchoSong>> = Result();
        val dataManager: DataManager = DataManager()
//        dataManager.getBanner().subscribe({ r ->
//            {
//                print(r)
//                url = r
//            }
//        })
//        val okHttpClient:OkHttpClient = OkHttpClient().newBuilder().build()
//        okHttpClient
//        var dd = dataManager.netService.search().execute().body()
//        if (null != dd)
//            url = dd
        val result = get("", null, null)

        return url
    }

    @GetMapping("/id/{id}")
    fun getById(@PathVariable("id") id: String): Result<java.util.ArrayList<EchoSong>> {
        var result: Result<java.util.ArrayList<EchoSong>> = Result();
//        val params = HashMap<String, Any>()
//        params["ids"] = id
//
//        val headers = HashMap<String, String>()
//        headers["User-Agent"] = "Paw/3.0.12 (Macintosh; OS X/10.12.6) GCDHTTPRequest"
//        headers["Content-Type"] = "application/json"
//        headers["X-Requested-With"] = "XMLHttpRequest"
//
//        val result = get("http://www.app-Echo.com/sound/api-infos", params, headers)

//        url.setData()
        val dataManager: DataManager = DataManager()
        dataManager.getId(id).subscribe({ r ->
            run {
                result = r
            }
        })
        return result
    }
    @GetMapping("/name/{name}")
    fun getByName(@PathVariable("name") name: String): SearchResult<ArrayList<EchoSong>> {
        var result: SearchResult<ArrayList<EchoSong>> = SearchResult();
//        val params = HashMap<String, Any>()
//        params["keyword"] = name
//        params["page"] = 1
//        params["limit"] = 10
//        params["src"] = 0
//
//        val headers = HashMap<String, String>()
//        headers["User-Agent"] = "Paw/3.0.12 (Macintosh; OS X/10.12.6) GCDHTTPRequest"
//        headers["Content-Type"] = "application/json"
//
//        val result = get("http://www.app-Echo.com/api/search/sound", params, headers)



        val dataManager: DataManager = DataManager()
        dataManager.search(name, 1, 10).subscribe({ r ->
            run {
                result = r
            }
        })
        return result
    }
    @GetMapping("/name/")
    fun getByName(@RequestParam("name") name: String,
                  @RequestParam(value="page", required=true) page :Int,
                  @RequestParam(value="limit", required=false) limit :Int
    ): SearchResult<ArrayList<EchoSong>> {
        var result: SearchResult<ArrayList<EchoSong>> = SearchResult();
//        val params = HashMap<String, Any>()
//        params["keyword"] = name
//        params["page"] = 1
//        params["limit"] = 10
//        params["src"] = 0
//
//        val headers = HashMap<String, String>()
//        headers["User-Agent"] = "Paw/3.0.12 (Macintosh; OS X/10.12.6) GCDHTTPRequest"
//        headers["Content-Type"] = "application/json"
//
//        val result = get("http://www.app-Echo.com/api/search/sound", params, headers)



        val dataManager: DataManager = DataManager()
        dataManager.search(name, page, limit).subscribe({ r ->
            run {
                result = r
            }
        })
        return result
    }


    @GetMapping("/getOrders")
    fun readString(@RequestParam(value="page", required=true) page :Int,
                   @RequestParam(value="limit", required=false) limit :Int): SearchResult<ArrayList<Order>>{
        var result: SearchResult<ArrayList<Order>> = SearchResult();
        val orders = java.util.ArrayList<Order>()
        for (i in 0..29) {
            val order = Order(i, "备注$i", "订单号$i$i$i$i", "酒店名称$i")

            orders.add(order)
        }
        result.setData(orders);
        result.setCode(0);
        return result;
    }

}