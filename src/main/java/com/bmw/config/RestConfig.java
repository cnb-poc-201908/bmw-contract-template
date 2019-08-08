package com.bmw.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bmw.common.BMWPocConstants;
import com.bmw.model.ContractTemplate;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RestConfig implements WebMvcConfigurer {


	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.clear();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converters.add(converter);
	}



	@Bean
	public CorsFilter corsFilter() {
		// 1.添加CORS配置信息
		CorsConfiguration config = new CorsConfiguration();
		// 放行哪些原始域
		config.addAllowedOrigin("*");
		// 是否发送Cookie信息
		config.setAllowCredentials(true);
		// 放行哪些原始域(请求方式)
		config.addAllowedMethod("*");
		// 放行哪些原始域(头部信息)
		config.addAllowedHeader("*");

		// 2.添加映射路径
		UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
		configSource.registerCorsConfiguration("/**", config);

		// 3.返回新的CorsFilter.
		return new CorsFilter(configSource);
	}

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public List<ContractTemplate> contractTemplateList(RedisTemplate<String, String> redisTemplate) throws IOException {
		ValueOperations<String, String> ops = redisTemplate.opsForValue();
		ObjectMapper objectMapper = new ObjectMapper();
		List<ContractTemplate> list = new ArrayList<>();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		String json = ops.get(BMWPocConstants.REDIS_CONTRACT_TEMPLATE_LIST_KEY);
		if(StringUtils.isNotBlank(json)) {
			list = objectMapper.readValue(json, new TypeReference<List<ContractTemplate>>() {});
		}
		return list;
	}
}
