package com.stylefeng.guns.core.util;

import com.stylefeng.guns.common.persistence.model.Modules;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

public class TreeBuilder {

    List<Modules> nodes = new ArrayList<>();

    @ResponseBody
    public TreeBuilder buildTree(List<Modules> nodes) {

        TreeBuilder treeBuilder = new TreeBuilder(nodes);

        return treeBuilder;
    }

    public TreeBuilder() {
    }

    public TreeBuilder(List<Modules> nodes) {
        super();
        this.nodes = nodes;
    }


    // 构建树形结构
    public List<Modules> buildTree() {
        List<Modules> treeNodes = new ArrayList<>();
        List<Modules> rootNodes = getRootNodes();
        for (Modules rootNode : rootNodes) {
            buildChildNodes(rootNode);
            treeNodes.add(rootNode);
        }
        return treeNodes;
    }

    // 递归子节点
    public void buildChildNodes(Modules Modules) {
        List<Modules> children = getChildNodes(Modules);
        if (!children.isEmpty()) {
            for (Modules child : children) {
                buildChildNodes(child);
            }
            Modules.setChildren(children);
        }
    }

    // 获取父节点下所有的子节点
    public List<Modules> getChildNodes(Modules pnode) {
        List<Modules> childNodes = new ArrayList<>();
        for (Modules n : nodes) {
            if (pnode.getId().equals(n.getPid())) {
                childNodes.add(n);
            }
        }
        return childNodes;
    }

    // 判断是否为根节点
    public boolean rootNode(Modules Modules) {
        boolean isRootNode = true;
        for (Modules n : nodes) {
            if (Modules.getPid().equals(n.getId())) {
                isRootNode = false;
                break;
            }
        }
        return isRootNode;
    }

    // 获取集合中所有的根节点
    public List<Modules> getRootNodes() {
        List<Modules> rootNodes = new ArrayList<>();
        for (Modules n : nodes) {
            if (rootNode(n)) {
                rootNodes.add(n);
            }
        }
        return rootNodes;
    }
}
