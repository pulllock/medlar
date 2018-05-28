package me.cxis.guice.example1;

import javax.inject.Inject;

public class ApplicationImpl implements Application {

    private UserService userService;
    private LogService logService;

    @Inject
    public ApplicationImpl(UserService userService, LogService logService) {
        this.userService = userService;
        this.logService = logService;
    }

    @Override
    public void work() {
        userService.process();
        logService.log("user service run...");
    }
}
