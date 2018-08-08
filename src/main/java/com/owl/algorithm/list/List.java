package com.owl.algorithm.list;

public class List<T> {
  private int _size;
  private ListNode<T> header, trailer;//头尾哨兵，很关键

  /**
   * 初始化方法
   */
  private void init() {
    header = new ListNode<>();
    trailer = new ListNode<>(null, header, null);
    header.setSucc(trailer);
    _size = 0;
  }

  public List() {
    init();
  }

  //只读接口
  public int size() {
    return this._size;
  }

  public boolean empty() {
    return _size <= 0;
  }

  public ListNode<T> first() {
    return header.getSucc();
  }

  public ListNode<T> last() {
    return trailer.getPred();
  }

  /**
   * 无序全表查找 o(n)
   */
  public ListNode<T> find(T data) {
    return find(data, _size, trailer);
  }

  /**
   * 无序区间（p节点n个前驱）查找
   */
  public ListNode<T> find(T data, int n, ListNode<T> p) {
    while (0 < n--) {
      if (data.equals(p.getPred().getData())) {
        return p;
      }
      p = p.getPred();
    }
    return null;
  }

  /**
   * 无序区间（p节点n个后继）查找
   */
  public ListNode<T> find(T data, ListNode<T> p, int n) {
    while (0 < n--) {
      if (data.equals(p.getSucc().getData())) {
        return p;
      }
      p = p.getSucc();
    }
    return null;
  }

  /**
   * 有序全表查找
   */
  public ListNode<T> search(T data) {
    return find(data, _size, trailer);
  }

  /**
   * 有序区间（p节点n个前驱）查找
   */
  public ListNode<T> search(T data, int n, ListNode<T> p) {
    return null;
  }

  /**
   * 有序区间（p节点n个后继）查找
   */
  public ListNode<T> search(T data, ListNode<T> p, int n) {
    return null;
  }

  //可写访问接口

  /**
   * 作为首节点插入
   */
  public ListNode<T> insertAsFirst(T data) {
    _size++;
    return header.insertAsSucc(data);
  }

  /**
   * 作为末节点插入
   */
  public ListNode<T> insertAsLast(T data) {
    _size++;
    return trailer.insertAsPred(data);
  }

  /**
   * 作为p节点前驱插入
   */
  public ListNode<T> insertBefore(ListNode<T> p, T data) {
    _size++;
    return p.insertAsPred(data);
  }

  /**
   * 作为p节点后继插入
   */
  public ListNode<T> insertAfter(ListNode<T> p, T data) {
    _size++;
    return p.insertAsSucc(data);
  }

  /**
   * 删除除合法位置p处合法的节点,迒回被删除节点
   */
  public T remove(ListNode<T> p) {
    _size--;
    p.getPred().setSucc(p.getSucc());
    p.getSucc().setPred(p.getPred());
    p.setPred(null);
    p.setSucc(null);
    return p.getData();
  }

  /**
   * 区间排序
   */
  public void sort(ListNode<T> p, int n) {

  }

  /**
   * 无序去重
   */
  public int deduplicate() {
    return 0;
  }

  /**
   * 有序去重
   */
  public int uniquify() {
    return 0;
  }

  /**
   * 反转
   */
  public void reverse() {
  }

  /**
   * 遍历
   */
  public void traverse() {
    ListNode<T> node = header.getPred();
    while (node != null) {
      System.out.println(node.getData());
      node = node.getSucc();
    }
  }

}
