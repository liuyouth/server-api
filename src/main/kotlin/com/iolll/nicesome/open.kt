package com.iolll.nicesome

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.filter.CorsFilter
import javax.servlet.http.HttpServletResponse
import java.io.IOException
import javax.servlet.*
import javax.servlet.http.HttpServletRequest


/**
 * Created by MacBook on 2018/5/2.
 */
//@Configuration
// class SimpleConfiguration{
//    private fun buildConfig(): CorsConfiguration {
//        val corsConfiguration = CorsConfiguration()
//        corsConfiguration.addAllowedOrigin("*") // 1 设置访问源地址
//        corsConfiguration.addAllowedHeader("*") // 2 设置访问源请求头
//        corsConfiguration.addAllowedMethod("*") // 3 设置访问源请求方法
//        return corsConfiguration
//    }
//
//    @Bean
//    fun corsFilter(): CorsFilter {
//        val source = UrlBasedCorsConfigurationSource()
//        source.registerCorsConfiguration("/**", buildConfig()) // 4 对接口配置跨域设置
//        return CorsFilter(source)
//    }
//}

//@Component
//class CorsFilter : Filter {
//
//
//    @Throws(ServletException::class)
//    override fun init(filterConfig: FilterConfig) {
//
//    }
//
//    @Throws(IOException::class, ServletException::class)
//    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
//        val httpResponse = response as HttpServletResponse
//        val httpRequest = request as HttpServletRequest
//        httpResponse.setHeader("Access-Control-Allow-Origin", "*")
//        httpResponse.setHeader("Access-Control-Allow-Methods", httpRequest.getMethod())
//        httpResponse.setHeader("Access-Control-Max-Age", "3600")
//        httpResponse.setHeader("Access-Control-Allow-Headers", httpRequest.getHeader("Access-Control-Request-Headers"))
//        chain.doFilter(request, response)
//    }
//
//    override fun destroy() {
//
//    }
//}
