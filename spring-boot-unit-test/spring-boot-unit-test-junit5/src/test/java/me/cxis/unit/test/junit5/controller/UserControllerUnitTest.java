package me.cxis.unit.test.junit5.controller;

import me.cxis.unit.test.junit5.AbstractUnitTest;
import me.cxis.unit.test.junit5.manager.UserManager;
import me.cxis.unit.test.junit5.model.UserVO;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class UserControllerUnitTest extends AbstractUnitTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserControllerUnitTest.class);

    @Spy
    @InjectMocks
    private UserController userController;

    @Mock
    private UserManager userManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("getUserById参数错误")
    public void testGetUserByIdParamError() {
        String errorMsg = Assertions.assertThrows(
                RuntimeException.class,
                () -> {userController.queryById(anyLong());}
        ).getMessage();

        LOGGER.info(errorMsg);

        Assertions.assertEquals("参数错误", errorMsg);
    }

    @Test
    public void testGetUserByIdSuccess() {
        UserVO mockUser = new UserVO();
        mockUser.setId(1L);
        mockUser.setName("小明");

        when(userManager.queryById(anyLong())).thenReturn(mockUser);

        UserVO result = userController.queryById(1L);
        LOGGER.info(result.toString());
        Assertions.assertNotNull(result);
    }
}
