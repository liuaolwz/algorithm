package com.owl.algorithm.sort;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

public class SortTest {
  private String[] arr;

  //随机生成50位以内大写字母数组
  @Before
  public void init(){
    arr = new String[ThreadLocalRandom.current().nextInt(50)];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = String.valueOf((char)ThreadLocalRandom.current().nextInt(97,122));
    }
  }
  //校验排序是否正确
  @After
  public void check(){
    assert Sort.isSorted(arr);
  }

  @Test
  public void testSelection(){
    Sort.SELECTION.sort(arr);
  }
  @Test
  public void testInsertion(){
    Sort.INSERTION.sort(arr);
  }
  @Test
  public void testSell(){
    Sort.SELL.sort(arr);
  }
}
