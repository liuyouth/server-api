package com.iolll.nicesome.db

import com.iolll.nicesome.model.entity.UrlType
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param

interface UrlTypeRepository : CrudRepository<UrlType, Long> , PagingAndSortingRepository<UrlType,Long> {

    //@Query("from UrlRecord u where u.type=:type")
    //fun findUrlRecord(@Param("type") type: String): List<UrlType>

    //fun findByNameLike(name:String ,page: Pageable):Page<UrlType>
    //fun findByTypeLike(type:String ,page: Pageable):Page<UrlType>
    //fun findByUser_id(id:Long ,page:Pageable):Page<UrlType>
    }
