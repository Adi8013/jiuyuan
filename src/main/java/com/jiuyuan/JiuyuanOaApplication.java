package com.jiuyuan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jiuyuan.*.*.mapper")
public class JiuyuanOaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JiuyuanOaApplication.class, args);
	}
}
