package com.owl.algorithm.list;

import com.sun.istack.internal.NotNull;

/**
 * 双向链表
 */
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
    public ListNode<T> find(@NotNull T data) {
        return find(data, header, _size);
    }

    /**
     * 无序区间（p节点n个前驱）查找
     */
    public ListNode<T> find(@NotNull T data, int n, ListNode<T> p) {
        while (0 < n--) {
            if (data.equals(p.getPred().getData())) {
                return p.getPred();
            }
            p = p.getPred();
        }
        return null;
    }

    /**
     * 无序区间（p节点n个后继）查找
     */
    public ListNode<T> find(@NotNull T data, ListNode<T> p, int n) {
        while (0 < n--) {
            if (data.equals(p.getSucc().getData())) {
                return p.getSucc();
            }
            p = p.getSucc();
        }
        return null;
    }

    /**
     * 有序全表查找
     */
    public ListNode<T> search(@NotNull T data) {
        return find(data, _size, trailer);
    }

    /**
     * 有序区间（p节点n个前驱）查找
     */
    public ListNode<T> search(@NotNull T data, int n, ListNode<T> p) {
        return null;
    }

    /**
     * 有序区间（p节点n个后继）查找
     */
    public ListNode<T> search(@NotNull T data, ListNode<T> p, int n) {
        return null;
    }

    //可写访问接口

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
     * 区间排序
     */
    public void sort(ListNode<T> p, int n) {

    }

    /**
     * 无序去重
     */
    public int deduplicate() {
        ListNode<T> p = first();
        int count = 0;
        while (p != trailer) {
            T data = p.getData();
            ListNode<T> q = p;//记录p节点，遍历p子节点
            while (q.getSucc() != trailer) {
                if (data.equals(q.getSucc().getData())) {
                    count++;
                    remove(q.getSucc());
                } else {
                    q = q.getSucc();
                }
            }
            p = p.getSucc();
        }
        return count;
    }

    /**
     * 有序去重
     */
    public int uniquify() {
        ListNode<T> p = first();
        int count = 0;
        while (p.getSucc() != trailer) {
            if (p.getData().equals(p.getSucc().getData())) {
                remove(p.getSucc());
                count++;
            } else {
                p = p.getSucc();
            }
        }
        return count;
    }

    /**
     * 反转
     */
    public void reverse() {
        //两两交换data
        ListNode<T> p = first();
        ListNode<T> q = last();
        for (int i = 0; i < _size / 2; i++) {
            final T data = p.getData();
            p.setData(q.getData());
            q.setData(data);
            p = p.getSucc();
            q = q.getPred();
        }
        //适用单链表反转，整体思路是，遍历每个节点，并将其后继作为首节点
//    ListNode<T> cur = first();
//    while (cur.getSucc()!=trailer){
//      ListNode<T> tmp = cur.getSucc();
//      //将cur的后继作为首节点
//      cur.setSucc(tmp.getSucc());
//      cur.setPred(tmp);
//      tmp.setSucc(header.getSucc());
//      tmp.setPred(header);
//      header.setSucc(tmp);
//    }
    }

    /**
     * 遍历
     */
    public void traverse() {
        ListNode<T> node = header.getSucc();
        while (node != trailer) {
            System.out.print(node.getData() + " ");
            node = node.getSucc();
        }
        System.out.println();
    }

}
