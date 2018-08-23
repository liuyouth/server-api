package com.iolll.nicesome.model.entity

import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import javax.persistence.*

@Entity
data class UrlType(
        /**
         * 自增主键ID
         */
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0L,
        /**
         * 类型名称
         */
        var typeName: String? = "",
        @ManyToOne(optional = true)
        @NotFound(action = NotFoundAction.IGNORE)
        @JoinColumn(name = "spuer_type_id", foreignKey = ForeignKey(name = "super_type"))
        var superType: UrlType? = UrlType(1L, "root", null)

) {
    constructor() : this(0L,"",UrlType(1L, "root", null))

    override fun toString(): String {
        return "UrlType(id=$id, typeName=$typeName, superType=$superType)"
    }
}