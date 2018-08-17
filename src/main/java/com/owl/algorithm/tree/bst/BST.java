package com.owl.algorithm.tree.bst;

import com.owl.algorithm.tree.BinNode;
import com.owl.algorithm.tree.BinTree;

/**
 * 二叉搜索树
 */
public abstract class BST<T> extends BinTree<T> {
    BinNode<T> _hot;//命中节点的父亲

    public abstract BinNode<T> search(T t);
    public abstract BinNode<T> insert(T t);
    public abstract BinNode<T> remove(T t);
}
