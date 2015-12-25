package com.cinema.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * PersistenceConfig
 * Created by rayn on 2015/12/24.
 */
@Configuration
@EnableTransactionManagement
public class PersistenceConfig {

	@Autowired
	private Environment env;

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(env.getProperty("jdbc.driver"));
		ds.setUrl(env.getProperty("jdbc.url"));
		ds.setUsername(env.getProperty("jdbc.username"));
		ds.setPassword(env.getProperty("jdbc.password"));
		return ds;
	}

	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory() {
		LocalSessionFactoryBuilder builder =
				new LocalSessionFactoryBuilder(getDataSource());
		builder.scanPackages("com.cinema.model");
		builder.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		builder.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		builder.setProperty("hibernate.show_sql", env.getProperty("hibernate.showSQL"));
		builder.setProperty("hibernate.format_sql", env.getProperty("hibernate.formatSQL"));
		return builder.buildSessionFactory();
	}

	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager =
				new HibernateTransactionManager(getSessionFactory());
		return transactionManager;
	}

}
