package me.cxis.spring;

import me.cxis.spring.controller.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(UserController.class)
public class RestClientTests {

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private UserController userController;

    @Test
    public void testGetName() {
        server.expect(requestTo("/user/name?userId=1"))
                .andRespond(withSuccess("hello", MediaType.TEXT_PLAIN));

        String name = userController.getUserName(1);
        assertThat(name).isEqualTo("hello");
    }
}
