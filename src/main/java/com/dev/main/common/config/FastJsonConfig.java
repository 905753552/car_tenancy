package com.dev.main.common.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class FastJsonConfig extends WebMvcConfigurerAdapter {

    // fastjson配置
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        com.alibaba.fastjson.support.config.FastJsonConfig config = new com.alibaba.fastjson.support.config.FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.WriteNullListAsEmpty, // List类型字段为null时输出[]而非null
                SerializerFeature.WriteMapNullValue, // 显示空字段
                SerializerFeature.WriteNullStringAsEmpty, // 字符串类型字段为null时间输出""而非null
                SerializerFeature.WriteNullBooleanAsFalse, // Boolean类型字段为null时输出false而null
                SerializerFeature.PrettyFormat); // 美化json输出，否则会作为整行输出
        //converter.setDateFormat("yyyy-MM-dd HH:mm:ss"); // 时间格式
        converter.setFastJsonConfig(config);
        converters.add(converter);
    }

}
