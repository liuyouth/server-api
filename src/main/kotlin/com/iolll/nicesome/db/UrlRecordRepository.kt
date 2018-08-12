package com.iolll.nicesome.db

import com.iolll.nicesome.model.entity.UrlRecord
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param

interface UrlRecordRepository : CrudRepository<UrlRecord, Long> , PagingAndSortingRepository<UrlRecord,Long> {

    @Query("from UrlRecord u where u.type=:type")
    fun findUrlRecord(@Param("type") type: String): List<UrlRecord>

    fun findByNameLike(name:String ,page: Pageable):Page<UrlRecord>
    fun findByTypeLike(type:String ,page: Pageable):Page<UrlRecord>
    fun findByUser_id(id:Long ,page:Pageable):Page<UrlRecord>
}
