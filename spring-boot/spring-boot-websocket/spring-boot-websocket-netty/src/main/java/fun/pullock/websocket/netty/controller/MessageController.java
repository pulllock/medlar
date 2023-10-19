package fun.pullock.websocket.netty.controller;

import fun.pullock.websocket.netty.service.MessageService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    @GetMapping("/pushToUser")
    public void pushMessageToUser(@RequestParam("userId") String userId, @RequestParam("message") String message) {
        messageService.pushMessageToUser(userId, message);
    }

    @GetMapping("/pushToAll")
    public void pushMessageToAll(@RequestParam("message") String message) {
        messageService.pushMessageToAllUser(message);
    }
}
