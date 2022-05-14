package me.cxis.groovy.manager;

import me.cxis.groovy.dao.BizDao;
import me.cxis.groovy.engine.GroovyClassLoaderScriptEngineManager;
import me.cxis.groovy.engine.GroovyShellScriptEngineManager;
import me.cxis.groovy.engine.JSR223ScriptEngineManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class BizManager {

    private final static Logger LOGGER = LoggerFactory.getLogger(BizManager.class);

    @Resource
    private BizDao bizDao;

    @Resource
    private JSR223ScriptEngineManager jsr223ScriptEngineManager;

    @Resource
    private GroovyClassLoaderScriptEngineManager groovyClassLoaderScriptEngineManager;

    @Resource
    private GroovyShellScriptEngineManager groovyShellScriptEngineManager;

    public void process() {
        LOGGER.info("处理业务逻辑");

        // 需要使用Groovy脚本进行处理的后续逻辑
        String jsr223Result = jsr223ScriptEngineManager.executeScript(bizDao.getJsr223GroovyContentFromDB());
        System.out.println(jsr223Result);

        String gclResult = groovyClassLoaderScriptEngineManager.executeScript(bizDao.getGroovyClassLoaderContentFromDB());
        System.out.println(gclResult);

        String groovyShellResult = groovyShellScriptEngineManager.executeScript(bizDao.getGroovyShellGroovyContentFromDB());
        System.out.println(groovyShellResult);
    }
}
