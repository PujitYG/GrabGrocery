package com.microservice.EmployeeService.Configuration;



import java.sql.SQLException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.sql.XADataSource;
import javax.transaction.SystemException;

import org.hibernate.dialect.Oracle10gDialect;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionManager;

import oracle.jdbc.xa.client.OracleXADataSource;


@Configuration
@EnableJpaRepositories(basePackages = "com.microservice.EmployeeService.Model",
entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager")
public class EmployeeServiceDataSourceConfig {
	
	@Bean
	public AtomikosDataSourceBean dataSource() throws SQLException {
		OracleXADataSource xaDatasource = new OracleXADataSource();
		xaDatasource.setDriverType("oracle.jdbc.driver.OracleDriver");
		xaDatasource.setUser("system");
		xaDatasource.setPassword("Admin@123");
		xaDatasource.setURL("jdbc:oracle:thin:@172.28.224.1:1521:xe");
		
		AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
		atomikosDataSourceBean.setXaDataSource(xaDatasource);

		return atomikosDataSourceBean;
	}
	

	
	
	
    @Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws SQLException {
	    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    vendorAdapter.setShowSql(true);
	    vendorAdapter.setGenerateDdl(true);

	    
	    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
	    factory.setJpaVendorAdapter(vendorAdapter);
	    factory.setPackagesToScan("com.microservice.EmployeeService.*");
//	    factory.setDataSource(dataSource2());
	    factory.setJtaDataSource(dataSource());
	    
	    Properties jpaProperties = new Properties();
	    jpaProperties.put("hibernate.hbm2ddl.auto", "update");
//	    jpaProperties.put("hibernate.show_sql", "true");
	    jpaProperties.put("hibernate.dialect","org.hibernate.dialect.Oracle12cDialect");
	    
	    factory.setJpaProperties(jpaProperties);

	    return factory;
	}
    

    
//    private final Properties hibernateProperties() {
//        Properties hibernateProperties = new Properties();
//        hibernateProperties.setProperty(
//          "hibernate.hbm2ddl.auto", "update");
//        hibernateProperties.setProperty(
//          "hibernate.dialect", "org.hibernate.dialect.Oracle12cDialect");
//        hibernateProperties.setProperty("hibernate.show_sql", "true");
//        hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", "true");
//        hibernateProperties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
//        hibernateProperties.setProperty("hibernate.cache.use_query_cache", "true");
        
//        return hibernateProperties;
//    }
	
   @Bean
   public UserTransactionManager userTransactionManager() throws SystemException {
       UserTransactionManager userTransactionManager = new UserTransactionManager();
       userTransactionManager.setTransactionTimeout(300);
       userTransactionManager.setForceShutdown(true);
       return userTransactionManager;
   }
   
   @Bean
   public JtaTransactionManager transactionManager() throws SystemException {
       JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
       jtaTransactionManager.setTransactionManager(userTransactionManager());
       jtaTransactionManager.setUserTransaction(userTransactionManager());
       return jtaTransactionManager;
   }

}
