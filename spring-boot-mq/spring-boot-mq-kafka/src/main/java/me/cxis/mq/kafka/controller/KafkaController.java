package me.cxis.mq.kafka.controller;

import jakarta.annotation.Resource;
import me.cxis.mq.kafka.service.KafkaMessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Resource
    private KafkaMessageService kafkaMessageService;

    @GetMapping("/send")
    public Boolean send(@RequestParam String message) {
        return kafkaMessageService.send(message);
    }
}
