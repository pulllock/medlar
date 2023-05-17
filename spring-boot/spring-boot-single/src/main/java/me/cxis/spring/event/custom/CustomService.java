package me.cxis.spring.event.custom;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

@Service
public class CustomService {

    @Resource
    private CustomEventPublish publish;

    public void publish() {
        publish.publish(new CustomEvent(this));
    }
}
