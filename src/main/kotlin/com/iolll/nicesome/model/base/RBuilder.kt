package com.iolll.nicesome.model.base

import java.util.ArrayList

object RBuilder {
    fun <T> seccess(data: T): Result<T> {
        return Result<T>().setCode(200).setMsg("请求成功！").setData(data)
    }
    fun <T> failed(data: T): Result<T> {
        return Result<T>().setCode(201).setMsg("请求失败！").setData(data)
    }
    fun <T> failed(msg: String): Result<T> {
        return Result<T>().setCode(201).setMsg(msg)
    }
    fun <T> seccess(data: List<T>, total: Long, allPage: Int): PageResult<T> {
        return PageResult<T>().setCode(200).setMsg("请求成功！").setData(data).setTotal(total).setAllPage(allPage)
    }
}
