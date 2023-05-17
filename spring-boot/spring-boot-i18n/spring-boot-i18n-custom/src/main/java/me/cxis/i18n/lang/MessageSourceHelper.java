package me.cxis.i18n.lang;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

@Component
public class MessageSourceHelper {

    @Resource
    private MessageSource messageSource;

    public String get(String code) {
        return get(code, null);
    }

    public String get(String code, Object[] args) {
        return get(code, null, null);
    }

    public String get(String code, Object[] args, String defaultMessage) {
        return messageSource.getMessage(code, args, defaultMessage, LocaleContextHolder.getLocale());
    }

    public String getLanguage() {
        return LocaleContextHolder.getLocale().getLanguage();
    }

    public String getCountry() {
        return LocaleContextHolder.getLocale().getCountry();
    }
}
