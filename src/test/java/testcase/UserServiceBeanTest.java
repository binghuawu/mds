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
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import conf.TestConfig;
import data.Application;
import data.domain.a.AUser;
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
	UserService<data.domain.a.AUser> userAService;

	@Autowired
	@Qualifier("u2")
	UserService<data.domain.b.BUser> userBService;

	final Logger LOG = LoggerFactory.getLogger(UserServiceBeanTest.class);

	@Before
	public void beforeTest() {
		final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(schemaScript);
		populator.addScript(dataScript);
		populator.execute(dataSource);
	}

	@Test(expected = InvalidDataAccessResourceUsageException.class)
	public void testSaveUser() {
		AUser u = new AUser();
		u.setName("test 1");
		u = userAService.create(u);
		Assert.assertNotNull(u.getId());
		Assert.assertNotNull(userAService.findOne(u.getId()));
		// this should fail because it isn't the same datasource
		Assert.assertNull(userBService.findOne(u.getId()));
	}

	@Test
	public void testUpdateProfile() {
		final AUser u = userAService.findOne(1L);
		Assert.assertNotNull(u);
		userAService.updateProfile(u.getId(), "new name 123");
		Assert.assertEquals("new name 123", userAService.findOne(1L).getName());
	}
}
