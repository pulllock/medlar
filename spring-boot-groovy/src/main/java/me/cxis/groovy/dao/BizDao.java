package me.cxis.groovy.dao;

import org.springframework.stereotype.Repository;

@Repository
public class BizDao {

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
}
