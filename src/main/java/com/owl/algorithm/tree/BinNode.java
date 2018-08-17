package com.owl.algorithm.tree;

/**
 * 二叉树节点
 */
public class BinNode<T> {
    T data;
    boolean color;//节点颜色true-红，false-黑
    BinNode<T> parent;//父节点
    BinNode<T> lChild;//左子节点
    BinNode<T> rChild;//右子节点
}
