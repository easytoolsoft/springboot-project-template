package com.easytoolsoft.template.common.spring.i18n;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author zhiwei.deng
 * @date 2017-05-08
 **/

public class MyResourceBundleMessageSource extends ResourceBundleMessageSource {
    public Map<String, String> getMessages(Locale locale, String... codePrefixes) {
        Map<String, String> messagesMap = new HashMap<>(128);
        Set<String> basenames = this.getBasenameSet();
        for (String basename : basenames) {
            ResourceBundle bundle = getResourceBundle(basename, locale);
            if (bundle != null) {
                for (String key : bundle.keySet()) {
                    if (StringUtils.startsWithAny(key, codePrefixes)) {
                        messagesMap.put(key, bundle.getString(key));
                    }
                }
            }
        }
        return messagesMap;
    }
}
