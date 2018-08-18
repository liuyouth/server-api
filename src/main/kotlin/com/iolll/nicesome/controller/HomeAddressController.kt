package com.iolll.nicesome.controller

import com.iolll.nicesome.authorization.annotation.Authorization
import com.iolll.nicesome.authorization.annotation.CurrentUser
import com.iolll.nicesome.db.HomeAddressRepository
import com.iolll.nicesome.model.base.*
import com.iolll.nicesome.model.entity.HomeAddress
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.data.domain.Sort
import org.springframework.util.StringUtils.isEmpty
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import java.math.BigInteger

/**
 * Created by GitHub Id = liuyouth on 2018/08/12.
 */
@RestController
@RequestMapping("/home/address")
class HomeAddressController {
    @Autowired
    lateinit var repository: HomeAddressRepository
    @Autowired
    @PersistenceContext
    lateinit var entityManager: EntityManager

    /**
     * @param user 当前登录人信息 无需客户端携带，只需要携带token即可。
     * @param likes 模糊查询数组，根据客户端传来
     *
     */
    @Authorization
    @GetMapping("/")
    fun list(@CurrentUser user: User,
             @RequestParam likes: Map<String, String>,
             @RequestParam(value = "name", defaultValue = "") name: String,
             @RequestParam(value = "type", defaultValue = "") type: String,
             @RequestParam(value = "page", defaultValue = "0") page: Int,
             @RequestParam(value = "size", defaultValue = "15") size: Int,
             @RequestParam(value = "sortField", defaultValue = "") filedName: String,
             @RequestParam(value = "sortOrder", defaultValue = "") sortOrder: String): PageResult<HomeAddress> {
        var likesc = likes
        if (likesc == null)
            likesc = HashMap()
        val userId = user.id
        val pageNum = if (page == 0) {
            0
        } else {
            page - 1
        }
        var filedNames: String = filedName
        if (isEmpty(filedName) || "null" == filedName)
            filedNames = "id"
        var sd = when (sortOrder) {
            "descend" -> Sort.Direction.DESC
            else -> Sort.Direction.ASC
        }

        var totalSql = getSql("select count(id) from `home_address`", userId, type, filedNames, name, likesc, sd, pageNum, size)
        var query = entityManager?.createNativeQuery(totalSql)
        val total = query.singleResult as BigInteger
        var allPage = (total.toInt() / 3)

        var sql = getSql("select * from `home_address`", userId, type, filedNames, name, likesc, sd, pageNum, size)
        var q = entityManager?.createNativeQuery(sql, HomeAddress::class.java)
        var list: List<HomeAddress> = q.resultList.toList() as List<HomeAddress>

        return RBuilder.seccess(list, total.toLong(), allPage)

    }

    @GetMapping("/list")
    fun adminlist(

            @RequestParam(value = "page", defaultValue = "0") page: Int,
            @RequestParam(value = "limit", defaultValue = "15") size: Int): PageResult<HomeAddress> {

        val pageNum = if (page == 0) {
            0
        } else {
            page - 1
        }

        var totalSql = getSql("select count(id) from `home_address`", 0, "", "", "", HashMap(), Sort.Direction.DESC, pageNum, size)
        var query = entityManager?.createNativeQuery(totalSql)
        val total = query.singleResult as BigInteger
        var allPage = (total.toInt() / 3)

        var sql = getSql("select * from `home_address`", 0, "", "", "", HashMap(), Sort.Direction.DESC, pageNum, size)
        var q = entityManager?.createNativeQuery(sql, HomeAddress::class.java)
        var list: List<HomeAddress> = q.resultList.toList() as List<HomeAddress>

        return RBuilder.seccess(list, total.toLong(), allPage)

    }

    //分页查询sql
    fun getSql(sqlte: String, userId: Long, type: String, filedNames: String, name: String, likes: Map<String, String>, sd: Sort.Direction, page: Int, size: Int): String {
        var sql = sqlte
        if (userId != 0L)
            sql += " where user_id =" + userId + " "


        if (!isEmpty(type))
            sql += " and type =" + type + " "
        if (!isEmpty(name))
            sql += " and name like %" + name + "% "

        likes.forEach { key, value ->
            sql += " and " + key + " like %" + value + "% "
        }

        if (!isEmpty(filedNames))
            sql += " order by " + filedNames + " " + sd

        sql += " limit " + page + "," + size
        println(sql)
        return sql
    }

    @Authorization
    @GetMapping("/{id}")
    fun getById(@CurrentUser user: User, @PathVariable("id") id: Long): HomeAddress {
        return repository.findOne(id)
    }

    @Authorization
    @PostMapping("/")
    fun add(@CurrentUser user: User, @RequestBody body: HomeAddress): Result<HomeAddress> {
        body.user = user
        return RBuilder.seccess(repository.save(body))
    }

    @Authorization
    @PutMapping("/")
    fun update(@CurrentUser user: User, @RequestBody body: HomeAddress): HomeAddress {
        body.user = user
        return repository.save(body)
    }

    @Authorization
    @DeleteMapping("/{id}")
    fun del(@CurrentUser user: User, @PathVariable("id") id: Long): Result<HomeAddress> {
        val body: HomeAddress = repository.findOne(id)
        body.deleted = true
        return RBuilder.seccess(repository.save(body))
        // 采用逻辑删除
        //        return repository.delete(id)
    }

}

