package com.louis.mango.admin.config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan("com.louis.mango.**.dao")
public class MybatisConfig {
	@Autowired
	private DataSource dataSource;
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setTypeAliasesPackage("com.louis.mango.**.model");
		//扫描 dao
		PathMatchingResourcePatternResolver resolver = new 
				PathMatchingResourcePatternResolver();
		sessionFactory.setMapperLocations(resolver.getResources("classpath*:**/sqlmap/*.xml"));
		//扫描映射文件
		return sessionFactory.getObject();
	}
}
