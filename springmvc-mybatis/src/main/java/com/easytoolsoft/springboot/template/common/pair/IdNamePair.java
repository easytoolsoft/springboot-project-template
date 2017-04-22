package com.easytoolsoft.springboot.template.common.pair;

/**
 * @author zhiwei.deng
 * @date 2017-03-25
 **/
public class IdNamePair {
    private String id;
    private String name;
    private boolean selected;

    public IdNamePair() {
    }

    public IdNamePair(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    public IdNamePair(final String id, final String name, final boolean selected) {
        this(id, name);
        this.selected = selected;
    }

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(final boolean selected) {
        this.selected = selected;
    }
}
