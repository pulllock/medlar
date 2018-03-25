package me.cxis.spring;

import me.cxis.spring.controller.UserController;
import me.cxis.spring.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockBeanTest {

    @MockBean
    private UserService userService;

    @Autowired
    private UserController userController;

    @Test
    public void testMockBean() {
        given(userService.getUserName(1))
                .willReturn("xiaohei");

        String name = userController.getUserName(1);
        assertThat(name).isEqualTo("xiaohei");
    }

}
