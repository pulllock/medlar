package me.cxis.spring.groovy;

import com.google.common.io.Resources;
import org.springframework.scripting.ScriptSource;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * 从数据库中读取groovy脚本内容
 */
public class DatabaseScriptSource implements ScriptSource {

    private final String scriptName;

    public DatabaseScriptSource(String scriptName) {
        this.scriptName = scriptName;
    }

    @Override
    public String getScriptAsString() throws IOException {
        // TODO 假装从数据库中根据scriptName读取
        System.out.println("xxxxxxxx:" + scriptName);
        URL url = this.getClass().getResource("/groovy/" + scriptName);
        String groovyContent = Resources.toString(url, StandardCharsets.UTF_8);
        System.out.println(groovyContent);
        return groovyContent;
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public String suggestedClassName() {
        return StringUtils.stripFilenameExtension(this.scriptName);
    }
}
