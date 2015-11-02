package conf;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class TestConfig {
	final Logger LOG = LoggerFactory.getLogger(TestConfig.class);

	@Primary
	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource ds = new SimpleDriverDataSource();

		ds.setDriverClass(org.h2.Driver.class);
		ds.setUrl("jdbc:h2:~/user-unit-test");
		ds.setUsername("sa");
		return ds;
	}

	@Bean
	public DataSource dataSourceB() {
		SimpleDriverDataSource ds = new SimpleDriverDataSource();

		ds.setDriverClass(org.h2.Driver.class);
		ds.setUrl("jdbc:h2:~/user-unit-testB");
		ds.setUsername("sa");
		return ds;
	}

}