package com.easytoolsoft.springboot.template.common.pair;

/**
 * @author zhiwei.deng
 * @date 2017-03-25
 **/
public class IdValuePair {
    private String id;
    private String value;
    private boolean selected;

    public IdValuePair() {
    }

    public IdValuePair(final String id, final String value) {
        this.id = id;
        this.value = value;
    }

    public IdValuePair(final String id, final String value, final boolean selected) {
        this(id, value);
        this.selected = selected;
    }

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(final boolean selected) {
        this.selected = selected;
    }
}
