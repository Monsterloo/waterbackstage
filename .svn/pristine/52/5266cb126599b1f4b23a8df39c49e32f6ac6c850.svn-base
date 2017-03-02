package com.hhh.fund.config;


import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

/**
 * Spring Application Context Configuration Class
 * 
 * @author 3hzl
 * 注意：<br>
 * 	<p>1、如果不采用Jpa的方式访问数据库，则不需要配置@EnableJpaRepositories。</p>
 * <p>2、注意新增业务Jpa entity需要在entityManagerFactory增加对entity所在package的扫描。</p>
 */
@Configuration
@PropertySource(value = {"classpath:db.properties"})
public   class  MongoContext  {
	@Resource
	public Environment env;
	public String getDatabaseName() {
		String database = env.getProperty("mongo.database");
		return   database ;
	}
	@Bean
	public  Mongo mongo()  throws Exception {
		String ip = env.getProperty("mongo.ip");
		String port1 = env.getProperty("mongo.port");
		int port = Integer.parseInt(port1);
        Mongo mongo =  new MongoClient(ip,port);
        return mongo;
	}
	@Bean
	 public  MongoTemplate mongoTemplate() throws Exception {
	        return new MongoTemplate(mongo(), getDatabaseName());
	    }
	 
}

