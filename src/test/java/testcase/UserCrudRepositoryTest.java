package testcase;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import conf.TestConfig;
import data.Application;
import data.dao.a.ARepository;
import data.dao.b.BRepository;
import data.domain.a.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { Application.class, TestConfig.class })
public class UserCrudRepositoryTest {

	@Value("classpath:sql/schema-h2.sql")
	private Resource schemaScript;

	@Value("classpath:sql/data.sql")
	private Resource dataScript;

	@Autowired
	ARepository crudRepo;

	@Autowired
	BRepository bRepo;

	@Autowired
	DataSource dataSource;

	final Logger LOG = LoggerFactory.getLogger(UserCrudRepositoryTest.class);

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
		u = crudRepo.save(u);
		Assert.assertNotNull(u.getId());

	}

	@Test
	public void testFindByName() {
		User u = crudRepo.findByNameJPQL("bob");
		Assert.assertNotNull(u);
		// Assert.assertEquals(2, u.getProjects().size());
		Assert.assertEquals("M", u.getDetail().getGender());
		Assert.assertEquals("bob", u.getDetail().getUser().getName());

		u = crudRepo.findByName("lisa");
		Assert.assertNotNull(u);
		Assert.assertEquals(Long.valueOf(1), crudRepo.countByNameNative("lisa"));
	}

	@Test
	// In case of lazy fetch, access to object properties should be guarded
	// within the same transaction. Otherwise, you will fail and end up with
	// "no session" error.
	@Transactional
	public void testLazyFetch() {
		User u = crudRepo.findByNameJPQL("bob");
		Assert.assertNotNull(u);
		Assert.assertNotNull(u.getDetail());
		Assert.assertEquals(2, u.getProjects().size());
		// test the 1st level cache
		User u2 = crudRepo.findByName("bob");
		Assert.assertNotNull(u2);
		Assert.assertTrue("level 1 cache is disabled", u.equals(u2));
	}
}
