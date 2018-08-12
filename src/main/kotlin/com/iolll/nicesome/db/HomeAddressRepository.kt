package com.iolll.nicesome.db

import com.iolll.nicesome.model.entity.HomeAddress
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param

/**
 * Created by GitHub Id = liuyouth on 2018/08/12.
 */

interface HomeAddressRepository : CrudRepository<HomeAddress, Long>, PagingAndSortingRepository<HomeAddress, Long> {

}
