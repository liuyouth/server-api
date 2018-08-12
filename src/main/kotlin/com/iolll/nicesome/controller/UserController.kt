package com.iolll.nicesome.controller

import com.iolll.nicesome.authorization.annotation.Authorization
import com.iolll.nicesome.authorization.annotation.CurrentUser
import com.iolll.nicesome.authorization.manager.TokenManager
import com.iolll.nicesome.authorization.model.TokenModel
import com.iolll.nicesome.config.ResultStatus
import com.iolll.nicesome.model.base.User
import com.iolll.nicesome.db.UserRepository
import com.iolll.nicesome.model.base.RBuilder
import com.iolll.nicesome.model.base.Result
import io.reactivex.Observable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.util.Assert
import org.springframework.util.StringUtils.isEmpty
import org.springframework.web.bind.annotation.*
@RestController
@RequestMapping("/user")
class UserController {
    @Autowired
    lateinit var repository: UserRepository
    @Autowired
    lateinit var tokenManager: TokenManager

    @PostMapping("/register")
    fun register(@RequestBody user: User): Result<String> {
        if (isEmpty(user.nikeName))
            return RBuilder.failed("昵称不能为空")
        if (isEmpty(user.pwd))
            return RBuilder.failed("密码不能为空")
        if (user.pwd.length < 6 || user.pwd.length > 20)
            return RBuilder.failed("密码长度为6-20位")
        if (isEmpty(user.email))
            return RBuilder.failed("邮箱不能为空")
        if (!isEmpty(user.id))
            return RBuilder.failed("非法请求")
        //基础验证通过 开始查重
        val emails = repository.findByEmail(user.email!!)
        if (emails.isEmpty())
            return RBuilder.failed("邮箱已被注册")
        val nikeNames = repository.findByNikeName(user.nikeName!!)
        if (nikeNames.isEmpty())
            return RBuilder.failed("昵称已被注册")
        //查重通过 进行存储注册
        val s = repository.save(user)
        if (0L != s.id)
            return RBuilder.seccess("注册成功！")
        return RBuilder.failed("注册失败！")
    }


    @PostMapping("/add")
    fun add() {

    }

    @ResponseBody
    @PostMapping("/login")
    fun login(@RequestParam userName: String, @RequestParam passWord: String): Result<TokenModel> {
        if (isEmpty(userName))
            return RBuilder.failed("账号不能为空")
        if (isEmpty(passWord))
            return RBuilder.failed("密码不能为空")

        val users = repository.findByName(userName)
        users.forEach { user ->
            if (user.pwd == passWord) {
                val model = tokenManager.createToken(user.id)
                return RBuilder.seccess(model);
            }

        }
        return RBuilder.failed("账号密码错误")
    }

    @GetMapping("/show")
    @Authorization
    @ResponseBody
    fun get(@CurrentUser user: User): Result<User> {

        return RBuilder.seccess(user)
    }


}