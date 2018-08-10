package com.owl.algorithm.list;

/**
 * 双向链表节点
 */
public class ListNode<T> {
  private T data;
  private ListNode<T> pred, succ;//前驱，后继

  public ListNode() {
  }

  public ListNode(T data, ListNode<T> pred, ListNode<T> succ) {
    this.data = data;
    this.pred = pred;
    this.succ = succ;
  }

  /**
   * 作为前驱插入
   */
  public ListNode<T> insertAsPred(T data) {
    ListNode<T> node = new ListNode<>(data, pred, this);
    pred.setSucc(node);
    pred = node;
    return node;
  }

  /**
   * 作为后继插入
   */
  public ListNode<T> insertAsSucc(T data) {
    ListNode<T> node = new ListNode<>(data, this, succ);
    succ.setPred(node);
    succ = node;
    return node;
  }

  public void setData(T data) {
    this.data = data;
  }

  public void setPred(ListNode<T> pred) {
    this.pred = pred;
  }

  public void setSucc(ListNode<T> succ) {
    this.succ = succ;
  }

  public T getData() {
    return data;
  }

  public ListNode<T> getPred() {
    return pred;
  }

  public ListNode<T> getSucc() {
    return succ;
  }

  @Override
  public String toString() {
    return "ListNode{" +
        "data=" + data +
        ", pred=" + pred.getData() +
        ", succ=" + succ.getData() +
        '}';
  }
}
