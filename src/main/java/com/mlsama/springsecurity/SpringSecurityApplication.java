package com.mlsama.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
// 扫描通用mapper的接口包,注意@MapperScan是tk包下的,不是mybatis的
@MapperScan(basePackages = "com.mlsama.springsecurity.mapper")
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

}
