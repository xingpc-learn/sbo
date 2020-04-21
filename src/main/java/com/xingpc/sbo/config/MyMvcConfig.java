package com.xingpc.sbo.config;

import com.xingpc.sbo.component.LoginHandlerInterceptor;
import com.xingpc.sbo.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: XingPc
 * @Description: 实现WebMvcConfigurer实现对mvc的扩展
 * @Date: 2020/2/19 14:13
 * @Version: 1.0
 */
//@EnableWebMvc   不要接管SpringMVC
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/to").setViewName("success");
    }

    /**
     * 欢迎页配置
     *
     * @param
     * @Return
     */
    @Bean
    public WebMvcConfigurer getLogin() {
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {

            /**
             * 添加视图映射
             * @param
             * @Return org.springframework.web.servlet.config.annotation.WebMvcConfigurer
             */
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/loginSuccess.html").setViewName("dashboard");
            }

            /**
             * 注册登录拦截器
             * @param
             * @Return
             */
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //静态资源：**.css,springboot做好了静态资源映射，不用处理
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/asserts/**","/index.html","/","/webjars/**","/user/login");
            }

        };
        return webMvcConfigurer;
    }

    /**
     * 切换国际化配置,方法名需要是localeResolver
     *
     * @param
     * @Return
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

}
