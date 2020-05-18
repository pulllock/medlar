package me.cxis.sentinel.example;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SentinelHelloExample {

    public static final String RESOURCE_NAME = "Hello_World_Resource";

    public static void main(String[] args) {
        Properties properties = System.getProperties();
        properties.setProperty("project.name", "SentinelExample_FromMain");
        properties.setProperty("csp.sentinel.dashboard.server", "127.0.0.1:8082");

        initFlowRules();
        while (true) {
            Entry entry = null;
            try {
                entry = SphU.entry(RESOURCE_NAME);

                // 业务逻辑
                System.out.println("业务逻辑。。。");
            } catch (BlockException e) {
                // 流控逻辑
                System.out.println("进入到流控了。。。");
            } finally {
                if (entry != null) {
                    entry.exit();
                }
            }
        }
    }

    private static void initFlowRules() {
        FlowRule rule = new FlowRule();
        rule.setResource(RESOURCE_NAME);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // QPS 1
        rule.setCount(1);

        List<FlowRule> rules = new ArrayList<>();
        rules.add(rule);

        FlowRuleManager.loadRules(rules);
    }
}
