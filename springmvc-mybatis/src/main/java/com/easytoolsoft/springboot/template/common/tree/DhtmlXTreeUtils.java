package com.easytoolsoft.springboot.template.common.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;

/**
 * dhtmxTree(http://dhtmlx.com/docs/products/dhtmlxTree/)控件的工具类
 *
 * @author zhiwei.deng
 */
public class DhtmlXTreeUtils {
    public static DhtmlXTreeNode getRootNode(final String id, final List<DhtmlXTreeNode> nodes) {
        final DhtmlXTreeNode rootNode = new DhtmlXTreeNode();
        rootNode.setId(id);
        rootNode.getItem().addAll(nodes);
        return rootNode;
    }

    public static JSONObject getJSONObject(final String id, final List<DhtmlXTreeNode> nodes) {
        return JSONObject.parseObject(getJSONString(id, nodes));
    }

    public static String getJSONString(final String id, final List<DhtmlXTreeNode> nodes) {
        final String jsonTextFormat = "{id:\"%1$s\", item:[%2$s]}";
        final String jsonNodeFormat = "{id:\"%1$s\",text:\"%2$s\",tooltip:\"%3$s\",child:%4$s}";

        final StringBuilder jsonString = new StringBuilder();
        final int count = nodes.size();
        for (int i = 0; i < count; i++) {
            final DhtmlXTreeNode node = nodes.get(i);
            final String jsonNodeStr = String.format(jsonNodeFormat, node.getId(), node.getText(), node.getTooltip(),
                node.getChild());
            jsonString.append(jsonNodeStr).append(i < count - 1 ? "," : "");
        }
        return String.format(jsonTextFormat, id, jsonString);
    }

    public static List<DhtmlXTreeNode> getNodes(final Collection<DhtmlXTreeNode> nodes, final String rootId) {
        if (nodes == null || nodes.size() == 0) {
            return new ArrayList<>(0);
        }

        final List<DhtmlXTreeNode> rootNodes = nodes.stream()
            .filter(x -> x.getPid().equals(rootId))
            .sorted((x, y) -> x.getSequence() > y.getSequence() ? 1 : -1)
            .collect(Collectors.toList());

        for (final DhtmlXTreeNode rootNode : rootNodes) {
            getChildNodes(nodes, rootNode);
        }
        return rootNodes;
    }

    private static void getChildNodes(final Collection<DhtmlXTreeNode> nodes, final DhtmlXTreeNode node) {
        final List<DhtmlXTreeNode> childNodes = nodes.stream()
            .filter(x -> x.getPid().equals(node.getId()))
            .sorted((x, y) -> x.getSequence() > y.getSequence() ? 1 : -1)
            .collect(Collectors.toList());

        for (final DhtmlXTreeNode childNode : childNodes) {
            node.getItem().add(childNode);
            getChildNodes(nodes, childNode);
        }
    }
}
