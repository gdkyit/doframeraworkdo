package com.gdky.restful.config;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.alibaba.druid.pool.DruidDataSource;
@Configuration
//事务 
@PropertySource(value = { "classpath:application.properties" }) 
@EnableTransactionManagement (proxyTargetClass = true)
public class DataSourceConfig {
	
    @Autowired  
    private Environment environment;  
  
    @Bean(name = "dataSource")
    @Primary
    public DruidDataSource dataSource() throws SQLException {  
    	DruidDataSource dataSource = new DruidDataSource();  
       // dataSource.setDriverClassName(environment.getRequiredProperty("spring.datasource.driver-class-name"));  
        dataSource.setUrl(environment.getRequiredProperty("spring.datasource.url"));  
        dataSource.setUsername(environment.getRequiredProperty("spring.datasource.username"));  
        dataSource.setPassword(environment.getRequiredProperty("spring.datasource.password"));  
        dataSource.setFilters("stat,wall,log4j");
        Properties p = new Properties();
        p.setProperty("druid.stat.mergeSql", "true");
        p.setProperty("druid.stat.slowSqlMillis", "5000");
        dataSource.setConnectProperties(p);
        return dataSource;  
    }  
    
    @Bean(name = "jdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(
            @Qualifier("dataSource") DruidDataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    
    @Bean(name = "primaryNamedParameterJdbcTemplate")
    public NamedParameterJdbcTemplate primaryNamedParameterJdbcTemplate(@Qualifier("dataSource") DruidDataSource dataSource){
    	return new NamedParameterJdbcTemplate(dataSource);
    }
    
    @Bean(name = "defaultTransactionManager")
    public PlatformTransactionManager primaryPlatformTransactionManager(@Qualifier("dataSource") DruidDataSource dataSource){
		return new DataSourceTransactionManager(dataSource);
    }

}