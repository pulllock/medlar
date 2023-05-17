package me.cxis.spring.groovy;

import org.springframework.core.io.ResourceLoader;
import org.springframework.scripting.ScriptSource;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.scripting.support.ScriptFactoryPostProcessor;
import org.springframework.scripting.support.StaticScriptSource;
import org.springframework.stereotype.Component;

/**
 * 自定义ScriptFactoryPostProcessor，支持从数据中读取groovy脚本
 */
@Component
public class CustomScriptFactoryPostProcessor extends ScriptFactoryPostProcessor {

    public static final String LANG_PREFIX_DATABASE = "database:";

    @Override
    protected ScriptSource convertToScriptSource(String beanName, String scriptSourceLocator, ResourceLoader resourceLoader) {
        if (scriptSourceLocator.startsWith(INLINE_SCRIPT_PREFIX)) {
            return new StaticScriptSource(scriptSourceLocator.substring(INLINE_SCRIPT_PREFIX.length()), beanName);
        }
        else if (scriptSourceLocator.startsWith(LANG_PREFIX_DATABASE)) {
            return new DatabaseScriptSource(scriptSourceLocator.substring(LANG_PREFIX_DATABASE.length()));
        }
        else {
            return new ResourceScriptSource(resourceLoader.getResource(scriptSourceLocator));
        }
    }
}
