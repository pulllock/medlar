package me.cxis.spring;

import me.cxis.spring.chain.GatewayHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Configuration
public class ChainConfig {

    @Resource
    private List<GatewayHandler> gatewayHandlers;

    @PostConstruct
    private void initChain() {
        Collections.sort(gatewayHandlers, AnnotationAwareOrderComparator.INSTANCE);

        int size = gatewayHandlers.size();
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                gatewayHandlers.get(i).setNextHandler(null);
            } else {
                gatewayHandlers.get(i).setNextHandler(gatewayHandlers.get(i + 1));
            }
        }
    }
}
