package testcase;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import conf.TestConfig;
import data.Application;
import data.domain.a.User;
import data.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { Application.class, TestConfig.class })
public class UserServiceBeanTest {

	@Value("classpath:sql/schema-h2.sql")
	private Resource schemaScript;

	@Value("classpath:sql/data.sql")
	private Resource dataScript;

	@Autowired
	@Qualifier("dataSource")
	DataSource dataSource;

	@Autowired
	@Qualifier("u1")
	UserService<data.domain.a.User> userService;

	@Autowired
	@Qualifier("u2")
	UserService<data.domain.b.User> userCrudService;

	final Logger LOG = LoggerFactory.getLogger(UserServiceBeanTest.class);

	@Before
	public void beforeTest() {
		final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(schemaScript);
		populator.addScript(dataScript);
		populator.execute(dataSource);
	}

	@Test
	public void testSaveUser() {
		User u = new User();
		u.setName("test 1");
		u = userService.create(u);
		Assert.assertNotNull(u.getId());

		Assert.assertNull(userCrudService.findOne(u.getId()));
		Assert.assertNotNull(userService.findOne(u.getId()));
	}

	@Test
	public void testUpdateProfile() {
		final User u = userService.findOne(1L);
		Assert.assertNotNull(u);
		userService.updateProfile(u.getId(), "new name 123");
		Assert.assertEquals("new name 123", userService.findOne(1L).getName());
	}
}
