package me.cxis.groovy.manager;

import com.alibaba.fastjson.JSON;
import me.cxis.groovy.dao.BizDao;
import me.cxis.groovy.dao.model.ScriptDO;
import me.cxis.groovy.engine.*;
import me.cxis.groovy.model.SubmitParam;
import me.cxis.groovy.model.SubmitResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

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

    @Resource
    private GCLEngineManager gclEngineManager;

    @Resource
    private ThreadPoolExecutor scriptExecuteTaskExecutor;

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

    public SubmitResult submit(SubmitParam param) {
        // 处理业务逻辑
        LOGGER.info("处理业务逻辑：{}", param);
        // 保存业务数据
        LOGGER.info("保存业务数据");

        SubmitResult result = new SubmitResult();
        result.setBizDesc("业务处理完成");
        Long bizResultId = 1L;

        // 查询是否有绑定动态脚本
        ScriptDO script = bizDao.queryScript();
        if (script == null) {
            return result;
        }

        // 过滤需要进行计算的数据
        String scriptParam = JSON.toJSONString(param);

        // 脚本执行的上下文
        ScriptContext context = new ScriptContext();
        context.setBizResultId(bizResultId);
        context.setParam(scriptParam);

        // 执行脚本

        // 同步执行
        if (script.getExecuteMode() == 1) {
            String scriptResult = execute(context, script.getName());
            result.setScriptResult(scriptResult);
        }
        // 异步执行
        else {
            result.setScriptResult("{}");
            CompletableFuture.supplyAsync(() -> execute(context, script.getName()), scriptExecuteTaskExecutor);
        }

        // 保存脚本执行结果

        LOGGER.info("返回结果：{}", result);
        return result;
    }

    private String execute(ScriptContext context, String scriptName) {
        LOGGER.info("execute: {} - {}", scriptName, context);
        AbstractResultCalculator calculator = gclEngineManager.getCalculator(scriptName);
        String scriptResult = calculator.calculate(context);
        if (scriptResult == null || scriptResult.length() == 0 || scriptResult.equals("null")) {
            scriptResult = "{}";
        }

        // 保存
        bizDao.save(context.getBizResultId(), scriptResult);

        return scriptResult;
    }

    public SubmitResult submit2(SubmitParam param) {
        // 处理业务逻辑
        LOGGER.info("处理业务逻辑：{}", param);
        // 保存业务数据
        LOGGER.info("保存业务数据");

        SubmitResult result = new SubmitResult();
        result.setBizDesc("业务处理完成");
        Long bizResultId = 1L;

        // 查询是否有绑定动态脚本
        ScriptDO script = bizDao.queryScript2();
        if (script == null) {
            return result;
        }

        // 过滤需要进行计算的数据
        String scriptParam = JSON.toJSONString(param);

        // 脚本执行的上下文
        ScriptContext context = new ScriptContext();
        context.setBizResultId(bizResultId);
        context.setParam(scriptParam);

        // 执行脚本

        // 同步执行
        if (script.getExecuteMode() == 1) {
            String scriptResult = execute(context, script);
            result.setScriptResult(scriptResult);
        }
        // 异步执行
        else {
            result.setScriptResult("{}");
            CompletableFuture.supplyAsync(() -> execute(context, script), scriptExecuteTaskExecutor);
        }

        // 保存脚本执行结果

        LOGGER.info("返回结果：{}", result);
        return result;
    }

    private String execute(ScriptContext context, ScriptDO script) {
        LOGGER.info("execute: {} - {}", script.getName(), context);
        AbstractResultCalculator calculator = gclEngineManager.getCalculator(script);
        String scriptResult = calculator.calculate(context);
        if (scriptResult == null || scriptResult.length() == 0 || scriptResult.equals("null")) {
            scriptResult = "{}";
        }

        // 保存
        bizDao.save(context.getBizResultId(), scriptResult);

        return scriptResult;
    }
}
