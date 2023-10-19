package fun.pullock.groovy.engine;

import groovy.lang.GroovyClassLoader;
import fun.pullock.groovy.dao.model.ScriptDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class GCLEngineManager {

    private final static Logger LOGGER = LoggerFactory.getLogger(GCLEngineManager.class);

    private final GroovyClassLoader groovyClassLoader = new GroovyClassLoader();

    private final ConcurrentHashMap<String, AbstractResultCalculator> calculators = new ConcurrentHashMap<>();

    private final static String SCRIPT_DIR_PREFIX = "fun.pullock.groovy.scripts.";

    public AbstractResultCalculator getCalculator(String scriptName) {
        AbstractResultCalculator calculator = calculators.get(scriptName);
        if (calculator != null) {
            LOGGER.info("从缓存中获取");
            return calculator;
        }

        LOGGER.info("第一次缓存中没有，需要计算后放入缓存");

        try {

            Class<?> clazz = groovyClassLoader.loadClass(SCRIPT_DIR_PREFIX + scriptName);
            calculator = (AbstractResultCalculator) clazz.newInstance();

            calculators.put(scriptName, calculator);
        } catch (Exception e)  {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
        return calculator;
    }

    public AbstractResultCalculator getCalculator(ScriptDO script) {
        AbstractResultCalculator calculator = calculators.get(script.getName());
        if (calculator != null) {
            LOGGER.info("从缓存中获取");
            return calculator;
        }

        LOGGER.info("第一次缓存中没有，需要计算后放入缓存");

        try {

            Class<?> clazz = groovyClassLoader.parseClass(script.getContent());
            calculator = (AbstractResultCalculator) clazz.newInstance();

            calculators.put(script.getName(), calculator);
        } catch (Exception e)  {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
        return calculator;
    }
}
