package com.easytoolsoft.template.common.pair;

/**
 * @author zhiwei.deng
 * @date 2017-03-25
 **/
public class IdTextPair {
    private String id;
    private String text;
    private boolean selected;

    public IdTextPair() {
    }

    public IdTextPair(final String id, final String text) {
        this.id = id;
        this.text = text;
    }

    public IdTextPair(final String id, final String text, final boolean selected) {
        this(id, text);
        this.selected = selected;
    }

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
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
