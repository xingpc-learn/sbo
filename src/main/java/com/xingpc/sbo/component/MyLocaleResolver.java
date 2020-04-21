package com.xingpc.sbo.component;


import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @Author: XingPc
 * @Description: 更改请求头国际化信息,请求链接带上区域信息
 * @Date: 2020/2/19 16:33
 * @Version: 1.0
 */
public class MyLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String localeString = request.getParameter("locale");
        Locale locale = Locale.getDefault();
        if (!StringUtils.isEmpty(localeString)) {
            String[] split = localeString.split("_");
            locale = new Locale(split[0], split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }

}
