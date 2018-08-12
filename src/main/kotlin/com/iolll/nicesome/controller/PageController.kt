package com.iolll.nicesome.controller

import com.sun.org.apache.xpath.internal.operations.Mod
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class PageController {
    /**
     * 前台首页
     */
    @RequestMapping("/")
    fun index(model: Model): String {
        println("reception/index")
        return "reception/index"
    }

    @RequestMapping("/v/{module}")
    fun toModule(model: Model, @PathVariable("module") module: String): String {
        return "reception/$module/index"
    }

    @RequestMapping("/v/{module}/{name}")
    fun toModuleName(model: Model, @PathVariable("module") module: String,
                     @PathVariable("name") name: String
    ): String {
        return "reception/$module/$name"
    }

    /**
     * 后台首页
     */
    @RequestMapping("/admin")
    fun adminIndex(model: Model): String {
        println("back/index")
        return "back/index"
    }
    @RequestMapping("/admin/{module}")
    fun backToModule(model: Model, @PathVariable("module") module: String): String {
        return "back/$module/index"
    }

    @RequestMapping("/admin/{module}/{name}")
    fun backToModuleName(model: Model, @PathVariable("module") module: String,
                     @PathVariable("name") name: String
    ): String {
        return "back/$module/$name"
    }

}