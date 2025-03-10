package com.dechator.scheduler.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(basePackages = "com.dechator")
@EnableTransactionManagement
public class MybatisConfig {

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public HikariConfig hikariConfig() {
    return new HikariConfig();
  }

  @Bean(name = "dataSource")
  public DataSource dataSource() throws Exception {
    // Hikari connection pool 설정
    DataSource dataSource = new HikariDataSource(hikariConfig());
    return dataSource;
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    // Mapper 경로지정
    sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/**/*.xml"));
    return sqlSessionFactoryBean.getObject();
  }

  @Bean
  public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
    // 쿼리 결과를 CamelCase 로 변경
    sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
    return new SqlSessionTemplate(sqlSessionFactory);
  }
}