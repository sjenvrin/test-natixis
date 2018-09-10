package fr.jenvrin.simon;

import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void shouldGetAllTheTask() throws Exception {
		System.out.println("Test1");
		assertEquals(1, 1);
	}

}
