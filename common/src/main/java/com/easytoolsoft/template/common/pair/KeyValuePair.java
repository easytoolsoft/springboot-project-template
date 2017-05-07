package com.easytoolsoft.template.common.pair;

/**
 * @author zhiwei.deng
 * @date 2017-03-25
 **/
public class KeyValuePair {
    private String key;
    private String name;

    public KeyValuePair() {
    }

    public KeyValuePair(final String key, final String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public String getName() {
        return this.name;
    }

    public void setValue(final String name) {
        this.name = name;
    }
}
