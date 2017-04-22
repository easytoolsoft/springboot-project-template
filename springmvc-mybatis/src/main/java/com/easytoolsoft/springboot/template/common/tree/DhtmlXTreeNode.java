package com.easytoolsoft.springboot.template.common.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * dhtmxTree(http://dhtmlx.com/docs/products/dhtmlxTree/)控件的树结点数据模型类
 * http://docs.dhtmlx.com/tree__syntax_templates.html#jsonformattemplate
 *
 * @author zhiwei.deng
 */
public class DhtmlXTreeNode {
    private final List<DhtmlXTreeNode> item;
    private String id;
    private String pid;
    private String text;
    private String tooltip;
    private int checked;
    private int child = 0;
    private int open;
    private int sequence = 10;

    public DhtmlXTreeNode() {
        this.item = new ArrayList<>(10);
    }

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getPid() {
        return this.pid;
    }

    public void setPid(final String pid) {
        this.pid = pid;
    }

    public String getText() {
        return this.text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public String getTooltip() {
        return this.tooltip;
    }

    public void setTooltip(final String tooltip) {
        this.tooltip = tooltip;
    }

    public int getChecked() {
        return this.checked;
    }

    public void setChecked(final int checked) {
        this.checked = checked;
    }

    public int getChild() {
        return this.child;
    }

    public void setChild(final int child) {
        this.child = child;
    }

    public int getOpen() {
        return this.open;
    }

    public void setOpen(final int open) {
        this.open = open;
    }

    public int getSequence() {
        return this.sequence;
    }

    public void setSequence(final int sequence) {
        this.sequence = sequence;
    }

    public List<DhtmlXTreeNode> getItem() {
        return this.item;
    }
}
