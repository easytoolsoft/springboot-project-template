package com.easytoolsoft.springboot.template.common.pair;

/**
 * @author zhiwei.deng
 * @date 2017-03-25
 **/
public class NameTextPair {
    private String name;
    private String text;
    private boolean selected;

    public NameTextPair() {
    }

    public NameTextPair(final String name, final String text) {
        this.name = name;
        this.text = text;
    }

    public NameTextPair(final String name, final String text, final boolean selected) {
        this(name, text);
        this.selected = selected;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getText() {
        return this.text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(final boolean selected) {
        this.selected = selected;
    }
}
