package com.iolll.nicesome.model.entity

import com.iolll.nicesome.model.base.User
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import javax.persistence.*

@Entity
data class UrlRecord(
        /**
         * 自增主键ID
         */
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0L,
        /**
         * 归属用户
         */
//        var userid: Int = 0,
        @ManyToOne(optional = true)
        @NotFound(action = NotFoundAction.IGNORE)
        @JoinColumn(name = "user_id", foreignKey = ForeignKey(name = "url_for_user"))
        var user: User? = null,
        /**
         * 姓名
         */
        var name: String? = "",
        /**
         * 默认icon
         */
        var icon: String? = "img/icon_record.png",
        /**
         * 类型
         */
        @ManyToOne(optional = true)
        @NotFound(action = NotFoundAction.IGNORE)
        @JoinColumn(name = "type_id", foreignKey = ForeignKey(name = "url_for_type"))
        var type: UrlType? = null,
        /**
         * 空间   以后改为 group 组
         */
        var space: String? = "",
        /**
         * URL地址
         */
        var url: String = "#",
        /**
         * 是否对所有人可以见
         */
        var openAll: Boolean? = false,
        /**
         * 使用次数
         */
        var useNum: Int = 0,
        /**
         * 从这里添加到自己的库的数量
         */
        var forkNum: Int = 0,
        /**
         * 点赞数量
         */
        var starNum: Int = 0,
        /**
         * 是否删除
         */
        var deleted: Boolean? = false


) {
        override fun toString(): String {
                return "UrlRecord(id=$id, user=$user, name=$name, icon=$icon, type=$type, space=$space, url='$url', openAll=$openAll, useNum=$useNum, forkNum=$forkNum, starNum=$starNum, deleted=$deleted)"
        }
}