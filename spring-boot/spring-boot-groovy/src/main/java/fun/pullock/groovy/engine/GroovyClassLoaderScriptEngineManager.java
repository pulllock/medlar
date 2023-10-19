package fun.pullock.groovy.engine;

import groovy.lang.GroovyClassLoader;
import org.springframework.stereotype.Component;

/**
 * GroovyClassLoader
 */
@Component
public class GroovyClassLoaderScriptEngineManager {

    GroovyClassLoader groovyClassLoader = new GroovyClassLoader();

    public String executeScript(String script) {
        Class<?> clazz = groovyClassLoader.parseClass(script);
        try {
            AbstractBizScriptProcessor scriptProcessor = (AbstractBizScriptProcessor) clazz.newInstance();
            return scriptProcessor.processScript();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
