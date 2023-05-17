package me.cxis.groovy.dao;

import me.cxis.groovy.dao.model.ScriptDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Random;

@Repository
public class BizDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(BizDao.class);

    public String getJsr223GroovyContentFromDB() {
        return "println('process 1')\n" +
                "return \"processed by jsr223\"";
    }

    public String getGroovyClassLoaderContentFromDB() {
        return "package me.cxis.groovy\n" +
                "\n" +
                "import me.cxis.groovy.engine.AbstractBizScriptProcessor\n" +
                "\n" +
                "class BizProcessor extends AbstractBizScriptProcessor{\n" +
                "\n" +
                "    @Override\n" +
                "    String processScript() {\n" +
                "        println('processScript')\n" +
                "        return \"processed by groovy class loader\"\n" +
                "    }\n" +
                "}\n";
    }

    public String getGroovyShellGroovyContentFromDB() {
        return "println('process groovy shell')\n" +
                "return \"processed by groovy shell\"";
    }

    public ScriptDO queryScript() {
        ScriptDO scriptDO = new ScriptDO();
        scriptDO.setName("SubmitScript");
        scriptDO.setExecuteMode(new Random().nextInt(3));
        return scriptDO;
    }

    public ScriptDO queryScript2() {
        ScriptDO scriptDO = new ScriptDO();
        scriptDO.setName("SubmitScript2");
        scriptDO.setExecuteMode(new Random().nextInt(3));
        scriptDO.setContent("package me.cxis.groovy.scripts\n" +
                "\n" +
                "import groovy.json.JsonOutput\n" +
                "import groovy.json.JsonSlurper\n" +
                "import groovy.transform.EqualsAndHashCode\n" +
                "import me.cxis.groovy.engine.AbstractResultCalculator\n" +
                "import me.cxis.groovy.engine.ScriptContext\n" +
                "\n" +
                "class SubmitScript2 extends AbstractResultCalculator {\n" +
                "\n" +
                "    @Override\n" +
                "    String calculate(ScriptContext context) {\n" +
                "        if (!context) {\n" +
                "            return null\n" +
                "        }\n" +
                "\n" +
                "        if (!context.bizResultId || !context.param) {\n" +
                "            return null\n" +
                "        }\n" +
                "\n" +
                "        def jsonSlurper = new JsonSlurper()\n" +
                "\n" +
                "        def paramMap = jsonSlurper.parseText(context.param)\n" +
                "\n" +
                "        assert paramMap instanceof Map\n" +
                "\n" +
                "        int age = paramMap.get(\"age\") as int\n" +
                "\n" +
                "        def result = new BizCalResult()\n" +
                "        result.age = age\n" +
                "\n" +
                "        if (age > 20) {\n" +
                "            result.code = 'One-2'\n" +
                "            result.num = 1\n" +
                "        } else if (age > 10){\n" +
                "            result.code = 'Two-2'\n" +
                "            result.num = 2\n" +
                "        } else {\n" +
                "            result = null\n" +
                "        }\n" +
                "\n" +
                "        return JsonOutput.toJson(result)\n" +
                "    }\n" +
                "\n" +
                "    @EqualsAndHashCode\n" +
                "    class BizCalResult {\n" +
                "        String code\n" +
                "        int num\n" +
                "        int age\n" +
                "    }\n" +
                "}\n");
        return scriptDO;
    }

    public void save(Long bizResultId, String scriptResult) {
        LOGGER.info("保存：{} - {}", bizResultId, scriptResult);
    }
}
