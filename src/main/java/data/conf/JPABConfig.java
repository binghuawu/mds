package data.conf;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import data.domain.b.BUser;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryB", transactionManagerRef = "transactionManagerB", basePackages = {
		"data.domain.b", "data.dao.b" })
public class JPABConfig {

	@Bean
	public DataSource dataSourceB() {
		SimpleDriverDataSource ds = new SimpleDriverDataSource();

		ds.setDriverClass(org.h2.Driver.class);
		ds.setUrl("jdbc:h2:tcp://localhost/~/testB");
		ds.setUsername("sa");
		return ds;
	}

	@Bean(name = "entityManagerFactoryB")
	public LocalContainerEntityManagerFactoryBean localContainerEMFB() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSourceB());
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
		emf.setPackagesToScan(BUser.class.getPackage().getName());
		emf.setPersistenceUnitName("PU-B");
		return emf;
	}

	@Bean(name = "transactionManagerB")
	public JpaTransactionManager jpaTransactionManagerB() {
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(localContainerEMFB().getObject());
		return tm;
	}
}