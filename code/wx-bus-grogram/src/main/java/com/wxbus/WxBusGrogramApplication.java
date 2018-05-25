package com.wxbus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wxbus.dao")//配置mapper 的路径
public class WxBusGrogramApplication {

	public static void main(String[] args) {
		SpringApplication.run(WxBusGrogramApplication.class, args);
	}
}
