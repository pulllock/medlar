package me.cxis.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTemplateTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testRestTemplate() {
        String body = restTemplate.getForObject("/user/name?userId=1",String.class);
        assertThat(body).isEqualTo("xiaohong");
    }
}
