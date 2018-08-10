package com.owl.algorithm.list;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ListTest {
  List<Integer> tags = new List<>();
  @Before
  public void init(){
    tags.insertAsLast(1);
    tags.insertAsLast(1);
    tags.insertAsLast(2);
    tags.insertAsLast(3);
    tags.insertAsLast(3);
    tags.insertAsLast(6);
    tags.insertAsLast(7);
    tags.insertAsLast(7);
    tags.insertAsLast(8);
    tags.insertAsLast(9);
    System.out.print("原始链表：");
    tags.traverse();
  }
  @Test
  public void find() throws Exception {
    final ListNode<Integer> node = tags.find(9);
    System.out.println(node);
  }

  @Test
  public void search() throws Exception {
  }

  @Test
  public void remove() throws Exception {

  }

  @Test
  public void deduplicate() throws Exception {
    final int count = tags.deduplicate();
    System.out.print("去重后： ");
    tags.traverse();
  }

  @Test
  public void uniquify() throws Exception {
    final int count = tags.uniquify();
    System.out.print("去重后： ");
    tags.traverse();
  }

  @Test
  public void reverse() throws Exception {
    tags.reverse();
    tags.traverse();
  }

}