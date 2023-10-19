package fun.pullock.groovy.engine;

import groovy.lang.GroovyShell;
import org.springframework.stereotype.Component;

/**
 * GroovyShell用来执行一些脚本片段
 */
@Component
public class GroovyShellScriptEngineManager {

    GroovyShell groovyShell = new GroovyShell();

    public String executeScript(String script) {
        Object result = groovyShell.evaluate(script);

        return (String) result;
    }
}
