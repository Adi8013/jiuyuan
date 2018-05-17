package com.jiuyuan;

import javax.servlet.Filter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jiuyuan.sys.filter.ApplicationFilter;

@SpringBootApplication
@MapperScan("com.jiuyuan.*.*.mapper")
@EnableTransactionManagement
public class JiuyuanOaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JiuyuanOaApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean<Filter> someFilterRegistration() {
		FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<Filter>();
		registration.setFilter(applicationFilter());
		registration.addUrlPatterns("/*");
		registration.setName("applicationFilter");
		return registration;
	}
	
	@Bean(name = "applicationFilter")
	public Filter applicationFilter() {
		return new ApplicationFilter();
	}
}
