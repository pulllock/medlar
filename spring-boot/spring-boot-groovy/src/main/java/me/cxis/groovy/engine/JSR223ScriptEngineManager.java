package me.cxis.groovy.engine;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import javax.script.*;
import java.time.LocalDateTime;

@Component
public class JSR223ScriptEngineManager {

    private ScriptEngineManager scriptEngineManager = new ScriptEngineManager();

    private ScriptEngine scriptEngine;

    @PostConstruct
    public void init() {
        scriptEngine = scriptEngineManager.getEngineByName("groovy");
    }


    public String executeScript(String script) {
        //  编译脚本，不需要每次都进行脚本的重新编译，可以在第一次编译后将编译后的内容放到缓存中
        Compilable compilableEngine = (Compilable) scriptEngine;
        try {
            CompiledScript compiledScript = compilableEngine.compile(script);

            // 可以传一些参数到脚本中
            Bindings bindings = compiledScript.getEngine().createBindings();
            bindings.put("now", LocalDateTime.now());

            // 执行脚本
            Object result = compiledScript.eval(bindings);
            System.out.println("result: " + result);
            return (String) result;
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        return null;
    }
}
