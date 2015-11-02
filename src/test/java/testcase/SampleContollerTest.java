package testcase;

import java.net.URI;
import java.util.List;

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
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import conf.TestConfig;
import data.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest
@SpringApplicationConfiguration(classes = { Application.class, TestConfig.class })
public class SampleContollerTest {

	final RestTemplate template = new TestRestTemplate();

	@Value("${server.port}")
	String serverPort;

	@Value("${server.contextPath}")
	String serverContextRoot;

	@Value("classpath:sql/schema-h2.sql")
	private Resource schemaScript;

	@Value("classpath:sql/data.sql")
	private Resource dataScript;
	@Autowired
	DataSource dataSource;

	final Logger LOG = LoggerFactory.getLogger(SampleContollerTest.class);

	@Before
	public void beforeTest() {
		final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(schemaScript);
		populator.addScript(dataScript);
		populator.execute(dataSource);
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void testEntityAsJSON() {
		final String url = "http://localhost:" + serverPort + serverContextRoot
				+ "/users";

		RequestEntity<?> ent2 = RequestEntity.get(URI.create(url))
				.accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<List> resp = template.exchange(ent2, List.class);

		Assert.assertEquals(HttpStatus.OK, resp.getStatusCode());
		Assert.assertTrue(resp.getHeaders().getContentType()
				.includes(MediaType.APPLICATION_JSON));
		List<?> list = resp.getBody();
		Assert.assertTrue(!list.isEmpty());
	}
}
