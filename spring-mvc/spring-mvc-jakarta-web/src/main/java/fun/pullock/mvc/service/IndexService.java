package fun.pullock.mvc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndexService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexService.class);

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getIndexName() {
        LOGGER.info(userService.getUserName(2L));
        return "index";
    }
}
