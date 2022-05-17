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

    public void save(Long bizResultId, String scriptResult) {
        LOGGER.info("保存：{} - {}", bizResultId, scriptResult);
    }
}
