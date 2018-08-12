package com.iolll.nicesome.model.entity

import com.iolll.nicesome.model.base.User
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import javax.persistence.*

@Entity
data class HomeAddress(
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
        @JoinColumn(name = "user_id", foreignKey = ForeignKey(name = "home_for_user"))
        var user: User? = null,


        /**
         * URL地址
         */
        var address: String = "#",
        /**
         * 是否对所有人可以见  是否公开
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


//类型部分 后面会抽出去

        /**
         * 默认icon
         */
        var icon: String? = "img/icon_record.png",
        /**
         * 类型
         */
        var type: String? = "",
        /**
         * 类型 的类型 名称  例如 微博的类型是社交
         */
        var typeName: String? = "",


        /**
         * 是否删除
         */
        var deleted: Boolean? = false


) {
        override fun toString(): String {
                return "HomeAddress(id=$id, user=$user, address='$address', openAll=$openAll, useNum=$useNum, forkNum=$forkNum, starNum=$starNum, icon=$icon, type=$type, typeName=$typeName, deleted=$deleted)"
        }


}