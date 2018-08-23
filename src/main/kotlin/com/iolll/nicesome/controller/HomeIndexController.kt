package com.iolll.nicesome.controller

import com.iolll.nicesome.authorization.annotation.Authorization
import com.iolll.nicesome.authorization.annotation.CurrentUser
import com.iolll.nicesome.db.UrlRecordRepository
import com.iolll.nicesome.db.UrlTypeRepository
import com.iolll.nicesome.model.base.*
import com.iolll.nicesome.model.entity.UrlRecord
import com.iolll.nicesome.model.entity.UrlType
import io.reactivex.Observable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.data.domain.Sort
import org.springframework.util.StringUtils.isEmpty
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import java.math.BigInteger
import java.util.*


@RestController
@RequestMapping("/home")
class HomeIndexController {
    @Autowired
    lateinit var repository: UrlRecordRepository
    @Autowired
    lateinit var urlTypeRepository: UrlTypeRepository
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
             @RequestParam(value = "sortOrder", defaultValue = "") sortOrder: String): PageResult<UrlRecord> {
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

        var totalSql = getSql("select count(id) from `url_record`", userId, type, filedNames, name, likesc, sd, pageNum, size)
        var query = entityManager?.createNativeQuery(totalSql)
        val total = query.singleResult as BigInteger
        var allPage = (total.toInt() / 3)

        var sql = getSql("select * from `url_record`", userId, type, filedNames, name, likesc, sd, pageNum, size)
        var q = entityManager?.createNativeQuery(sql, UrlRecord::class.java)
        var list: List<UrlRecord> = q.resultList.toList() as List<UrlRecord>

        return RBuilder.seccess(list, total.toLong(), allPage)

    }

    @GetMapping("/list")
    fun adminlist(

            @RequestParam(value = "page", defaultValue = "0") page: Int,
            @RequestParam(value = "limit", defaultValue = "15") size: Int): PageResult<UrlRecord> {

        val pageNum = if (page == 0) {
            0
        } else {
            page - 1
        }

        var totalSql = getSql("select count(id) from `url_record`", 0, "", "", "", HashMap(), Sort.Direction.DESC, pageNum, size)
        var query = entityManager?.createNativeQuery(totalSql)
        val total = query.singleResult as BigInteger
        var allPage = (total.toInt() / 3)

        var sql = getSql("select * from `url_record`", 0, "", "", "", HashMap(), Sort.Direction.DESC, pageNum, size)
        var q = entityManager?.createNativeQuery(sql, UrlRecord::class.java)
        var list: List<UrlRecord> = q.resultList.toList() as List<UrlRecord>

        return RBuilder.seccess(list, total.toLong(), allPage)

    }

    //分页查询sql
    fun getSql(sqlte: String, userId: Long, type: String, filedNames: String, name: String, likes: Map<String, String>, sd: Sort.Direction, page: Int, size: Int): String {
        var sql = sqlte
        if (userId != 0L)
            sql += " where user_id =" + userId + " "
        sql += " and type_id = 2 "


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
    fun getById(@CurrentUser user: User, @PathVariable("id") id: Long): UrlRecord {
        return repository.findOne(id)
    }

    @Authorization
    @PostMapping("/")
    fun add(@CurrentUser user: User, @RequestBody body: UrlRecord): Result<UrlRecord> {
        if (body.type != null) {
            if (null == urlTypeRepository.findOne(body.type!!.id))
                body.type = urlTypeRepository.save(body.type)
        } else body.type = UrlType(2,"") // 如果没有则把root给他
        body.user = user
        return RBuilder.seccess(repository.save(body))
    }

    @Authorization
    @PutMapping("/")
    fun update(@CurrentUser user: User, @RequestBody body: UrlRecord): UrlRecord {
        if (body.type != null) {
            if (null == urlTypeRepository.findOne(body.type!!.id))
                body.type = urlTypeRepository.save(body.type)
        } else body.type = UrlType().superType // 如果没有则把root给他
        body.user = user
        return repository.save(body)
    }

    @Authorization
    @DeleteMapping("/{id}")
    fun del(@CurrentUser user: User, @PathVariable("id") id: Long): Result<UrlRecord> {
        val body: UrlRecord = repository.findOne(id)
        body.deleted = true
        return RBuilder.seccess(repository.save(body))
        // 采用逻辑删除
//        return repository.delete(id)
    }


}

