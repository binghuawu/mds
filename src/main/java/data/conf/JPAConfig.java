package data.conf;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import data.domain.a.AUser;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager", basePackages = {
		"data.domain.a", "data.dao.a" })
public class JPAConfig {

	@Primary
	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource ds = new SimpleDriverDataSource();

		ds.setDriverClass(org.h2.Driver.class);
		ds.setUrl("jdbc:h2:tcp://localhost/~/test");
		ds.setUsername("sa");
		return ds;
	}

	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean localContainerEMF() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setJpaDialect(new HibernateJpaDialect());
		// hibernate specific properties
		Properties p = new Properties();
		p.put("hibernate.archive.autodetection", "class,hbm");
		p.put("hibernate.show_sql", "true");
		p.put("hibernate.format_sql", "true");
		p.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		// disable Hibernate 2L Cache which is enabled by default. In this
		// case, the query_cache must be disabled which is the default case.
		p.put("hibernate.cache.use_second_level_cache", "false");
		p.put("hibernate.cache.use_query_cache", "false");
		// print statistics in the log
		p.put("hibernate.generate_statistics", "true");
		// p.put("hibernate.hbm2ddl.auto", "create");
		emf.setJpaProperties(p);

		// we're configuring JPA without persistence.xml
		emf.setPackagesToScan(AUser.class.getPackage().getName());
		emf.setPersistenceUnitName("PU-A");
		return emf;
	}

	@Primary
	@Bean(name = "transactionManager")
	public JpaTransactionManager jpaTransactionManager() {
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(localContainerEMF().getObject());
		return tm;
	}

}